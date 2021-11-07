package org.geek.Mybatis.CRUD;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.geek.Mybatis.CRUD.mapper.UsersMapper;
import org.geek.Mybatis.CRUD.model.Goods;
import org.geek.Mybatis.CRUD.model.Orders;
import org.geek.Mybatis.CRUD.model.Users;
import org.geek.Mybatis.CRUD.service.goodsIService;
import org.geek.Mybatis.CRUD.service.ordersIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private UsersMapper usersMapper;
	@Autowired
	private goodsIService goodsIService;
	@Autowired
	private ordersIService ordersIService;

	@Test
	void contextLoads() {

	}

	@Test
	public void testSelectUsers(){
		System.out.println(("----- selectAll method test ------"));
		List<Users> userList = usersMapper.selectList(null);
		//Assert.assertEquals(5, userList.size());
		userList.forEach(System.out::println);
		List<Users> user = new LambdaQueryChainWrapper<>(usersMapper)
				.eq(Users::getUid,1)
				.list();
		user.forEach(System.out::println);

	}

	@Test
	public void testInsertUsers(){
		System.out.println(("----- Insert method test ------"));

//		Users users = new Users();
//		users.setGender("男");
//		users.setAddress("Xi'an");
//		users.setName("Marvin");
//		users.setNumber("1888888888");
//		users.setLast_update_time(new Timestamp(new Date().getTime()));
//		users.setCreate_time(new Timestamp(new Date().getTime()));
//		int result = usersMapper.insert(users);

		Users users1 = new Users();
		users1.setGender("男");
		users1.setAddress("咸阳");
		users1.setName("宁宁");
		users1.setNumber("1888888888");
		users1.setLast_update_time(new Timestamp(new Date().getTime()));
		users1.setCreate_time(new Timestamp(new Date().getTime()));
		int result2 = usersMapper.insert(users1);

		//Assert.assertEquals(5, userList.size());
		System.out.println(result2);
	}

	@Test
	public void batchInsertGoods(){
		System.out.println(("----- batch insert goods ------"));
		List<Goods> entityList = new ArrayList<>(1000);

		for (int i = 0; i < 1000; i++) {
			Goods good = new Goods();
			good.setGoodsname("商品 "+i);
			good.setCreate_time(new Timestamp(new Date().getTime()));
			good.setLast_update_time(new Timestamp(new Date().getTime()));
			good.setPrice(String.valueOf(Math.random()));
			good.setCategory("类别");
			entityList.add(good);
		}

		boolean b = goodsIService.saveBatch(entityList);

	}

	//-----100W 总共耗时  ------32755ms
	@Test
	public void batchInsertOrders() {
		System.out.println(("----- batch insert orders ------"));
		long startTime= System.currentTimeMillis();
		List<Orders> entityList = new ArrayList<>(1000000);
		for (int i = 0; i < 1000000; i++) {
			Orders order = new Orders();
			order.setCreate_time(new Timestamp(new Date().getTime()));
			order.setLast_update_time(new Timestamp(new Date().getTime()));
			order.setOrderdetail("商品详情");
			order.setGoodsid(i);
			order.setOrdernumber(String.valueOf(new Random().nextInt(1000000)));
			order.setUid(i);
			order.setStatus("正常");
			entityList.add(order);
		}
		ordersIService.saveBatch(entityList);
		long endTime= System.currentTimeMillis();

		System.out.println(("----- 总共耗时  ------" +(endTime - startTime)+"ms"));


	}

}

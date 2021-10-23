package org.dml;

import org.util.JDBCDataSource;
import org.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HikariTest {

    static void prepared_batch_insert(Connection connection)  {
        //需要执行的sql语句
        String sql = "insert into actor(first_name,last_name) values(?,?)";
        //获取预处理对象，并给参数赋值
        PreparedStatement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareCall(sql);
            statement.setString(1,"SALLY");
            statement.setString(2,"WU");
            statement.addBatch();

            statement.setString(1,"MARVIN");
            statement.setString(2,"TIAN");
            statement.addBatch();

            statement.setString(1,"MAX");
            statement.setString(2,"CAO");
            statement.addBatch();

            statement.setString(1,"HELLO");
            statement.setString(20,"WORLD");
            statement.addBatch();

            //执行sql语句（执行了几条记录，就返回几）
            statement.addBatch();  //executeUpdate：执行并更新
            int [] counts = statement.executeBatch();
            connection.commit();
            System.out.println("insert done" + counts);
        } catch (SQLException throwables) {

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }

        //关闭jdbc连接
        JdbcUtil.closeResource(null,statement,connection);
    }


    static void prepared_query(Connection connection) throws SQLException {
        String sql = "select * from actor";
        ResultSet resultSet = null;
        //获取预处理对象，并给参数赋值
        PreparedStatement statement = connection.prepareCall(sql);
        resultSet= statement.executeQuery();
        while (resultSet.next()){
            System.out.print(resultSet.getString("first_name") + "--");
            System.out.print(resultSet.getString("last_name"));
            System.out.println();
        }
        JdbcUtil.closeResource(null,statement,connection);
    }



    public static void main(String[] args) throws SQLException {
        Connection connection = new JDBCDataSource().getConnection();
        //prepared_batch_insert(connection);
        prepared_query(connection);
    }
}

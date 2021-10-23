package org.dml;

import org.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUDTest {


    //prepared statement
    static void prepared_insert(Connection connection)  {
        //需要执行的sql语句
        String sql = "insert into actor(first_name,last_name) values(?,?)";
        //获取预处理对象，并给参数赋值
        PreparedStatement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareCall(sql);
            statement.setString(1,"SALLY");
            statement.setString(2,"WU");
            //执行sql语句（执行了几条记录，就返回几）
            int i = statement.executeUpdate();  //executeUpdate：执行并更新

            connection.commit();
        } catch (SQLException throwables) {

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }


        System.out.println("insert done");
        //关闭jdbc连接
        JdbcUtil.closeResource(null,statement,connection);
    }

    static void prepared_delete(Connection connection) throws SQLException  {
        String sql = "delete from actor where actor_id = 201";
        //获取预处理对象，并给参数赋值
        PreparedStatement statement =null;
        try{
            statement = connection.prepareCall(sql);
            connection.setAutoCommit(false);
            int i = statement.executeUpdate();
            connection.commit();
        }
        catch (SQLException throwables) {

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }

        //executeUpdate：执行并更新
        System.out.println("delete 执行完成 ");
        JdbcUtil.closeResource(null,statement,connection);
    }

    static void prepared_modify(Connection connection) throws SQLException {
        String sql = "update actor set first_name='123' where first_name='SALLY' ";
        //获取预处理对象，并给参数赋值
        connection.setAutoCommit(false);
        PreparedStatement statement = connection.prepareCall(sql);
        int i = statement.executeUpdate();
        //executeUpdate：执行并更新
        System.out.println(i);
        JdbcUtil.closeResource(null,statement,connection);
    }

    static void prepared_query(Connection connection) throws SQLException {
        String sql = "select * from actor";
        ResultSet resultSet = null;
        //获取预处理对象，并给参数赋值
        PreparedStatement statement = connection.prepareCall(sql);
        resultSet= statement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("first_name"));
            System.out.print(" ");
            System.out.print(resultSet.getString("last_name"));
        }
        JdbcUtil.closeResource(null,statement,connection);
    }

    public static void main(String[] args) throws SQLException {
        //获取数据库连接
        Connection connection = JdbcUtil.getConnection();
        connection.setAutoCommit(false);
        //prepared statement
        //prepared_insert(connection);
        //prepared_delete(connection);
        //prepared_modify(connection);
        prepared_query(connection);

    }
}

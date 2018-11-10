package dao;

import pojo.User;

import java.sql.*;

public class MainDao {

    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/db"; // 我的数据库名称为：db
    static final String NAME = "root";                          // 我的数据库用户名为：root
    static final String PASSWORD = "";                          // 我的数据库密码为：空

    public User findUserByAccount(String account) {
        try {
            Class.forName(DRIVER);
            Connection connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            Statement statement = connection.createStatement();
            String sql = "SELECT account, password FROM user WHERE account='" + account + "'"; // 从user表查找数据
            ResultSet resultSet = statement.executeQuery(sql);
            User user = new User();
            while (resultSet.next()) {
                user.setAccount(resultSet.getString("account"));
                user.setPassword(resultSet.getString("password"));
                break;
            }
            resultSet.close();
            statement.close();
            connection.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

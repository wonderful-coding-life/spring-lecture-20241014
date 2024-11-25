package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutor", "tutor", "tutorp");
//        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM member");

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM member WHERE id=?");

        preparedStatement.setInt(1, 5);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            var member = new Member(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getInt("age"));
            System.out.println("result set " + member);
        }

        preparedStatement.setInt(1, 7);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            var member = new Member(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getInt("age"));
            System.out.println("result set " + member);
        }

        connection.close();
    }
}
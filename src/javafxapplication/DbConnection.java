/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lakshan
 */
public class DbConnection {

    private static Connection connection;

    public static Connection connectDb() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:80/Pet", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}

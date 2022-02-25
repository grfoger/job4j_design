package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config conf = new Config("app.properties");
        conf.load();
        Class.forName(conf.value("hibernate.connection.driver_class"));
        try (Connection connection = DriverManager.getConnection(
                        conf.value("hibernate.connection.url"),
                        conf.value("hibernate.connection.username"),
                        conf.value("hibernate.connection.password"))) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
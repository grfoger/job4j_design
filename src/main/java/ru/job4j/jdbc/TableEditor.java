package ru.job4j.jdbc;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (FileReader reader = new FileReader("app.properties");) {
            properties.load(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (TableEditor table = new TableEditor(properties)) {
            table.dropTable("some_table");
            table.createTable("some_table");
            System.out.println(getTableScheme(table.connection, "some_table"));
            table.addColumn("some_table", "name", "text");
            System.out.println(getTableScheme(table.connection, "some_table"));
            table.renameColumn("some_table", "name", "family");
            System.out.println(getTableScheme(table.connection, "some_table"));
            table.dropColumn("some_table", "family");
            System.out.println(getTableScheme(table.connection, "some_table"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initConnection() {
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        try {
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private Statement initStatement() throws SQLException {
        return connection.createStatement();
    }

    public void createTable(String tableName) {
        try (Statement stat = initStatement()) {
            String sql = String.format(
                    "create table if not exists " + tableName + "(id serial primary key);"
            );
            stat.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try (Statement stat = initStatement()) {
            String sql = String.format(
                    "drop table if exists " + tableName + ";"
            );
            stat.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try (Statement stat = initStatement()) {
            String sql = String.format(
                    "alter table " + tableName + " add column " + columnName + " " + type + ";"
            );
            stat.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement stat = initStatement()) {
            String sql = String.format(
                    "alter table " + tableName + " drop column " + columnName + ";"
            );
            stat.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement stat = initStatement()) {
            String sql = String.format(
                    "alter table " + tableName + " rename column " + columnName + " to " + newColumnName + ";"
            );
            stat.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
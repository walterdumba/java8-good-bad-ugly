package org.examples.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonDAO {
    private static final String DB_ENDPOINT = "jdbc:mysql://localhost/test?user=minty&password=greatsqldb";

    public List<Person> findAll() throws SQLException {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try{
            connection = DriverManager.getConnection(DB_ENDPOINT);
            statement = connection.prepareStatement("SELECT * FROM person");
            resultSet = statement.executeQuery();
            return toPersonList(resultSet);
        }catch (Exception e){
            throw e;//Say we rethrow it and a more meaningful info was added just for illustration purpose
        }
        finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public Person findPersonById(String id) throws SQLException {
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try{
            connection = DriverManager.getConnection(DB_ENDPOINT);
            statement = connection.prepareStatement("SELECT * FROM person WHERE id=?");
            statement.setString(1, id);
            resultSet = statement.executeQuery();
            return toPerson(resultSet);
        }catch (Exception e){
            throw e;//Say we rethrow it and a more meaningful info was added just for illustration purpose
        }
        finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public int update(String id, String name) throws SQLException {
        Connection connection=null;
        PreparedStatement statement=null;
        try{
            connection = DriverManager.getConnection(DB_ENDPOINT);
            statement = connection.prepareStatement("UPDATE PERSON SET name=? WHERE id=?");
            statement.setString(1, name);
            statement.setString(2, id);
            return statement.executeUpdate();
        }catch (Exception e){
            throw e;//Say we rethrow it and a more meaningful info was added just for illustration purpose
        }
        finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public int insert(String name, int age) throws SQLException {
        Connection connection=null;
        PreparedStatement statement=null;
        try{
            connection = DriverManager.getConnection(DB_ENDPOINT);
            statement = connection.prepareStatement("INSERT INTO person VALUES(?,?)");
            statement.setString(1, name);
            statement.setInt(2, age);
            return statement.executeUpdate();
        }catch (Exception e){
            throw e;//Say we rethrow it and a more meaningful info was added just for illustration purpose
        }
        finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    private static Person toPerson(ResultSet rs){
        return null;//TODO:
    }

    private static List<Person> toPersonList(ResultSet rs){
        return null;//TODO:
    }
}
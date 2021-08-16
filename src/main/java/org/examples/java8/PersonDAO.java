package org.examples.java8;

import org.examples.common.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonDAO {
    private static final String DB_ENDPOINT = "jdbc:mysql://localhost/test?user=minty&password=greatsqldb";

    public List<Person> findAll() throws SQLException {
        return doWithBlock("SELECT * FROM person", statement -> toPersonList(statement.executeQuery()));
    }

    public Person findPersonById(String id) throws SQLException {
        return doWithBlock("SELECT * FROM person WHERE id=?",
                statement -> {
                    statement.setString(1, id);
                    return toPerson(statement.executeQuery());
                }
        );
    }

    public int update(String id, String name) throws SQLException {
        return doWithBlock("UPDATE PERSON SET name=? WHERE id=?",
                statement -> {
                    statement.setString(1, id);
                    statement.setString(2, name);
                    return statement.executeUpdate();
                }
        );
    }

    public int insert(String name, String age) throws SQLException {
        return doWithBlock("INSERT INTO person VALUES(?,?)",
                statement -> {
                    statement.setString(1, name);
                    statement.setString(2, age);
                    return statement.executeUpdate();
                }
        );
    }


    private static <T> T doWithBlock(String query, ThrowableLambda<?, SQLException> block) throws SQLException {
        Connection connection=null;
        PreparedStatement statement=null;
        try{
            connection = DriverManager.getConnection(DB_ENDPOINT);
            statement = connection.prepareStatement(query);
            return (T) block.execute(statement);
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
        return null;//TODO: the mapping here
    }

    private static List<Person> toPersonList(ResultSet rs){
        return null;//TODO: the mapping here
    }
}
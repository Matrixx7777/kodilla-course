package com.kodilla.jdbc;


import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DbManagerTestSuite {

    @Test
    void testConnect() throws SQLException {
        //Given
        //When
        DbManager dbManager = DbManager.getInstance();
        //Then
        assertNotNull(dbManager.getConnection());
    }

    @Test
    void testSelectUsers() throws SQLException {
        //Given
        DbManager dbManager = DbManager.getInstance();

        //When
        String sqlQuery = "SELECT * FROM USERS";
        Statement statement = dbManager.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sqlQuery);

        //Then
        int counter = 0;
        while (rs.next()) {
            System.out.println(rs.getInt("ID") + ", " +
                    rs.getString("FIRSTNAME") + ", " +
                    rs.getString("LASTNAME"));
            counter++;
        }
        rs.close();
        statement.close();
        assertEquals(5, counter);
    }

    @Test
    void testSelectUsersAndPosts() throws SQLException{
        //Given
        DbManager dbManager = DbManager.getInstance();

        //When
        String sqlQuery = "SELECT P.USER_ID, U.FIRSTNAME, U.LASTNAME, COUNT(*) AS POSTS_NUMBER\n" +
                "FROM POSTS P JOIN USERS U ON P.USER_ID = U.ID\n" +
                "GROUP BY P.USER_ID\n" +
                "HAVING COUNT(*) > 1;";

//        //OR
//        String sqlQuery2 ="SELECT P.USER_ID, U.FIRSTNAME, U.LASTNAME, COUNT(*) AS POSTS_NUMBER\n" +
//                "FROM POSTS P, USERS U\n" +
//                "WHERE P.USER_ID = U.ID\n" +
//                "GROUP BY P.USER_ID\n" +
//                "HAVING COUNT(*) > 1;";

        Statement statement = dbManager.getConnection().createStatement();
        ResultSet rs = statement.executeQuery(sqlQuery);

        //Then
        int counter = 0;
        while(rs.next()){
            System.out.println(rs.getInt("USER_ID") + ", " +
                    rs.getString("FIRSTNAME") + ", " +
                    rs.getString("LASTNAME") + ", " +
                    rs.getInt("POSTS_NUMBER"));
            counter++;
        }
        rs.close();
        statement.close();
        assertEquals(2, counter);

    }
}

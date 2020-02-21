package com.kgisl.brokermvc;
import java.sql.*;
public class Test {
    public static void main(String[] args) {
        try{
            Class.forName("com.sqlite.Driver");
        Connection con = DriverManager.getConnection("jdbc:sqlite:resources/test.db");

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
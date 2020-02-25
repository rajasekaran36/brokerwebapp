package com.kgisl.brokermvc;

import java.sql.*;
import javax.sql.DataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

public class Test {
    public static void main(String[] args) {

        MysqlDataSource ds = new MysqlDataSource();
        ds.setUser("root");
        ds.setPassword("");
        ds.setServerName("localhost");
        ds.setPort(3306);
        ds.setDatabaseName("broker");

        try {
            Connection con = ds.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from users");

            while(rs.next()){
                System.out.println(rs.getString(1)+"   "+rs.getString(2));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       /*  int i = 0;
        String a = "<a href='#?curstart="+i+"'>"+i+"</a>"; */
    
    }
}
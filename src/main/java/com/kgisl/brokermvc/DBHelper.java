package com.kgisl.brokermvc;

import java.sql.*;

public class DBHelper {
    public static void pushBrokerage(Brokerage entity) {
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Loaded");
        } catch (ClassNotFoundException cnf) {
            System.out.println("Problem with Loding JDBC Driver");
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "");
            System.out.println("Connection Made");
        } catch (SQLException e) {
            System.out.println("Problem with Connection");
        }
        try {
            stmt = con.prepareStatement(
                    "INSERT INTO `broker`.`brokerages` (`id`, `uccCode`, `tradeDateAndTime`, `scrip`, `tradeType`, `quantity`, `price`, `amount`, `brokerage`, `gst`, `st`, `stampDuty`, `transactionCharge`, `sebiCharges`, `netamount`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            System.out.println("Update Done");
        } catch (SQLException e) {
            System.out.println("Problem with Statements");
        }

        try {
            stmt.setInt(1, entity.getId());
            stmt.setString(2, entity.getUccCode());
            //stmt.setDate(3, Date.valueOf(entity.getTradeDateAndTime().toLocalDate()));
            stmt.setTimestamp(3, Timestamp.valueOf(entity.getTradeDateAndTime()));
            stmt.setString(4,entity.getScrip());
            stmt.setString(5,entity.getTradeType());
            stmt.setInt(6,entity.getQuantity());
            stmt.setDouble(7, entity.getPrice());
            stmt.setDouble(8, entity.getAmount());
            stmt.setDouble(9, entity.getBrokerage());
            stmt.setDouble(10, entity.getGst());
            stmt.setDouble(11, entity.getSt());
            stmt.setDouble(12, entity.getStampDuty());
            stmt.setDouble(13, entity.getTransactionCharge());
            stmt.setDouble(14, entity.getSebiCharges());
            stmt.setDouble(15, entity.getNetamount());
        } catch (SQLException e) {
            System.out.println("Problem with values");
        }
        try{
        int i = stmt.executeUpdate();
        System.out.println(i+"Row Updated");
        }catch(SQLException se){
            System.out.println("Problem with update");
        }
        
        try{
            con.close();
        }catch(SQLException e){
            System.out.println("Problem with closing connection");
        }
    }
}
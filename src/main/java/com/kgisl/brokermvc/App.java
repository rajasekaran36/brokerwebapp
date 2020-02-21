package com.kgisl.brokermvc;
import java.time.LocalDate;
import java.util.Scanner;


public class App{
    public static void main(String[] args) {
        TradeDaoImpl t = new TradeDaoImpl();
        t.loadTradeInfoFromFile("resources/tradefile-2.csv");
        BrokerageDaoImpl b = new BrokerageDaoImpl();
        b.processTrades(t.getallTrades());
        b.printAllContracts();
        System.out.println("Top Buyed Scrip: "+b.topBuyScrip());
        System.out.println("Top Soled Scrip: "+b.topSellScrip());
        System.out.println("Total Brokerage: "+b.totalBrokerage());
        System.out.println("Total Brokerage on 2015-03-27: "+b.totalBrokerageForDay(LocalDate.parse("2015-03-27")));
        System.out.println("Highest Brokerage Collected On: "+b.getHighestBrokerageCollectionDay());
        System.out.println("Total Brokerage For Client (CITIBNPPARIA): "+b.getTotalBrokerageForAPerson("CITIBNPPARIA"));
        System.out.println("Highest Brokerage Paid by Client (CITIBNPPARIA) on: "+b.getHighestBrokeragePaidDayByClient("CITIBNPPARIA"));

        System.out.println("Daily Traders: "+b.getDailyTradingUsers().toString());

        System.out.println("Average Buying Price of AXISBANK:"+b.getAverageBuyingPricePerScrip("AXISBANK"));
        System.out.println("Average Selling Price of AXISBANK:"+b.getAverageSellingPricePerScrip("AXISBANK"));
        //b.getAllBrokerages().forEach(brokerage->DBHelper.pushBrokerage(brokerage));
        Integer.parseInt("12d");
        //DBHelper.pushBrokerage(b.getAllBrokerages().get(0));
    }
}
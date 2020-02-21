package com.kgisl.brokermvc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TradeDaoImpl implements TradeDao {
    static Integer id;
    static List<Trade> trades;

    public TradeDaoImpl() {
        id = 0;
        trades = new ArrayList<Trade>();
    }
    public List<Trade> getallTrades() {
        return trades;
    }

    public List<Trade> getTradesByCustomerId(String customerID) {
        List<Trade> customerSpecificTrades = new ArrayList<Trade>();
        
        getallTrades().forEach((trade)->{
            if(trade.getUccCode().equals(customerID))
            customerSpecificTrades.add(trade);
        });
        
        return customerSpecificTrades;
    }

    public List<Trade> getTradesBySymbol(String symbol) {
        List<Trade> symbolSpecificTrades = new ArrayList<Trade>();
        
        getallTrades().forEach((trade)->{
            if(trade.getScrip().equals(symbol))
                symbolSpecificTrades.add(trade);
        });

        return symbolSpecificTrades;
    }

    public Boolean updateTradeInfo(Trade aTrade) {

        return null;
    }

    public Boolean deleteTradeInfo(Trade aTrade) {

        return null;
    }

    public Boolean loadTradeInfoFromFile(String filePath) {
        File tradeFile = new File(filePath);
        try {
            Scanner fileReader = new Scanner(tradeFile);
            fileReader.nextLine();
            while (fileReader.hasNextLine()){
                String[] info = fileReader.nextLine().split(",");
                TradeDaoImpl.id++;
                String uccCode = info[0];
                String dateAndTime = info[1];
                String scrip = info[2];
                String tradeType=info[3];
                Integer qty = Integer.parseInt(info[4]);
                Double rate = Double.parseDouble(info[5]);
                String stType = info[6];
                String gstType = info[7];
                trades.add(new Trade(id, uccCode, TradeUtils.convertToLocalDateTime(dateAndTime), scrip, tradeType, qty, rate, stType, gstType));
            }
            fileReader.close();
        } catch (FileNotFoundException fnf) {
            System.out.println("Cannot Find Trade File");
        }
        return true;
    }

    public List<Trade> getTrades(String customerID, String symbol){
        List<Trade> tradesSpeficToCustomerAndSymBol = new ArrayList<Trade>();
        trades.forEach((Trade t)->{
            if(t.getUccCode().equals(customerID)&&t.getScrip().equals(symbol))
                tradesSpeficToCustomerAndSymBol.add(t);
        });
        return tradesSpeficToCustomerAndSymBol;
    }

    public HashMap<String, List<Trade>> getTradeGroups(List<Trade> customerTrades) {
        HashMap<String, List<Trade>> map = new HashMap<String,List<Trade>>();
        customerTrades.forEach((Trade trade)->map.put(trade.getScrip(),null));
        map.forEach((key,trades)->{map.put(key,getTrades(customerTrades.get(0).getUccCode(),key));});
        return map;
    }

}
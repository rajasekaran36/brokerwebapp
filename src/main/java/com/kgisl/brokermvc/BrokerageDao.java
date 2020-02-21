package com.kgisl.brokermvc;

import java.time.LocalDate;
import java.util.List;

public interface BrokerageDao{
    public void processTrades(List<Trade> trades);
    public void displayBrokerages();
    public void printContract(Integer id);
    public void printAllContracts();
    public String topBuyScrip();
    public String topSellScrip();
    public Double totalBrokerage();
    public Double totalBrokerageForDay(LocalDate date);
    public LocalDate getHighestBrokerageCollectionDay();
    public Double getTotalBrokerageForAPerson(String uccCode);
    public LocalDate getHighestBrokeragePaidDayByClient(String uccCode);
    public List<String> getDailyTradingUsers();
    
}
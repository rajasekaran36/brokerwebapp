package com.kgisl.brokermvc;

import java.util.HashMap;
import java.util.List;

public interface TradeDao{
    public List<Trade> getallTrades();
    public List<Trade> getTradesByCustomerId(String customerID);
    public List<Trade> getTradesBySymbol(String symbol);
    public Boolean updateTradeInfo(Trade aTrade);
    public Boolean deleteTradeInfo(Trade aTrade);
    public Boolean loadTradeInfoFromFile(String filePath);
    public HashMap<String, List<Trade>> getTradeGroups(List<Trade> customerTrades);
}
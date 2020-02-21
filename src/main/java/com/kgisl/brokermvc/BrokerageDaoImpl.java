package com.kgisl.brokermvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Iterator;

public class BrokerageDaoImpl implements BrokerageDao{
    private List<Brokerage> brokerages=new ArrayList<Brokerage>();

    public void processTrades(List<Trade> trades){
        trades.forEach(trade->{
            brokerages.add(new Brokerage(trade.getId(),trade.getUccCode(),trade.getDateAndTime(),trade.getScrip(),trade.getTradeType(),trade.getQty(),trade.getRate()));
        });
        brokerages.forEach(
            brokerage->{

                brokerage.setAmount(brokerage.getPrice()*brokerage.getQuantity());
                brokerage.setBrokerage((brokerage.getAmount()/100)*0.35);
                brokerage.setSt((brokerage.getAmount()/100)*0.017);
                
                Trade aTrade = TradeUtils.getTrade(brokerage.getId());
                if(aTrade.getStType().equals("E")&&aTrade.getGstType().equals("E")){
                    
                    brokerage.setGst((brokerage.getBrokerage()*18)/100);
                    
                }
                else if(aTrade.getStType().equals("E")&&aTrade.getGstType().equals("I")){
                    
                    brokerage.setGst((brokerage.getBrokerage()*18)/118);
                   
                }
                else if(aTrade.getStType().equals("I")&&aTrade.getGstType().equals("E")){
                    
                    brokerage.setGst(((brokerage.getBrokerage()-brokerage.getSt())*18)/100);
                    
                }
                else if(aTrade.getStType().equals("I")&&aTrade.getGstType().equals("I")){
                    
                    brokerage.setGst(((brokerage.getBrokerage()-brokerage.getSt())*18)/118);
                    
                }
                
                brokerage.setStampDuty((brokerage.getAmount()/100)*0.005);
                brokerage.setTransactionCharge((brokerage.getAmount()/100)*0.00325);
                brokerage.setSebiCharges((brokerage.getAmount()/100)*0.002);
                if(aTrade.getTradeType().equals("B"))
                    brokerage.setNetamount(brokerage.getAmount()+(brokerage.getBrokerage()+brokerage.getGst()+brokerage.getSt()+brokerage.getStampDuty()+brokerage.getTransactionCharge()+brokerage.getSebiCharges()));
                else if(aTrade.getTradeType().equals("S"))
                    brokerage.setNetamount(brokerage.getAmount()-(brokerage.getBrokerage()+brokerage.getGst()+brokerage.getSt()+brokerage.getStampDuty()+brokerage.getTransactionCharge()+brokerage.getSebiCharges()));
            }
        );
    }

    public static String rounder(Double val){
         return String.format("%.2f", val);  
    }
    
    public List<Brokerage> getAllBrokeragesMin(){
        List<Brokerage> minbro = new ArrayList<Brokerage>();
        for(Brokerage x:getAllBrokerages()){
            x.setAmount(Double.parseDouble(rounder(x.getAmount())));
            x.setBrokerage(Double.parseDouble(rounder(x.getBrokerage())));
            x.setGst(Double.parseDouble(rounder(x.getGst())));
            x.setSt(Double.parseDouble(rounder(x.getSt())));
            x.setStampDuty(Double.parseDouble(rounder(x.getStampDuty())));
            x.setTransactionCharge(Double.parseDouble(rounder(x.getTransactionCharge())));
            x.setSebiCharges(Double.parseDouble(rounder(x.getSebiCharges())));
            x.setNetamount(Double.parseDouble(rounder(x.getNetamount())));
            minbro.add(x);
        }
        return minbro;
    }
    public void displayBrokerages(){
        brokerages.forEach(brokerage->System.out.println(brokerage.toString()));
    }
    public void printContract(Integer id){
       
        System.out.println("*******************************************************");
        System.out.println("------------------- XYZ Broking----------------------- ");
        System.out.println("*******************************************************");
        for(Brokerage brokerage:brokerages){
            if(brokerage.getId()==id){
                System.out.println("ID                     :   "+brokerage.getId());
                System.out.println("UCCCODE                :   "+brokerage.getUccCode());
                System.out.println("DATE&TIME              :   "+brokerage.getTradeDateAndTime());
                System.out.println("SCRIP                  :   "+brokerage.getScrip());
                System.out.println("TRADE TYPE             :   "+brokerage.getTradeType());
                System.out.println("QUANTITY               :   "+brokerage.getQuantity());
                System.out.println("PRICE                  :   Rs."+brokerage.getPrice());
                System.out.println("MARKET AMOUNT          :   Rs."+brokerage.getAmount());
                System.out.println("BROKERAGE AMOUNT       :   Rs."+brokerage.getBrokerage());
                System.out.println("GST                    :   Rs."+brokerage.getGst());
                System.out.println("STT                    :   Rs."+brokerage.getSt());
                System.out.println("STAMP DUTY             :   Rs."+brokerage.getStampDuty());
                System.out.println("TRANSACTION CHARGES    :   Rs."+brokerage.getTransactionCharge());
                System.out.println("SEBI CHARGES           :   Rs."+brokerage.getSebiCharges());
                System.out.println("NET AMOUNT             :   Rs."+brokerage.getNetamount());
            }
        }

        System.out.println("*******************************************************");
    }

    public void printAllContracts(){
        brokerages.forEach(brokerage->{
            printContract(brokerage.getId());
        });
    }
    public List<Brokerage> getAllBrokerages(){
        return brokerages;
    }

    public Brokerage getBrokerageById(Integer id){
        for(Brokerage x: getAllBrokerages()){
            if(x.getId()==id){
                return x;
            }
        }
        return null;
    }
    public String topBuyScrip() {
        String topBuyScrip = "";
        HashMap<String, Integer> countmap = new HashMap<String, Integer>();
        List<String> buyedScrips = brokerages.stream().filter(brok->brok.getTradeType().equals("B")).map(Brokerage::getScrip).collect(Collectors.toList());

        buyedScrips.forEach(scrip->{
            Integer count = countmap.get(scrip);
            if(count!=null){
                count = count + 1;
                countmap.put(scrip, count);
            }
            else
                countmap.put(scrip, 1);
        });
        Map.Entry<String,Integer> currentEntry = countmap.entrySet().iterator().next();
        Iterator<Entry<String, Integer>> itr = countmap.entrySet().iterator();
        Integer max = currentEntry.getValue();
        topBuyScrip = currentEntry.getKey();

        while(itr.hasNext()){
            currentEntry = itr.next();
            if(max<currentEntry.getValue()){
                max = currentEntry.getValue();
                topBuyScrip = currentEntry.getKey();
            }
        }
        
        return topBuyScrip;
    }

    public String topSellScrip() {
        String topSellScrip = "";
        HashMap<String, Integer> countmap = new HashMap<String, Integer>();
        List<String> soledScrips = brokerages.stream().filter(brok->brok.getTradeType().equals("S")).map(Brokerage::getScrip).collect(Collectors.toList());

        soledScrips.forEach(scrip->{
            Integer count = countmap.get(scrip);
            if(count!=null){
                count = count + 1;
                countmap.put(scrip, count);
            }
            else
                countmap.put(scrip, 1);
        });
        Map.Entry<String,Integer> currentEntry = countmap.entrySet().iterator().next();
        Iterator<Entry<String,Integer>> itr = countmap.entrySet().iterator();
        Integer max = currentEntry.getValue();
        topSellScrip = currentEntry.getKey();

        while(itr.hasNext()){
            currentEntry = itr.next();
            if(max<currentEntry.getValue()){
                max = currentEntry.getValue();
                topSellScrip = currentEntry.getKey();
            }
        }
        
        return topSellScrip;
    }

    public Double totalBrokerage(){
        return brokerages.stream().map(Brokerage::getBrokerage).mapToDouble(x->x).sum();
    }

    public Double totalBrokerageForDay(LocalDate date){
        Double totalBrokofDay = 0.0;
        List<Brokerage> brokeragesForADay = new ArrayList<Brokerage>();
        brokerages.forEach(brokerage->{
            //System.out.println(brokerage.getTradeDateAndTime().toLocalDate()+"---"+date);
            if(brokerage.getTradeDateAndTime().toLocalDate().equals(date)){
                brokeragesForADay.add(brokerage);
            }
        });
        totalBrokofDay = brokeragesForADay.stream().map(Brokerage::getBrokerage).mapToDouble(b->b).sum();
        return totalBrokofDay;
    }

    public LocalDate getHighestBrokerageCollectionDay(){
        LocalDate highestBrokerageCollectedOn = LocalDate.now();
        List<LocalDate> dates = brokerages.stream().map(Brokerage::getTradeDateAndTime).distinct().map(dt->dt.toLocalDate()).collect(Collectors.toList());
        Double maxCollection = 0.0;
        for(LocalDate curDate:dates){
            Double curDayCollection = totalBrokerageForDay(curDate);
            if(maxCollection<curDayCollection){
                maxCollection = curDayCollection;
                highestBrokerageCollectedOn = curDate;
            }
        }
        return highestBrokerageCollectedOn;
    }

    public Double getTotalBrokerageForAPerson(String uccCode){
        Double totalBrokerageForAPerson = 0.0;
        totalBrokerageForAPerson = brokerages.stream().filter(brokerage->brokerage.getUccCode().equals(uccCode)).map(Brokerage::getBrokerage).mapToDouble(b->b).sum();
        return totalBrokerageForAPerson;
    }

    public LocalDate getHighestBrokeragePaidDayByClient(String uccCode){
        LocalDate highestBrokeragePaidDayByClient = LocalDate.now();
        List<LocalDate> dates = brokerages.stream().filter(brokerage->brokerage.getUccCode().equals(uccCode)).map(Brokerage::getTradeDateAndTime).distinct().map(dt->dt.toLocalDate()).collect(Collectors.toList());
        Double maxPaymentValue = 0.0;
        for(LocalDate curDate:dates){
            Double curDayPaymentValue = totalBrokerageForDay(curDate);
            if(maxPaymentValue<curDayPaymentValue){
                maxPaymentValue = curDayPaymentValue;
                highestBrokeragePaidDayByClient = curDate;
            }
        }
        return highestBrokeragePaidDayByClient;
    }

    public List<String> getDailyTradingUsers(){
        List<String> dailyTraders = new ArrayList<String>();
        HashMap<String,Boolean> dailyTradersMapping = new HashMap<String, Boolean>();
        HashSet<String> tradersSet = new HashSet<String>();
        tradersSet.addAll(brokerages.stream().map(Brokerage::getUccCode).distinct().collect(Collectors.toSet()));
        tradersSet.forEach(code->dailyTradersMapping.put(code,false));

        List<LocalDate> stockMarketActiveDates = brokerages.stream().map(Brokerage::getTradeDateAndTime).map(dt->dt.toLocalDate()).distinct().collect(Collectors.toList());
        
        for(String uccCode:tradersSet){
            stockMarketActiveDates.forEach(date->{
                List<LocalDate> uccTradedDates = brokerages.stream().filter(brokerage->brokerage.getUccCode().equals(uccCode)).map(Brokerage::getTradeDateAndTime).map(dt->dt.toLocalDate()).distinct().collect(Collectors.toList());
                //System.out.println(uccTradedDates.toString()+"---"+stockMarketActiveDates.toString());
                if(uccTradedDates.equals(stockMarketActiveDates))
                    dailyTradersMapping.put(uccCode, true);
            });
        }
        
        dailyTradersMapping.forEach((k,v)->{
            if(v==true){
                dailyTraders.add(k);
            }
        });
        return dailyTraders;
    }

    public Double getAverageBuyingPricePerScrip(String scrip){
        Double averageBuyingPricePerScrip = 0.0;
        List<Brokerage> buyScrips = brokerages.stream().filter(brokerage->brokerage.getScrip().equals(scrip)).filter(brokerage->brokerage.getTradeType().equals(("B"))).collect(Collectors.toList());
        averageBuyingPricePerScrip = buyScrips.stream().map(Brokerage::getPrice).mapToDouble(p->p).sum()/buyScrips.size();
        return averageBuyingPricePerScrip;
    }

    public Double getAverageSellingPricePerScrip(String scrip){
        Double averageSellingPricePerScrip = 0.0;
        List<Brokerage> sellScrips = brokerages.stream().filter(brokerage->brokerage.getScrip().equals(scrip)).filter(brokerage->brokerage.getTradeType().equals(("S"))).collect(Collectors.toList());
        averageSellingPricePerScrip = sellScrips.stream().map(Brokerage::getPrice).mapToDouble(p->p).sum()/sellScrips.size();
        return averageSellingPricePerScrip;
    }
}
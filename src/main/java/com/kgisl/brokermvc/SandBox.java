package com.kgisl.brokermvc;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SandBox
 
{
    public static void displayTrades(List<Trade> trades){
        for(Trade x:trades){
            System.out.println(x.toString());
        }
    }
    public static Stream<Trade> tradesToStream(List<Trade> list){
        Stream.Builder<Trade> sb = Stream.builder();
        list.forEach(t->sb.add(t));
        return sb.build();
    }
    public static void main( String[] args )
    {
        TradeDao td = new TradeDaoImpl();
        td.loadTradeInfoFromFile("resources/tradefile-1.csv");

        List<Trade> trades = td.getallTrades();
        Consumer pcon = System.out::println;

        /* Stream.Builder<String> sb = Stream.builder();
        trades.forEach(t->sb.add(t.getCustomerId()));
        Stream<String> cusids = sb.build(); */

        Stream<Trade> allTradesStream = tradesToStream(td.getallTrades());
        String customerId="CITIBNPPARIA";
        String sym = "INFY";
        /* 
        Stream<Trade> customerTrades = allTradesStream.filter(t->t.getCustomerId().equals(customerId)).collect(Collectors.toList()).stream();
        Stream<Trade> cusSymbol = allTradesStream.filter(t->t.getCustomerId().equals(customerId)).filter(s->s.getSymbol().equals(sym)).collect(Collectors.toList()).stream();
         */
        
        
        List<Trade> customerTrades = allTradesStream.filter(t->t.getId().equals(customerId)).collect(Collectors.toList());

        List<Trade> cusSymbol = customerTrades.stream().filter(t->t.getScrip().equals(sym)).collect(Collectors.toList());

        displayTrades(cusSymbol);
        
        if(TradeUtils.isHaveDiffrentRate(cusSymbol)){
            Double totalAmount = cusSymbol.stream().map(amo->amo.getQty()*amo.getRate()).reduce((t,u)->t+u).get();
            Integer totalQty = cusSymbol.stream().map(t->t.getQty()).reduce((t,u)->t+u).get();
            Double weightedAvg = totalAmount/totalQty;
            System.out.println("Total Qty:"+totalQty);
            System.out.println("Weighted Avg:"+weightedAvg);
            totalAmount = cusSymbol.stream().map(amo->amo.getQty()*weightedAvg).reduce((t,u)->t+u).get();
            System.out.println("Total Amount:"+totalAmount);
        }
        else{
             
        } 


        //trs.map(Trade::getSymbol)
        //List<String> dss = cusids.distinct().collect(Collectors.toList());
        
        /* //CITIBNPPARIA
        HashMap<String,List<Trade>> groups = td.getTradeGroups(td.getTradesByCustomerId("CITIBNPPARIA"));

        groups.forEach((key,trades)->{displayTrades(trades);});

        /* for(String key:groups.keySet()){
            displayTrades(groups.get(key));
        } */

        
    }
}

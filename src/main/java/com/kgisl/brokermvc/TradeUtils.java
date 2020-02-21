package com.kgisl.brokermvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public class TradeUtils{
    
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

    public static Boolean isHaveDiffrentRate(List<Trade> list){
        Stream.Builder<Trade> sb = Stream.builder();
        list.forEach(t->sb.add(t));
        if(sb.build().map(Trade::getRate).distinct().count()>1)
            return true;
        else
            return false;
    }

    public static LocalDateTime convertToLocalDateTime(String data){
        String[] raw =data.split(" ");
        String[] date = raw[0].split("/");
        String[] time = raw[1].split(":");
        for(int i=0;i<2;i++)
            if(date[i].length()<2)
                date[i]="0"+date[i];
        
        Integer[] datei = Stream.of(date).map(Integer::parseInt).toArray(Integer[]::new);
        Integer[] timei = Stream.of(time).map(Integer::parseInt).toArray(Integer[]::new);
        
        return LocalDateTime.of(datei[2],datei[0],datei[1], timei[0], timei[1]);
    }
    public static Trade getTrade(Integer id){
        Trade result = null;
        for(Trade trade:TradeDaoImpl.trades){
            if(trade.getId()==id)
                result=trade;
        }        
        return result;
    }
}
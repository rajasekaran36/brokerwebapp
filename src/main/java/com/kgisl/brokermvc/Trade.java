package com.kgisl.brokermvc;

import java.time.LocalDateTime;

public class Trade{
    Integer id;
    String uccCode;
    LocalDateTime dateAndTime;
    String scrip;
    String tradeType; //Single Letter String (Buy->B or Sell->S)
    Integer qty;
    Double rate;
    String stType; //Single Letter String (Exclusive->E or Inclusive->I)
    String gstType; //Single Letter String (Exclusive->E or Inclusive->I)


    public Trade() {
    }


    public Trade(Integer id, String uccCode, LocalDateTime dateAndTime, String scrip, String tradeType, Integer qty, Double rate, String stType, String gstType) {
        this.id = id;
        this.uccCode = uccCode;
        this.dateAndTime = dateAndTime;
        this.scrip = scrip;
        this.tradeType = tradeType;
        this.qty = qty;
        this.rate = rate;
        this.stType = stType;
        this.gstType = gstType;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUccCode() {
        return this.uccCode;
    }

    public void setUccCode(String uccCode) {
        this.uccCode = uccCode;
    }

    public LocalDateTime getDateAndTime() {
        return this.dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getScrip() {
        return this.scrip;
    }

    public void setScrip(String scrip) {
        this.scrip = scrip;
    }

    public String getTradeType() {
        return this.tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public Integer getQty() {
        return this.qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Double getRate() {
        return this.rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getStType() {
        return this.stType;
    }

    public void setStType(String stType) {
        this.stType = stType;
    }

    public String getGstType() {
        return this.gstType;
    }

    public void setGstType(String gstType) {
        this.gstType = gstType;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", uccCode='" + getUccCode() + "'" +
            ", dateAndTime='" + getDateAndTime() + "'" +
            ", scrip='" + getScrip() + "'" +
            ", tradeType='" + getTradeType() + "'" +
            ", qty='" + getQty() + "'" +
            ", rate='" + getRate() + "'" +
            ", stType='" + getStType() + "'" +
            ", gstType='" + getGstType() + "'" +
            "}";
        
}
}
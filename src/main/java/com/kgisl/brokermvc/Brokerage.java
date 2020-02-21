package com.kgisl.brokermvc;

import java.time.LocalDateTime;

public class Brokerage {
    private Integer id;
    private String uccCode;
    private LocalDateTime tradeDateAndTime;
    private String scrip;
    private String tradeType; //Single Letter String (Buy->B or Sell->D)
    private Integer quantity;
    private Double price;
    private Double amount;
    private Double brokerage;
    private Double gst;
    private Double st;
    private Double stampDuty;
    private Double transactionCharge;
    private Double sebiCharges;
    private Double netamount;

    public Double getStampDuty() {
        return this.stampDuty;
    }

    public void setStampDuty(Double stampDuty) {
        this.stampDuty = stampDuty;
    }

    public Double getTransactionCharge() {
        return this.transactionCharge;
    }

    public void setTransactionCharge(Double transactionCharge) {
        this.transactionCharge = transactionCharge;
    }

    public Double getSebiCharges() {
        return this.sebiCharges;
    }

    public void setSebiCharges(Double sebiCharges) {
        this.sebiCharges = sebiCharges;
    }
    

    public Brokerage() {
    }

    public Brokerage(Integer id, String uccCode, LocalDateTime tradeDateAndTime, String scrip, String tradeType, Integer quantity, Double price) {
        this.id = id;
        this.uccCode = uccCode;
        this.tradeDateAndTime = tradeDateAndTime;
        this.scrip = scrip;
        this.tradeType = tradeType;
        this.quantity = quantity;
        this.price = price;
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

    public LocalDateTime getTradeDateAndTime() {
        return this.tradeDateAndTime;
    }

    public void setTradeDateAndTime(LocalDateTime tradeDateAndTime) {
        this.tradeDateAndTime = tradeDateAndTime;
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

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getBrokerage() {
        return this.brokerage;
    }

    public void setBrokerage(Double brokerage) {
        this.brokerage = brokerage;
    }

    public Double getGst() {
        return this.gst;
    }

    public void setGst(Double gst) {
        this.gst = gst;
    }

    public Double getSt() {
        return this.st;
    }

    public void setSt(Double st) {
        this.st = st;
    }

    public Double getNetamount() {
        return this.netamount;
    }

    public void setNetamount(Double netamount) {
        this.netamount = netamount;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", uccCode='" + getUccCode() + "'" +
            ", tradeDateAndTime='" + getTradeDateAndTime() + "'" +
            ", scrip='" + getScrip() + "'" +
            ", tradeType='" + getTradeType() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", price='" + getPrice() + "'" +
            ", amount='" + getAmount() + "'" +
            ", brokerage='" + getBrokerage() + "'" +
            ", gst='" + getGst() + "'" +
            ", st='" + getSt() + "'" +
            ", stampDuty='" + getStampDuty() + "'" +
            ", transactionCharge='" + getTransactionCharge() + "'" +
            ", sebiCharges='" + getSebiCharges() + "'" +
            ", netamount='" + getNetamount() + "'" +
            "}";
    }


}

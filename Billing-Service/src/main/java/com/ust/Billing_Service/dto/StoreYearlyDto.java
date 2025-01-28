package com.ust.Billing_Service.dto;

public class StoreYearlyDto {
    private String month;
    private double totalSale;
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(double totalSale) {
        this.totalSale = totalSale;
    }

    public StoreYearlyDto() {
    }

    public StoreYearlyDto(String month, double totalSale) {
        this.month = month;
        this.totalSale = totalSale;
    }

}

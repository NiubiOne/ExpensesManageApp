package com.test.ExpensesManage;



public class Total {
    private double total;
    private String currency;

    public Total(String currency,double total) {
        this.currency = currency;
        this.total = total;
    }

    public double getTotal() {
        return total;
    }


    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Total{" +
                "total=" + total +
                ", currency='" + currency + '\'' +
                '}';
    }
}

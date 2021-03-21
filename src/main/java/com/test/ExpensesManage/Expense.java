package com.test.ExpensesManage;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;



@Entity
public class Expense {
    private @Id @GeneratedValue long  id;
    private Date date;
    private double amount;
    private String currency;
    private String product;

    public Expense() {
    }

    public Expense(Date date, double amount, String currency, String product) {
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.product = product;
    }


    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getProduct() {
        return product;
    }

    public long getId() {
        return id;
    }



    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", name='" + product + '\'' +
                '}';
    }


}

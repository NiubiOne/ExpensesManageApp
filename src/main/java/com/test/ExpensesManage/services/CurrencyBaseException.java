package com.test.ExpensesManage.services;

public class CurrencyBaseException extends RuntimeException {

    public CurrencyBaseException() {
        super("Wrong currency base");
    }
}

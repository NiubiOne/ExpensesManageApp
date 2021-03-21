package com.test.ExpensesManage;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {
    Expense expense;

    @BeforeEach
    void setUp() {
        expense = new Expense(new Date(2021-3-20),4,"USD","Apples");
    }

    @Test
    void getDate() {
        assertEquals(new Date(2021-3-20),expense.getDate());
    }

    @Test
    void getAmount() {
        assertEquals(4.0,expense.getAmount());
    }

    @Test
    void getCurrency() {
        assertEquals("USD",expense.getCurrency());
    }

    @Test
    void getProduct() {
        assertEquals("Apples",expense.getProduct());
    }
}
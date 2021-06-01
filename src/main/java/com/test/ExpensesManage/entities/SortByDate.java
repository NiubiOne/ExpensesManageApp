package com.test.ExpensesManage.entities;

import com.test.ExpensesManage.entities.Expenses;

import java.util.Comparator;

public class SortByDate implements Comparator<Expenses> {
    @Override
    public int compare(Expenses o1, Expenses o2) {
        return o1.getDate().compareTo(o2.getDate());

    }
}

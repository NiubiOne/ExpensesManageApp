package com.test.ExpensesManage;

import java.util.Comparator;

public class SortByDate implements Comparator<Expense> {
    @Override
    public int compare(Expense o1, Expense o2) {
        return o1.getDate().compareTo(o2.getDate());

    }
}

package com.test.ExpensesManage.repositories;


import com.test.ExpensesManage.entities.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;


public interface ExpensesRepository extends JpaRepository<Expenses,Long> {

    List<Expenses> findByDate(Date date);
    @Transactional
    List<Expenses> deleteByDate(Date date);
}

package com.test.ExpensesManage;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;


public interface Repository extends JpaRepository<Expense,Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Expense e WHERE e.date = :date")
    void deleteByDate(@Param("date") Date date);


    List<Expense> findByDate(Date date);
}

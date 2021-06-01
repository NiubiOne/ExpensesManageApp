package com.test.ExpensesManage;


import com.test.ExpensesManage.entities.Expenses;
import com.test.ExpensesManage.services.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.*;

@RestController
public class ExpensesController {

    @Autowired
    ExpensesService expensesService;

    @RequestMapping
    public String hi(){
        return "Hello";
    }

    @GetMapping("/expenses")
    public  List<Expenses> getAll(){
      return  expensesService.getAll();
    }
    @GetMapping("/expenses/grouped")
    public  Map<Date,List<Expenses>> getAllGroupedByDate(){
        return  expensesService.getAllGroupedByDate();
    }

    @DeleteMapping("/expenses")
    public ResponseEntity<String> deleteByDate(@RequestParam("date") Date date) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Successfully deleted\n"+ expensesService.deleteByDate(date));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Wrong date format"+e.getMessage());
        }
    }
     @PostMapping("/expenses")
    public ResponseEntity<String> newExpense(@RequestBody Expenses expenses){
//         {
//                 "date": "2021-04-25",
//                 "amount": 2.85,
//                 "currency": "USD",
//                 "product": "Yogurt"
//         }
         try {
             expensesService.addExpenses(expenses);
             return ResponseEntity.status(HttpStatus.OK)
                     .body("Successfully added\n"+expenses.toString());
         } catch (Exception e) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                     .body("Something went wrong");
         }
    }
    @PutMapping("/expenses/{id}")
    public void replaceExpense(@RequestBody Expenses newExpenses, @PathVariable Long id) {
        expensesService.changeExpensesById(newExpenses,id);
    }


    @GetMapping("/total")
    public ResponseEntity<String> calculateTotal(@RequestParam("base") String base) {
        try {
            double sum = expensesService.getSumInCurrency(base);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Total =" + Math.round(sum*100.0)/100.0 + ", currency:" + base);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Wrong currency: "+base);
        }
    }

}

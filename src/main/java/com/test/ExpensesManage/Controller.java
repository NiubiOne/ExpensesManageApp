package com.test.ExpensesManage;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.*;

@RestController
public class Controller {
    private List<Expense> list;
    private final Repository repository;

    public Controller(Repository repository) {
        this.repository = repository;
    }


    @RequestMapping
    public String hi(){
        return "Hello";
    }

    @GetMapping("/expenses")
    public Map<String, List<Expense>> getAll(){
        Set<Date> dates = new TreeSet<>();
        Map<String,List<Expense>> map = new LinkedHashMap<>();
         list = repository.findAll();
         for (Expense i:list){
             dates.add(i.getDate());
         }
         for (Date j:dates){
             map.put(j.toString(),repository.findByDate(j));
         }

        return map;

    }

    @DeleteMapping("/expenses")

    public List<Expense> deleteByDate(@RequestParam("date") Date date){
         list = repository.findByDate(date);
         repository.deleteByDate(date);
        System.out.println("Deleted: "+list.toString());
        return list;
    }
     @PostMapping("/expenses")
    public Expense newExpense(@RequestBody Expense expense){
         System.out.println("Saved: "+expense);
//         {
//                 "date": "2021-04-25",
//                 "amount": 2.85,
//                 "currency": "USD",
//                 "product": "Yogurt"
//         }
        return repository.save(expense);
    }


    @GetMapping("/total")
    public ResponseEntity<Total> calculateTotal(@RequestParam("base") String base){
    Converter c = Converter.getConverter();
    if (c.isBaseValid(base)){
        list = repository.findAll();
        double amount=0;
        for (Expense i:list){
            amount += c.getInEUR(i.getCurrency(),i.getAmount());
        }
        amount= Math.round(c.convertTo(base,amount)*100.0)/100.0;
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Total(base,amount));
    }else {
        return ResponseEntity.badRequest()
                .body(new Total("Wrong currency",0));
    }

    }


}

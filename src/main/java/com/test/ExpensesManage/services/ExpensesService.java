package com.test.ExpensesManage.services;

import com.test.ExpensesManage.entities.Currency;
import com.test.ExpensesManage.entities.Expenses;
import com.test.ExpensesManage.repositories.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.util.*;

@Service
public class ExpensesService {

    @Autowired
    ExpensesRepository expensesRepository;
    @Autowired
    RestTemplate restTemplate;


    public List<Expenses> getAll(){
        return expensesRepository.findAll();
    }

    public Map<Date, List<Expenses>> getAllGroupedByDate(){
        Set<Date> dates = new TreeSet<>();
        Map<Date,List<Expenses>> map = new LinkedHashMap<>();
        List<Expenses> list = expensesRepository.findAll();
        for (Expenses i:list){
            dates.add(i.getDate());
        }
        for (Date j:dates){
            map.put(j, expensesRepository.findByDate(j));
        }
        return map;
    }
    public List<Expenses> deleteByDate(Date date){
       return expensesRepository.deleteByDate(date);
    }

    public List<Expenses> findByDate(Date date){
        return expensesRepository.findByDate(date);
    }
    public void addExpenses(Expenses expenses){
        expensesRepository.save(expenses);
    }

    public void changeExpensesById(Expenses newExpenses, Long id){
        expensesRepository.findById(id)
                .map(expenses -> {
                    expenses.setDate(newExpenses.getDate());
                    expenses.setAmount(newExpenses.getAmount());
                    expenses.setCurrency(newExpenses.getCurrency());
                    expenses.setProduct(newExpenses.getProduct());
                    return expensesRepository.save(newExpenses);
                })
                .orElseGet(() -> {
                    newExpenses.setId(id);
                    return expensesRepository.save(newExpenses);
                });
    }



    public Double getSumInCurrency(String base) throws CurrencyBaseException {
        Currency currency = restTemplate.
                getForObject("http://data.fixer.io/api/latest?access_key=e065f65f54f42d660ebe9611a36528ba&format=1&base=EUR",Currency.class);
        List<Expenses> expenses = expensesRepository.findAll();
        double sum;
        if (currency.getRates().containsKey(base)){
            // i can get only rates in EUR from free version of API, so firs need to find total in EUR
            double sumInEUR=0.0;
            for (Expenses i:expenses){
                sumInEUR += i.getAmount() / currency.getRates().get(i.getCurrency());
            }

            sum = sumInEUR * currency.getRates().get(base);
       }
       else throw new CurrencyBaseException();

        return sum;
    }

}

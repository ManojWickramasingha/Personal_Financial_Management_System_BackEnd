package edu.icet.pim.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.pim.entity.ExpenseEntity;
import edu.icet.pim.model.Expense;
import edu.icet.pim.repository.ExpenseRepository;
import edu.icet.pim.service.ExpenseService;
import edu.icet.pim.util.PaymentMethod;
import edu.icet.pim.util.RecurringOption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

    final ExpenseRepository repository;
    final ObjectMapper mapper;
    @Override
    public Boolean addExpense(Expense expense) {
        repository.save(mapper.convertValue(expense, ExpenseEntity.class));
        return true;
    }

    @Override
    public List<Expense> searchByDate(LocalDate date) {
        List<ExpenseEntity> expenseEntities = repository.findByCreateDate(date);
        List<Expense> expenseList = new ArrayList<>();
        for(ExpenseEntity expense : expenseEntities){
            expenseList.add(mapper.convertValue(expense, Expense.class));
        }
        return expenseList;
    }

    @Override
    public List<Expense> searchByCategory(String category) {
        List<ExpenseEntity> expenseEntities = repository.findByCategory(category);
        List<Expense> expenseList = new ArrayList<>();
        for(ExpenseEntity expense : expenseEntities){
            expenseList.add(mapper.convertValue(expense, Expense.class));
        }
        return expenseList;
    }

    @Override
    public List<Expense> searchByPaymentMethod(PaymentMethod paymentMethod) {
        List<ExpenseEntity> expenseEntity = repository.findByPaymentMethod(paymentMethod);
        List<Expense> expenseList = new ArrayList<>();
        for(ExpenseEntity expense : expenseEntity){
            expenseList.add(mapper.convertValue(expense, Expense.class));
        }
        return expenseList;
    }

    @Override
    public List<Expense> searchByCurrency(String currency) {
        List<ExpenseEntity> expenseEntities = repository.findByCurrency(currency);
        List<Expense> expenseList = new ArrayList<>();
        for(ExpenseEntity expense : expenseEntities){
            expenseList.add(mapper.convertValue(expense, Expense.class));
        }
        return expenseList;
    }

    @Override
    public List<Expense> searchByRecurringOption(RecurringOption recurringOption) {
        List<ExpenseEntity> expenseEntities = repository.findByRecurringOption(recurringOption);
        List<Expense> expenseList = new ArrayList<>();
        for(ExpenseEntity expenseEntity : expenseEntities){
            expenseList.add(mapper.convertValue(expenseEntity, Expense.class));
        }
        return expenseList;
    }

    @Override
    public Boolean deleteById(Integer id) {
        repository.deleteById(id);
        return true;
    }
}
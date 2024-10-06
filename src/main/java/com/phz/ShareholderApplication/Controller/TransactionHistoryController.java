package com.phz.ShareholderApplication.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phz.ShareholderApplication.Model.TransactionHistory;
import com.phz.ShareholderApplication.Service.TransactionHistoryService;

@RestController
@RequestMapping("/transactionHistory")
public class TransactionHistoryController {
    @Autowired
    private TransactionHistoryService transactionHistoryService;

    
    @PostMapping("/add")
    public String addTransactionHistory(@RequestBody TransactionHistory transactionHistory) {
        transactionHistoryService.saveTransactionHistory(transactionHistory);
        return "New transaction is added";
    }

    
    @GetMapping("/all")
    public List<TransactionHistory> getAllTransactionHistories() {
        return transactionHistoryService.getAllTransactionHistories();
    }

   
    @GetMapping("/{id}")
    public Optional<TransactionHistory> getTransactionHistoryById(@PathVariable Long id) {
        return transactionHistoryService.getTransactionHistoryById(id);
    }

}

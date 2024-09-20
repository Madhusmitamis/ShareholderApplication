package com.phz.ShareholderApplication.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    // POST request to add a new TransactionHistory
    @PostMapping("/add")
    public String addTransactionHistory(@RequestBody TransactionHistory transactionHistory) {
        transactionHistoryService.saveTransactionHistory(transactionHistory);
        return "New transaction is added";
    }

    // GET request to get all TransactionHistories
    @GetMapping("/all")
    public List<TransactionHistory> getAllTransactionHistories() {
        return transactionHistoryService.getAllTransactionHistories();
    }

    // GET request to get a specific TransactionHistory by ID
    @GetMapping("/{id}")
    public Optional<TransactionHistory> getTransactionHistoryById(@PathVariable Long id) {
        return transactionHistoryService.getTransactionHistoryById(id);
    }

    // DELETE request to delete a TransactionHistory by ID
    // @DeleteMapping("/delete/{id}")
    // public String deleteTransactionHistory(@PathVariable Long id) {
    // transactionHistoryService.deleteTransactionHistory(id);
    // return "Transaction with id " + id + " has been deleted";
    // }
}

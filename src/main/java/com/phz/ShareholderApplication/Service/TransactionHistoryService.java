package com.phz.ShareholderApplication.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.phz.ShareholderApplication.Model.TransactionHistory;
import com.phz.ShareholderApplication.Repository.TransactionHistoryRepository;

@Service
public class TransactionHistoryService {
    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    // Method to save a new TransactionHistory
    public TransactionHistory saveTransactionHistory(TransactionHistory transactionHistory) {
        return transactionHistoryRepository.save(transactionHistory);
    }

    // Method to get all TransactionHistories
    public List<TransactionHistory> getAllTransactionHistories() {
        return transactionHistoryRepository.findAll();
    }

    // Method to get a TransactionHistory by ID
    public Optional<TransactionHistory> getTransactionHistoryById(Long id) {
        return transactionHistoryRepository.findById(id);
    }

}

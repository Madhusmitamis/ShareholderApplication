package com.phz.ShareholderApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.phz.ShareholderApplication.Model.TransactionHistory;



public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

}

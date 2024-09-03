package com.phz.ShareholderApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.phz.ShareholderApplication.Model.TransactionHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

}

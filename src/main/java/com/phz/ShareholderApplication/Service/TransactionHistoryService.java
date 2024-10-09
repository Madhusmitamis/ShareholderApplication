package com.phz.ShareholderApplication.Service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.phz.ShareholderApplication.Model.Owner;
import com.phz.ShareholderApplication.Model.Shareholder;
import com.phz.ShareholderApplication.Model.TransactionHistory;
import com.phz.ShareholderApplication.Repository.OwnerRepository;
import com.phz.ShareholderApplication.Repository.ShareholderRepository;
import com.phz.ShareholderApplication.Repository.TransactionHistoryRepository;

@Service
public class TransactionHistoryService {
    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ShareholderRepository shareholderRepository;

    public TransactionHistory saveTransactionHistory(TransactionHistory transactionHistory) {
        try {
            System.out.println("Processing transaction: " + transactionHistory);
            if (transactionHistory.getBuyerType().equals("Owner")) {
                System.out.println("Fetching Owner for Buyer ID: " + transactionHistory.getBuyerId());
                Owner buyer = ownerRepository.findById(transactionHistory.getBuyerId())
                        .orElseThrow(() -> new IllegalArgumentException("Buyer not found"));
                updateShareQuantity(buyer, transactionHistory.getShareQty().intValue(), true);
                ownerRepository.save(buyer);
            } else {
                System.out.println("Fetching Shareholder for Buyer ID: " + transactionHistory.getBuyerId());
                Shareholder buyer = shareholderRepository.findById(transactionHistory.getBuyerId())
                        .orElseThrow(() -> new IllegalArgumentException("Buyer not found"));
                updateShareQuantity(buyer, transactionHistory.getShareQty().intValue(), true);
                shareholderRepository.save(buyer);
                updateOwnerTableIfApplicable(buyer);
            }

            if (transactionHistory.getSellerType().equals("Owner")) {
                System.out.println("Fetching Owner for Seller ID: " + transactionHistory.getSellerId());
                Owner seller = ownerRepository.findById(transactionHistory.getSellerId())
                        .orElseThrow(() -> new IllegalArgumentException("Seller not found"));
                updateShareQuantity(seller, transactionHistory.getShareQty().intValue(), false);
                ownerRepository.save(seller);
            } else {
                System.out.println("Fetching Shareholder for Seller ID: " + transactionHistory.getSellerId());
                Shareholder seller = shareholderRepository.findById(transactionHistory.getSellerId())
                        .orElseThrow(() -> new IllegalArgumentException("Seller not found"));
                updateShareQuantity(seller, transactionHistory.getShareQty().intValue(), false);
                shareholderRepository.save(seller);
                updateOwnerTableIfApplicable(seller);
            }

            return transactionHistoryRepository.save(transactionHistory);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException("Failed to process transaction. " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage());
        }
    }

    private void updateShareQuantity(Owner owner, int shareQtyChange, boolean isAdding) {
        int change = isAdding ? shareQtyChange : -shareQtyChange;
        owner.setShareQty(owner.getShareQty() + change);
        owner.setSharePercentage(calculateSharePercentage(owner.getShareQty()));
    }

    private void updateShareQuantity(Shareholder shareholder, int shareQtyChange, boolean isAdding) {
        int change = isAdding ? shareQtyChange : -shareQtyChange;
        shareholder.setShareQty(shareholder.getShareQty() + change);
        shareholder.setSharePercentage(calculateSharePercentage(shareholder.getShareQty()));
    }

    private double calculateSharePercentage(int shareQty) {
        return ((double) shareQty / 4070921) * 100;
    }

    public List<TransactionHistory> getAllTransactionHistories() {
        return transactionHistoryRepository.findAll();
    }

    public Optional<TransactionHistory> getTransactionHistoryById(Long id) {
        return transactionHistoryRepository.findById(id);
    }

   public void updateOwnerTableIfApplicable(Shareholder shareholder) {
    Owner existingOwner = ownerRepository.findById(shareholder.getId()).orElse(null);
    if (shareholder.getSharePercentage() >= 25.0) {
        if (existingOwner == null) {
            Owner newOwner = new Owner(
                shareholder.getId(),
                shareholder.getName(),
                shareholder.getShareQty(),
                shareholder.getSharePercentage()
            );
            System.out.println("Promoting to Owner: " + newOwner.getName());
            ownerRepository.save(newOwner);
        } else {
            existingOwner.setShareQty(shareholder.getShareQty());
            existingOwner.setSharePercentage(shareholder.getSharePercentage());
            System.out.println("Updating existing Owner: " + existingOwner.getName());
            ownerRepository.save(existingOwner);
        }

        if (shareholderRepository.existsById(shareholder.getId())) {
            System.out.println("Removing from Shareholders: " + shareholder.getName());
            shareholderRepository.deleteById(shareholder.getId());
        }
    } else {
    if (existingOwner != null) {
        if (shareholder.getShareQty() > 0) {
            System.out.println("Demoting to Shareholder and removing from Owners: " + shareholder.getName());
            ownerRepository.deleteById(shareholder.getId());
            Shareholder existingShareholder = shareholderRepository.findById(shareholder.getId()).orElse(null);
            if (existingShareholder != null) {
                existingShareholder.setShareQty(shareholder.getShareQty());
                existingShareholder.setSharePercentage(shareholder.getSharePercentage());
                System.out.println("Updating existing Shareholder: " + existingShareholder.getName());
                shareholderRepository.save(existingShareholder);
            } else if (shareholder.getShareQty() > 0) {
                System.out.println("Adding to Shareholders: " + shareholder.getName());
                shareholderRepository.save(shareholder);
            }
        }
    }
}


   }
}

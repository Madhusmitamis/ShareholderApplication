package com.phz.ShareholderApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phz.ShareholderApplication.Model.Owner;
import com.phz.ShareholderApplication.Model.Shareholder;
import com.phz.ShareholderApplication.Repository.OwnerRepository;
import com.phz.ShareholderApplication.Repository.ShareholderRepository;

import java.util.List;

@Service
public class ShareholderService {

    @Autowired
    private ShareholderRepository shareholderRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    public Shareholder addShareholder(Shareholder shareholder) {
        Shareholder savedShareholder = shareholderRepository.save(shareholder);
        updateOwnerTableIfApplicable(savedShareholder);
        return savedShareholder;

    }

    public void saveShareholder(Shareholder shareholder) {
        shareholderRepository.save(shareholder);
        updateOwnerTableIfApplicable(shareholder);
    }

    public List<Shareholder> getAllShareholders() {
        List<Shareholder> shareholders = shareholderRepository.findAll();
        return shareholders;
    }

    public boolean updateShareholder(Long id, Shareholder shareholder) {
        return shareholderRepository.findById(id).map(existingShareholder -> {

            existingShareholder.setName(shareholder.getName());
            existingShareholder.setEncryptedSsn(shareholder.getEncryptedSsn());
            existingShareholder.setAddress(shareholder.getAddress());
            existingShareholder.setEmail(shareholder.getEmail());
            existingShareholder.setSharePercentage(shareholder.getSharePercentage());
            shareholderRepository.save(existingShareholder);

            updateOwnerTableIfApplicable(existingShareholder);

            return true; // Indicate success
        }).orElse(false); // If shareholder not found, return false
    }

    // private void updateOwnerTableIfApplicable(Shareholder shareholder) {
    // if (shareholder.getSharePercentage() > 25.0) {
    // // Add shareholder to Owner table
    // Owner owner = new Owner(shareholder.getId(), shareholder.getName(),
    // shareholder.getSharePercentage());
    // ownerRepository.save(owner);
    // } else {
    // // Remove shareholder from Owner table if share percentage <= 25%
    // ownerRepository.deleteById(shareholder.getId());
    // }

    public boolean checkIfEmailExists(String email) {
        return shareholderRepository.existsByEmail(email);
    }

    private void updateOwnerTableIfApplicable(Shareholder shareholder) {
        if (shareholder.getSharePercentage() > 25.0) {
            // Check for existing owner
            Owner existingOwner = ownerRepository.findById(shareholder.getId()).orElse(null);

            if (existingOwner == null) {
                // Create a new owner if none exists
                Owner newOwner = new Owner(shareholder.getId(), shareholder.getName(), 0,
                        shareholder.getSharePercentage());
                ownerRepository.save(newOwner);
            } else {
                // Update existing owner's share percentage
                existingOwner.setSharePercentage(shareholder.getSharePercentage());
                ownerRepository.save(existingOwner);
            }
        } else {
            // Remove shareholder from Owner table if share percentage <= 25%
            if (ownerRepository.existsById(shareholder.getId())) {
                ownerRepository.deleteById(shareholder.getId());
            }
        }
    }

}

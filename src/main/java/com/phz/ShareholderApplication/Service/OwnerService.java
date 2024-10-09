package com.phz.ShareholderApplication.Service;

import com.phz.ShareholderApplication.Model.Owner;
import com.phz.ShareholderApplication.Model.Shareholder;
import com.phz.ShareholderApplication.Repository.OwnerRepository;
import com.phz.ShareholderApplication.Repository.ShareholderRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private ShareholderRepository shareholderRepository;

    
    public Owner saveOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

   
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    
    public Owner getOwnerById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    public void updateOwner(Long id, Owner owner) {
        Owner existingOwner = ownerRepository.findById(id).orElse(null);
        if (existingOwner != null) {
            existingOwner.setName(owner.getName());
            existingOwner.setShareQty(owner.getShareQty());
            existingOwner.setShareQty(owner.getShareQty());
            ownerRepository.save(existingOwner);
        }
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
                ownerRepository.save(newOwner);
            } else {
                
                existingOwner.setSharePercentage(shareholder.getSharePercentage());
                existingOwner.setShareQty(shareholder.getShareQty());
                ownerRepository.save(existingOwner);
            }
        } else {
            
            if (existingOwner != null) {
                ownerRepository.deleteById(shareholder.getId());

                
                Shareholder existingShareholder = shareholderRepository.findById(shareholder.getId()).orElse(null);
                if (existingShareholder != null) {
                    existingShareholder.setShareQty(shareholder.getShareQty());
                    existingShareholder.setSharePercentage(shareholder.getSharePercentage());
                    shareholderRepository.save(existingShareholder);
                }
            }
        }
    }
}

  


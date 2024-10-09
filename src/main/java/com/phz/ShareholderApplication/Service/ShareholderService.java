package com.phz.ShareholderApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return savedShareholder;
    }

    public void saveShareholder(Shareholder shareholder) {
        shareholderRepository.save(shareholder);
        
    }

    public List<Shareholder> getAllShareholders() {
        List<Shareholder> shareholders = shareholderRepository.findAll();
        System.out.println("Retrieved shareholders: " + shareholders);
        return shareholders;
    }

    public boolean updateShareholder(Long id, Shareholder shareholder) {
        return shareholderRepository.findById(id).map(existingShareholder -> {

            existingShareholder.setName(shareholder.getName());
            existingShareholder.setEncryptedSsn(shareholder.getEncryptedSsn());
            existingShareholder.setAddress(shareholder.getAddress());
            existingShareholder.setEmail(shareholder.getEmail());
            existingShareholder.setShareQty(shareholder.getShareQty());

            shareholderRepository.save(existingShareholder);

            return true; 
        }).orElse(false); 
    }

    public boolean checkIfEmailExists(String email) {
        return shareholderRepository.existsByEmail(email);
    }

}

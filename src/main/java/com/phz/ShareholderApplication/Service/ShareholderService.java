package com.phz.ShareholderApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phz.ShareholderApplication.Model.Shareholder;
import com.phz.ShareholderApplication.Repository.ShareholderRepository;
import java.util.List;

@Service
public class ShareholderService {

    @Autowired
    private ShareholderRepository shareholderRepository;

    public void saveShareholder(Shareholder shareholder) {
        shareholderRepository.save(shareholder);
    }

    public List<Shareholder> getAllShareholders() {
        return shareholderRepository.findAll();
    }
}

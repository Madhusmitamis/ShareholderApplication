package com.phz.ShareholderApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.phz.ShareholderApplication.Model.Shareholder;
import com.phz.ShareholderApplication.Repository.ShareholderRepository;

public class ShareholderServiceImpl implements ShareholderService {
    @Autowired
    private ShareholderRepository shareholderRepository;

    @Override
    public Shareholder saveShareholder(Shareholder shareholder) {
        return shareholderRepository.save(shareholder);
    }

}

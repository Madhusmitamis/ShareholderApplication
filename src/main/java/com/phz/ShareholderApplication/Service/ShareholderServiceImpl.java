package com.phz.ShareholderApplication.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phz.ShareholderApplication.Model.Shareholder;
import com.phz.ShareholderApplication.Repository.ShareholderRepository;

@Service
public class ShareholderServiceImpl implements ShareholderService {
    @Autowired
    private ShareholderRepository shareholderRepository;

    @Override
    public Shareholder saveShareholder(Shareholder shareholder) {
        return shareholderRepository.save(shareholder);
    }

}

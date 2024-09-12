package com.phz.ShareholderApplication.Service;

import com.phz.ShareholderApplication.Model.Owner;
import com.phz.ShareholderApplication.Repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner saveOwners(Owner owner) {
        return ownerRepository.save(owner);
    }
}

package com.phz.ShareholderApplication.Service;

import com.phz.ShareholderApplication.Model.Owner;
import com.phz.ShareholderApplication.Repository.OwnerRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    // Method to save a new Owner
    public Owner saveOwner(Owner owner) {
        return ownerRepository.save(owner);
    }

    // Method to get all Owners
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    // Method to find Owner by ID
    public Owner getOwnerById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    // Method to delete Owner by ID
    public void deleteOwner(Long id) {
        ownerRepository.deleteById(id);
    }
}

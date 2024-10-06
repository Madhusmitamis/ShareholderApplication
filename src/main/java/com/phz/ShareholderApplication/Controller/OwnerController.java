package com.phz.ShareholderApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phz.ShareholderApplication.Model.Owner;
import com.phz.ShareholderApplication.Service.OwnerService;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    
    @PostMapping("/add")
    public String addOwner(@RequestBody Owner owner) {
        ownerService.saveOwner(owner);
        return "New owner is added";
    }

   
    @GetMapping("/all")
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

   
    @GetMapping("/{id}")
    public Owner getOwnerById(@PathVariable Long id) {
        return ownerService.getOwnerById(id);
    }

    @PutMapping("/update/{id}")
    public String updateOwner(@PathVariable Long id, @RequestBody Owner owner) {
        ownerService.updateOwner(id, owner);
        return "Owner with id " + id + " has been updated";
    }

  
}

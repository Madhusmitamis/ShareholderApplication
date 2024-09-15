package com.phz.ShareholderApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.phz.ShareholderApplication.Model.Owner;
import com.phz.ShareholderApplication.Service.OwnerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

   
    @GetMapping("/view")
    public List<Owner> viewOwners() {
        return ownerService.getAllOwners();
    }

   
    @PostMapping("/add")
    public String addOwner(@RequestBody Owner owner) {
        ownerService.saveOwners(owner);
        return "New owner is added";
    }

}

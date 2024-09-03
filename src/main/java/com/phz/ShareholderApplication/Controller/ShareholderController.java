package com.phz.ShareholderApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phz.ShareholderApplication.Model.Shareholder;
import com.phz.ShareholderApplication.Service.ShareholderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.access.prepost.PreAuthorize;



@RestController
@RequestMapping("/shareholder")
public class ShareholderController {
    @Autowired
    private ShareholderService shareholderService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addShareholder(@RequestBody Shareholder shareholder) {
        shareholderService.saveShareholder(shareholder);
        return "New shareholder is added";
    }
    

}

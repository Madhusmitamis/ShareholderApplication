package com.phz.ShareholderApplication.Controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phz.ShareholderApplication.Model.Shareholder;
import com.phz.ShareholderApplication.Service.ShareholderService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/shareholder")
public class ShareholderController {
    @Autowired
    private ShareholderService shareholderService;

    @PostMapping("/add")
    public String addShareholder(@RequestBody Shareholder shareholder) {
        shareholderService.saveShareholder(shareholder);
        return "New shareholder is added";
    }

    @GetMapping("/all")
    public List<Shareholder> getAllShareholders() {
        return shareholderService.getAllShareholders();
    }

    
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateShareholder(@PathVariable Long id, @RequestBody Shareholder shareholder) {
       
        boolean isUpdated = shareholderService.updateShareholder(id, shareholder);
        if (isUpdated) {
            return ResponseEntity.ok("Shareholder updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shareholder not found");
        }
    }

    @GetMapping("/check-email/{email}")
    public ResponseEntity<Map<String, Boolean>> checkEmailExists(@PathVariable String email) {
        boolean exists = shareholderService.checkIfEmailExists(email);
        return ResponseEntity.ok(Collections.singletonMap("exists", exists));
    }

}

package com.phz.ShareholderApplication.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int shareQuantity;
    private double sharePercentage;

    public void setShareQuantity(int shareQuantity) {
        this.shareQuantity = shareQuantity;
        this.sharePercentage = ((double) shareQuantity / 4070921) * 100; 
    }

    public String getSharePercentage() {
        return String.format("%.2f", sharePercentage) + "%"; 
    }
}
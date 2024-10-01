package com.phz.ShareholderApplication.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Shareholder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @MaskData
    @JsonProperty("encryptedSsn")
    private String encryptedSsn;
    private int shareQty; 
    private double sharePercentage;
    private String address;
    private String email;
    
   public void setShareQty(int shareQty) {
        this.shareQty = shareQty;
        this.sharePercentage = ((double) shareQty / 4070921) * 100;
    }
    public String getFormattedSharePercentage() {
    return String.format("%.2f", sharePercentage) + "%"; 
}

}
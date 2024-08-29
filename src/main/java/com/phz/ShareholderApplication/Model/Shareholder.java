package com.phz.ShareholderApplication.Model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Shareholder extends AbstractPersistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String encryptedSsn;
    private String address;
    private String email;

    public void setSocialSecurityNumber(String ssn) {
        TextEncryptor encryptor = Encryptors.text("password", "5c0744940b5c369b");
        this.encryptedSsn = encryptor.encrypt(ssn);
    }

    public String getSocialSecurityNumber() {
        TextEncryptor encryptor = Encryptors.text("password", "5c0744940b5c369b");
        return encryptor.decrypt(this.encryptedSsn);
    }

}

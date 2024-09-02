package com.phz.ShareholderApplication.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "transaction_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String seller;

    @Column(nullable = false)
    private String buyer;

    @Column(nullable = false)
    private Integer numberOfShares;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Long to;

    @Column(nullable = false)
    private Long from;

    public TransactionHistory(String name, String seller, String buyer, Integer numberOfShares, Double price, Long from,
            Long to) {
        this.name = name;
        this.seller = seller;
        this.buyer = buyer;
        this.numberOfShares = numberOfShares;
        this.price = price;
        this.from = from;
        this.to = to;
    }

}

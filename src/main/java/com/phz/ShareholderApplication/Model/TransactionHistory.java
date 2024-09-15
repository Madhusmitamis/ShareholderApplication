package com.phz.ShareholderApplication.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

<<<<<<< HEAD

=======
import java.time.LocalDate;

import jakarta.persistence.Column;
>>>>>>> 8905ddff035b9a5de71716e0ac88e1988716f89a
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

    private String name;
    private LocalDate dateOfPurchase;
    private String seller;
    private String buyer;
    private Long shareQty;
    private Double price;
    private boolean taxReported;
    private Long shareNumberFrom;
    private Long shareNumberTo;
    private String note;

}

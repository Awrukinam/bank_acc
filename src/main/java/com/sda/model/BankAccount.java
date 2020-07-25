package com.sda.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Id;
import java.math.BigDecimal;

//@Value - robi to co 2 ponizsze adnotacje + dodadje slowo final do kazdego pola
@Getter
@AllArgsConstructor // robi  konstruktory ze wszystkimi argumentami
@NoArgsConstructor
public class BankAccount {

    @Id
    private String pesel;
    private BigDecimal value;
    @Column(name = "account Number")
    private String accountNumber;

    public void addMoney(BigDecimal transfer){
        value = this.value.add(transfer);
    };

}

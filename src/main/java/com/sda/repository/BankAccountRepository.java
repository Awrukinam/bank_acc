package com.sda.repository;

import com.sda.request.CreateBankAccountRequest;
import com.sda.model.BankAccount;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccountRepository {
    //klasa odpowiedzialna za laczenie sie z repozytorium

    boolean existsByPesel(@NonNull String pesel);

    BankAccount create(CreateBankAccountRequest request);

    List<BankAccount> findAll();

    void delete(@NonNull String pesel);

    void addIncome(@NonNull String pesel , BigDecimal income);


}

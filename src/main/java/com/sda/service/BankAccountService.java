package com.sda.service;

import com.sda.model.BankAccount;
import com.sda.repository.BankAccountRepository;
import com.sda.request.CreateBankAccountRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class BankAccountService {

    private BankAccountRepository repository;


    public Optional<BankAccount> createBankAccount(CreateBankAccountRequest request) {
        // sprawdzamy czy konto istnieje
        // jezeli istnieje to nie tworzymc, jezeli nie istnieje to utworzyc
        boolean exist = repository.existsByPesel(request.getPesel());
        if (exist) {
            return Optional.empty();
        }
        return Optional.of(repository.create(request));
    }

    public List<BankAccount> findAll() {
        return repository.findAll();
    }

    public boolean delete(String pesel){
        if (repository.existsByPesel(pesel)){
            repository.delete(pesel);
            return true;
        }
        return false;
    }
    public boolean addIncome(String pesel, BigDecimal income){
        if (repository.existsByPesel(pesel)){
            repository.addIncome(pesel, income);
            return true;
        }
        return false;
    }
}

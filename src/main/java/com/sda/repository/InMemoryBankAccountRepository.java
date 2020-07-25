package com.sda.repository;

import com.sda.model.BankAccount;
import com.sda.request.CreateBankAccountRequest;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.*;


public class InMemoryBankAccountRepository implements BankAccountRepository {

    // tworzymy singleton, zeby byla 1 i tylko 1 instancja tej klasy

    //SINGLETON ponizej
    private static InMemoryBankAccountRepository repository; //tworzymy obiekt InMemoryBankAccountRepositiory

    public static InMemoryBankAccountRepository getInstance() {
        // sprawdzamy czy obiekt jest nullem, jezeli jest to tworzymy nowy obiekt
        if (repository == null) {
            repository = new InMemoryBankAccountRepository();
        }
        // zwracamy obiekt
        return repository;
    }
    // SINGLETON powyzej
    // robiac SINGLETON musze miec prywatny konstruktor bezargumentowy

    private List<BankAccount> bankAccounts;

    private InMemoryBankAccountRepository() {
        bankAccounts = new ArrayList<>();
    }

    @Override
    public boolean existsByPesel(@NonNull String pesel) {

        /*        bankAccounts.stream()
                .filter(b -> b.getPesel().equalsIgnoreCase(pesel))
                .findAny()
                .isPresent();

             to jest to samo co kod ponizej. Stream rozbudowany, ponizej jest bardziej zwiezly stream
         */
        bankAccounts.stream()
                .anyMatch(b -> b.getPesel().equalsIgnoreCase(pesel));
        return false;
    }

    @Override
    public BankAccount create(CreateBankAccountRequest request) {
        Random random = new Random();
        Long accountNumber = random.nextLong();

        BankAccount bankAccount = new BankAccount(request.getPesel(), request.getInitValue(), accountNumber.toString());
        bankAccounts.add(bankAccount);

        return bankAccount;
    }

    @Override
    public List<BankAccount> findAll() {
        return Collections.unmodifiableList(bankAccounts);
    }

    @Override
    public void delete(@NonNull String pesel) {
        bankAccounts.stream()
                .filter(ba -> ba.getPesel().equalsIgnoreCase(pesel))
                .findFirst()
                .ifPresent(ba -> bankAccounts.remove(ba));

    }

    @Override
    public void addIncome(@NonNull String pesel, BigDecimal income ) {
        bankAccounts.stream()
                .filter(ba -> ba.getPesel().equalsIgnoreCase(pesel))
                .findFirst()
                .ifPresent(ba -> ba.addMoney(income));
    }
}

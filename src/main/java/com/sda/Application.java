package com.sda;

import com.sda.model.BankAccount;
import com.sda.repository.BankAccountRepository;
import com.sda.repository.InMemoryBankAccountRepository;
import com.sda.request.CreateBankAccountRequest;
import com.sda.service.BankAccountService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Application {
    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String THREE ="3" ;
    private static final String FOUR ="4" ;

    public static void main(String[] args) {
        BankAccountService service = new BankAccountService(InMemoryBankAccountRepository.getInstance());
        printMainMenu();
        Scanner scanner = new Scanner(System.in);
        String userChoice = scanner.nextLine();
        while (userChoice.equals(ONE) || userChoice.equals(TWO) || userChoice.equals(THREE) || userChoice.equals(FOUR)) {
            if (userChoice.equalsIgnoreCase(ONE)) {
                createBankAccount(service, scanner);

            } else if (userChoice.equalsIgnoreCase(TWO)) {
                printAllAccounts(service);

            } else if (userChoice.equalsIgnoreCase(THREE)) {
                deleteBankAccount(service, scanner);

            }else if (userChoice.equalsIgnoreCase(FOUR)){
                bookIncomingTransfer(service, scanner);
            }
            printMainMenu();
            userChoice = scanner.next();
        }
    }

    private static void createBankAccount(BankAccountService service, Scanner scanner) {
        System.out.println("Insert PESEL: ");
        String pesel = scanner.next();
        CreateBankAccountRequest request = new CreateBankAccountRequest(pesel, BigDecimal.ZERO);
        Optional<BankAccount> bankAccount = service.createBankAccount(request);
        if (bankAccount.isEmpty()) {
            System.out.println("Account with given pesel already exists!");
        } else {
            System.out.println("Done!");
        }


    }

    private static void deleteBankAccount(BankAccountService service, Scanner scanner) {
        System.out.println("please provide PESEL: ");
        String pesel = scanner.next();
        boolean canDelete = service.delete(pesel);

        if (canDelete){
            System.out.println("bank account deleted");
        }else System.out.println("no account with such pesel");

    }

  private static void bookIncomingTransfer(BankAccountService service, Scanner scanner){
        System.out.println("enter pesel: ");
        String pesel = scanner.next();
        System.out.println("how much you want to transfer?");
        BigDecimal income = scanner.nextBigDecimal();

        service.addIncome(pesel,income);

        }




    private static void printMainMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1. Create bank account - press 1");
        System.out.println("2. show all accounts - press 2");
        System.out.println("3. delete bank account - press 3");
        System.out.println("4. add income into bank account - press 4");
        System.out.println("6. if you want to exit, please press something else");
    }

    private static void printAllAccounts(BankAccountService service) {
        List<BankAccount> bankAccounts = service.findAll();
        bankAccounts.forEach(ba -> {
            System.out.println("Pesel: " + ba.getPesel());
            System.out.println("Account number: " + ba.getAccountNumber());
            System.out.println("Account value: " + ba.getValue());
            System.out.println();
        });
    }
}


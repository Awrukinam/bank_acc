//package com.sda.repository.SQLRepository;
//
//import com.sda.model.BankAccount;
//import com.sda.repository.BankAccountRepository;
//import com.sda.request.CreateBankAccountRequest;
//import lombok.NonNull;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//
//import java.util.List;
//import java.util.Random;
//
//public class MySQLBankRepositiory implements BankAccountRepository {
//
//
//    @Override
//    public boolean existsByPesel(@NonNull String pesel) {
//        SessionFactory sessionFactory = HibernateSessionFactoryCreator.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        Query queryOne = session.createQuery("SELECT b.pesel FROM BankAccount AS b");
//
//        transaction.commit();
//        session.close();
//        boolean isSame = queryOne.equals(pesel);
//        return isSame;
//    }
//
//    public BankAccount create(CreateBankAccountRequest request) {
//        Random random = new Random();
//        Long accountNumber = random.nextLong();
//
//        BankAccount bankAccount = new BankAccount(request.getPesel(), accountNumber.toString(),request.getInitValue());
//        SessionFactory sessionFactory = HibernateSessionFactoryCreator.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//        session.persist(bankAccount);
//     //   session.persist(new BankAccount(request.getPesel(), accountNumber.toString(),request.getInitValue()));
//
//        transaction.commit();
//        session.close();
//
//        return bankAccount;
//    }
//
//    @Override
//    public List<BankAccount> findAll() {
//
//        SessionFactory sessionFactory = HibernateSessionFactoryCreator.getSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//
//
//        transaction.commit();
//        session.close();
//
//
//        return null;
//    }
//}
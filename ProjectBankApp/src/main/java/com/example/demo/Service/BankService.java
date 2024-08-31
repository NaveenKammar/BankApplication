package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.BankAccount;
import com.example.demo.Entity.LoginHistory;
import com.example.demo.Entity.User;

import com.example.demo.Entity.Transaction;

public interface BankService {
	
	void createBankAccount(String username, String password, Double initialBalance);
    boolean loginUser(String username, String password);
    void logoutUser();
    BankAccount getCurrentUserBankAccount();
    void deposit(Double amount);
    void withdraw(Double amount);
    List<Transaction> getTransactionHistory();
    List<LoginHistory> getLoginHistory();
}

package com.example.demo.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.Transaction;

import com.example.demo.Entity.BankAccount;
import com.example.demo.Entity.LoginHistory;
import com.example.demo.Entity.User;
import com.example.demo.Repository.BankAccountRepository;
import com.example.demo.Repository.LoginHistoryRepository;
import com.example.demo.Repository.TransactionRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.BankService;


@Service
public class BankServiceImpl implements BankService {
	
	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private BankAccountRepository bankAccountRepository;

	    @Autowired
	    private TransactionRepository transactionRepository;

	    @Autowired
	    private LoginHistoryRepository loginHistoryRepository;

	    private User currentUser;

	    @Override
	    public void createBankAccount(String username, String password, Double initialBalance) {
	        User user = new User();
	        user.setUsername(username);
	        user.setPassword(new BCryptPasswordEncoder().encode(password));
	        
	        BankAccount account = new BankAccount();
	        account.setUser(user);
	        account.setBalance(initialBalance);
	        
	        user.setBankAccount(account);
	        userRepository.save(user);
	    }


	    @Override
	    public boolean loginUser(String username, String password) {
	        Optional<User> userOptional = userRepository.findByUsername(username);
	        if (userOptional.isPresent() && new BCryptPasswordEncoder().matches(password, userOptional.get().getPassword())) {
	            currentUser = userOptional.get();
	            LoginHistory loginHistory = new LoginHistory();
	            loginHistory.setUser(currentUser);
	            loginHistoryRepository.save(loginHistory);
	            return true;
	        }
	        return false;
	    }

	    @Override
	    public void logoutUser() {
	        List<LoginHistory> loginHistoryList = loginHistoryRepository.findByUser(currentUser);
	        if (!loginHistoryList.isEmpty()) {
	            LoginHistory loginHistory = loginHistoryList.get(0);
	            loginHistory.setLogoutTime(LocalDateTime.now());
	            loginHistoryRepository.save(loginHistory);
	        }
	        currentUser = null;
	    }

	    @Override
	    public BankAccount getCurrentUserBankAccount() {
	        return currentUser.getBankAccount();
	    }

	    @Override
	    public void deposit(Double amount) {
	        BankAccount account = currentUser.getBankAccount();
	        account.setBalance(account.getBalance() + amount);
	        bankAccountRepository.save(account);

	        Transaction transaction = new Transaction(amount, "DEPOSIT", account);
	        transactionRepository.save(transaction);
	    }

	    @Override
	    public void withdraw(Double amount) {
	        BankAccount account = currentUser.getBankAccount();
	        if (account.getBalance() >= amount) {
	            account.setBalance(account.getBalance() - amount);
	            bankAccountRepository.save(account);

	            Transaction transaction = new Transaction(amount, "WITHDRAWAL", account);
	            transactionRepository.save(transaction);
	        } else {
	            throw new IllegalArgumentException("Insufficient balance");
	        }
	    }

	    @Override
	    public List<Transaction> getTransactionHistory() {
	        return transactionRepository.findByBankAccount(currentUser.getBankAccount());
	    }


	    @Override
	    public List<LoginHistory> getLoginHistory() {
	        return loginHistoryRepository.findByUser(currentUser);
	    }
	}



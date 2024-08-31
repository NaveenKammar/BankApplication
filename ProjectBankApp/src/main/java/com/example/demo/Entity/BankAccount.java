package com.example.demo.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
@Entity
public class BankAccount {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private Double balance = 0.0;

	    @OneToOne
	    @JoinColumn(name = "user_id")
	    private User user;

	    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
	    private List<Transaction> transactions;

		public BankAccount(Long id, Double balance, User user, List<Transaction> transactions) {
			super();
			this.id = id;
			this.balance = balance;
			this.user = user;
			this.transactions = transactions;
		}

		public BankAccount() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Double getBalance() {
			return balance;
		}

		public void setBalance(Double balance) {
			this.balance = balance;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public List<Transaction> getTransactions() {
			return transactions;
		}

		public void setTransactions(List<Transaction> transactions) {
			this.transactions = transactions;
		}

		@Override
		public String toString() {
			return "BankAccount [id=" + id + ", balance=" + balance + ", user=" + user + ", transactions="
					+ transactions + "]";
		}
	    
}

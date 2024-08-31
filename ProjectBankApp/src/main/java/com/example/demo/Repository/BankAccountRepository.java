package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long>{

}

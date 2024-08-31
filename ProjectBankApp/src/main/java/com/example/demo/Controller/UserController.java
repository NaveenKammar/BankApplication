package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.LoginHistory;
import com.example.demo.Entity.User;
import com.example.demo.Service.BankService;


import com.example.demo.Entity.Transaction;


@Controller
public class UserController {
	

	    @Autowired
	    private BankService bankService;

	    @GetMapping("/signup")
	    public String showSignupForm(Model model) {
	        model.addAttribute("user", new User());
	        return "signup";
	    }

	    @PostMapping("/signup")
	    public String signup(@RequestParam String username, @RequestParam String password, @RequestParam Double initialBalance) {
	        bankService.createBankAccount(username, password, initialBalance); // Create account and user
	        return "redirect:/login"; // Redirect to login page after signup
	    }

	    @GetMapping("/login")
	    public String showLoginForm() {
	        return "login";
	    }

	    @PostMapping("/login")
	    public String login(@RequestParam String username, @RequestParam String password, Model model) {
	        if (bankService.loginUser(username, password)) {
	            return "redirect:/home";
	        } else {
	            model.addAttribute("error", "Invalid username or password");
	            return "login";
	        }
	    }

	    @GetMapping("/home")
	    public String home(Model model) {
	        model.addAttribute("bankAccount", bankService.getCurrentUserBankAccount());
	        return "home";
	    }

	    @PostMapping("/deposit")
	    public String deposit(@RequestParam Double amount) {
	        bankService.deposit(amount);
	        return "redirect:/home";
	    }

	    @PostMapping("/withdraw")
	    public String withdraw(@RequestParam Double amount) {
	        bankService.withdraw(amount);
	        return "redirect:/home";
	    }

	    @GetMapping("/transactionHistory")
	    public String viewTransactionHistory(Model model) {
	        List<Transaction> transactions = bankService.getTransactionHistory();
	        model.addAttribute("transactions", transactions);
	        return "transactionHistory";
	    }

	    @GetMapping("/loginHistory")
	    public String viewLoginHistory(Model model) {
	        List<LoginHistory> loginHistory = bankService.getLoginHistory();
	        model.addAttribute("loginHistory", loginHistory);
	        return "loginHistory";
	    }

	    @PostMapping("/logout")
	    public String logout() {
	        bankService.logoutUser();
	        return "redirect:/login";
	    }
	}



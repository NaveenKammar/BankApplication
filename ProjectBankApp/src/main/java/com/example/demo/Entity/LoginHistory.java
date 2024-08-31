package com.example.demo.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class LoginHistory {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime loginTime = LocalDateTime.now();
    private LocalDateTime logoutTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

	public LoginHistory(Long id, LocalDateTime loginTime, LocalDateTime logoutTime, User user) {
		super();
		this.id = id;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.user = user;
	}

	public LoginHistory() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

	public LocalDateTime getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(LocalDateTime logoutTime) {
		this.logoutTime = logoutTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "LoginHistory [id=" + id + ", loginTime=" + loginTime + ", logoutTime=" + logoutTime + ", user=" + user
				+ "]";
	}
    
}

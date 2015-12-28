package com.ibm.itacademy.attractions.web;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import com.ibm.itacademy.attractions.Account;
import com.ibm.itacademy.attractions.AccountDao;

@Named
@ViewScoped
public class EditorBean implements Serializable {
	@Inject
	private AccountDao accountDao;

	private Account account;

	private String password;
	private String login;

	private String password1;
	private String password2;
	private boolean isPassword;

	public String submit() {
		this.account = new Account();
		if (password1 == password2) {
			password = password1;
		}
		account.setLogin(login);
		account.setPassword(password1);
		accountDao.saveOrUpdate(account);

		return "login?faces-redirect=true";

	}

	public Account getEditor() {
		return account;
	}

	public void setEditor(Account Account) {
		this.account = Account;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}

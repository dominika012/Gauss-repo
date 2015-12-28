package com.ibm.itacademy.attractions.web;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.ibm.itacademy.attractions.Account;
import com.ibm.itacademy.attractions.auth.LoginService;

@Named
@SessionScoped
public class LoginBean implements Serializable {
	
	@Inject
	private transient LoginService loginService;
	
	private String login;
	
	private String password;
	
	private boolean isAuthenticated;
	
	public String authenticate() {
		if(loginService.login(login, password)) {
			
			isAuthenticated = true;
			password = null;
			System.out.println("logggggiiiiiiiiiiin  "+ login);
			return "attractions?faces-redirect=true";
		}
		else{
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, 
						"Access denied", 
						"Incorrect login or password"));
		
		return null;
		}
	}
	
	//FIXME
	public String logout(){
		/*System.out.println("login befoooooooooore  "+login);
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().invalidateSession();
		isAuthenticated = false;
		this.login=null;
		System.out.println("login aaaaaaaaaaaaafteeeeer  "+login);
		return "index?faces-redirect=true";
		*/return null;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isAuthenticated() {
		return isAuthenticated;
	}
	
	
	
	
	
	
	
	

}

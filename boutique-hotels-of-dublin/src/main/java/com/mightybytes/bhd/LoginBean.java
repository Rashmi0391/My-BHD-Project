package com.mightybytes.bhd;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


@ManagedBean
@SessionScoped
public class LoginBean {
	String userName, password;
	User user;
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}

	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getCurrentUser() {
		return user.getUserName();
	}
	
	public String validateUserLogin() {
		UserData userData = Helper.getBean("userData", UserData.class);
	    //if (userName.equalsIgnoreCase("root") && password.equals("admin")) {
	       // return "home.xhtml";
		if (userData.validateUser(userName, password)) {
			//return "home.xhtml";
			if(userData.isCustomerOrStaff(userName) == "customer") {
				user = userData.getUserByUsername(userName);
				setUser(user);
				System.out.println("user: " + user.toString());
				return "CustomerDashboard.html";
			} else if(userData.isCustomerOrStaff(userName) == "staff") {
				return "dashboard.html";
			} else {
				return "index.html";
			}
	    } else {
	    	System.out.println("here");
	    	FacesContext context=FacesContext.getCurrentInstance();
				FacesMessage errorMessage=
						new FacesMessage ("Invalid Username/Password Combination");
				errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(null,  errorMessage);
				return (null);
	    }
	}
	
	public String logout() {
		return "login.xhtml?faces-redirect=true";
	}

}

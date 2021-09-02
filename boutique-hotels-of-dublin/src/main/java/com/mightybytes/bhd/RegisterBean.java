package com.mightybytes.bhd;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean
@SessionScoped
public class RegisterBean {
	String name, userName, password, passwordRepeat;

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getpasswordRepeat() {
		return passwordRepeat;
	}

	public void setpasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}
	

	public void testAccess() {
		System.out.println("fsdfsdfsdfsdf");
	}
	
	public boolean isStaff() {
		System.out.println(userName.endsWith("@staff.bhd.com"));
		return userName.endsWith("@staff.bhd.com");

	}
	
	public boolean isValidPassword() {
		boolean lowercase = false;
		boolean uppercase = false;
		boolean number = false;
		boolean special = false;
		int i = 0;
		while (i < password.length() && !(lowercase && uppercase && number && special)) {
			char c = password.charAt(i);

			
			if (Character.isLowerCase(c)) {
					lowercase = true;

			}
			else if (Character.isUpperCase(c)) {
					uppercase = true;

			}
			else if (Character.isDigit(c)) {
					number = true;

			}
			else {
					special = true;
			}
			i++;
		}
		
		return (lowercase && uppercase && number && special);	
	}
	
	
	public String registerUserAccount() {
		UserData userData = Helper.getBean("userData", UserData.class);
		
		
		boolean staff = false, customer = false;
		if (isStaff()) {
			staff = true;
		}
		else {
			customer = true;
		}
		
		if (!password.equals(passwordRepeat)) {

	    	FacesContext context=FacesContext.getCurrentInstance();
				FacesMessage errorMessage=
						new FacesMessage ("Entered passwords do not match");
				errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(null,  errorMessage);
				return (null);
		}
		
		if (!isValidPassword()) {

	    	FacesContext context=FacesContext.getCurrentInstance();
				FacesMessage errorMessage=
						new FacesMessage ("Password must contain at least one of each of the following: Lowercase letter, Uppercase letter, Number, Special character (eg !, @, ?)");
				errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(null,  errorMessage);
				return (null);
		}
		
		if (!userData.registerUser(name, userName, password, customer, staff)) {

	    	FacesContext context=FacesContext.getCurrentInstance();
				FacesMessage errorMessage=
						new FacesMessage ("Username \"" + userName + "\" is already in use. Please choose a different username\"");
				errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(null,  errorMessage);
				return (null);

	    } else {

	    	//return "home.xhtml";
	    	if(userData.isCustomerOrStaff(userName) == "customer") {
	    		return "CustomerDashboard.html";
	    	} else if(userData.isCustomerOrStaff(userName) == "staff") {
	    		return "dashboard.html";
	    	} else {
	    		return "index.html";
	    	}
	    }
	}

	@Override
	public String toString() {
		return "RegisterBean [userName=" + userName + ", password=" + password + ", passwordRepeat=" + passwordRepeat
				+ "]";
	}
	
	

}


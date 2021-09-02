package com.mightybytes.bhd;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.util.ArrayList;
import java.util.HashMap;

@ManagedBean(name = "customerResetPassword")
@SessionScoped
public class CustomerResetPassword {

	private String oldPassword;
	private String newPassword;
	private String confirmNewPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmNewPassword) {
		this.confirmNewPassword = confirmNewPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String resetPassword() {

		boolean isChanged = false;
		UserData userData = Helper.getBean("userData", UserData.class);
		ArrayList<User> users = userData.getUsers();
		User targetUser=null;
		//find the target User
		for (User user : users) {
			System.out.println(user);			
			if(user.getUserName().equals("customer")) {
				targetUser = user;
				break;
			}
		}

        //if not found then send error , this should not happen
		if(targetUser == null){			
				System.out.println("Wrong User");
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage errorMessage = new FacesMessage("Internal Error");
				errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(null, errorMessage);
				return "login.html";
			
		}else{
			//if found then check the feilds 
			if ( targetUser.getPassword().equals(oldPassword)
					&& this.newPassword.equals(this.confirmNewPassword)) {
				targetUser.setPassword(newPassword);
				isChanged = true;
			}
			else {
				//show failure if the feilds doesn't match.
				System.out.println("Input not matched");
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage errorMessage = new FacesMessage("Input not matched");
				errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
				context.addMessage(null, errorMessage);
				return (null);
			}
		}
		
		if (isChanged) {
			// show success 
			System.out.println("Customer password changed");
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage errorMessage = new FacesMessage("Password has been changed");
			errorMessage.setSeverity(FacesMessage.SEVERITY_INFO);
			context.addMessage(null, errorMessage);
			return (null);
		}
		return "login.html";

	}

	@Override
	public String toString() {
		return "CustomerResetPassword [oldPassword=" + oldPassword + ", newPassword=" + newPassword
				+ ", confirmNewPassword=" + confirmNewPassword + "]";
	}
	
	

}

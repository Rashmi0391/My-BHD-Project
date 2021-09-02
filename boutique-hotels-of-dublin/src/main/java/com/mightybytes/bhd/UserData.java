package com.mightybytes.bhd;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class UserData {

	private ArrayList<User> users;

	@PostConstruct
	public void init() {
		users = new ArrayList<User>();
		User firstUser = new User("name", "staff", "password", false, true);
		users.add(firstUser);
		User secondUser = new User("name", "customer", "password", true, false);
		users.add(secondUser);

	}
	
	public ArrayList<User> getUsers() {
		return users;
	}
	
	public boolean registerUser(String name, String userName, String password, boolean isCustomer, boolean isStaff) {
		if (users.contains(getUserByUsername(userName))) {
			return false;
		}
		
		User user = new User(name, userName, password, isCustomer, isStaff);
		users.add(user);
		LoginBean loginBean = Helper.getBean("loginBean", LoginBean.class);
		loginBean.setUser(user);
		System.out.println(users);
		return true;
	}
	
	

	public boolean validateUser(String userName, String password) {
		boolean validUser=false;
		for (User user:users) {
			if((user.getUserName().equals(userName))&&(user.getPassword().equals(password) )){
				validUser=true;
				break;
			}
		}
		return validUser;
	}
	
	
	public String isCustomerOrStaff(String username) {
		String result = "";
		for(User user : users) {
			if(user.getUserName().equalsIgnoreCase(username) && user.getIsCustomer()) {
				result = "customer"; 
			} else if(user.getUserName().equalsIgnoreCase(username) && user.getIsStaff()) {
				result = "staff";
			}
		}
		System.out.println("Result is " + result);
		return result;
	}
	
	public User getUserByUsername(String userName) {
		for (User user : users) {
			if (user.getUserName().equals(userName)) {
				return user;
			}
		}
		return null;
	}
}


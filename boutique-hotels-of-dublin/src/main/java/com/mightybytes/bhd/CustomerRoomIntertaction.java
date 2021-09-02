package com.mightybytes.bhd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "customerRoomBean")
@SessionScoped
public class CustomerRoomIntertaction {
	User user;
	
	public ArrayList<LocalDate> displayBookings() {
		LoginBean loginBean = Helper.getBean("loginBean", LoginBean.class);
		user = loginBean.getUser();
		return this.user.getBookings();
	}
}

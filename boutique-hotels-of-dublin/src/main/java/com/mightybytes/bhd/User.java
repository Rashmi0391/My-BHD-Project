package com.mightybytes.bhd;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class User {
	private String name;
	private String userName;
	private String password;
	private boolean isCustomer;
	private boolean isStaff;
	private int roomNo;
	ArrayList<LocalDate> bookings;
	
	public User(String name, String userName, String password, boolean isCustomer, boolean isStaff) {
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.isCustomer = isCustomer;
		this.isStaff = isStaff;
		bookings = new ArrayList<LocalDate>();
	}
	
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
	
	public boolean getIsStaff() {
		return isStaff;
	}
	
	public void setIsStaff(boolean isStaff) {
		this.isStaff = isStaff;
	}
	
	public boolean getIsCustomer() {
		return isCustomer;
	}
	
	public void setIsCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}

	@Override
	public String toString() {
		return "User [name=" + name+ ", userName=" + userName + ", password=" + password + ", isCustomer=" + isCustomer + ", isStaff="
				+ isStaff + "]";
	}

	public ArrayList<LocalDate> getBookings() {
		return bookings;
	}

	public void setBooking(LocalDate bookingDate) {
		bookings.add(bookingDate);
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	
	
	

}

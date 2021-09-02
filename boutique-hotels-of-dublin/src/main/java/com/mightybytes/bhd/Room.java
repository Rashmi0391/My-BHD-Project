package com.mightybytes.bhd;


import java.util.Date;
import java.util.TreeMap;

/*
 * room associated with 0 or 1 Customers
 */

public class Room {
	private int roomNumber, noBeds, customerId;
	private String roomName;
	private boolean isDouble, isSingle, isTwin, isFamily, isOccupied;
	private double roomCost;
	private Date bookedFrom, bookedTo; 
	TreeMap<Integer, Boolean> bookings = new TreeMap<Integer, Boolean>();
	TreeMap<Integer, String> customerOccupation = new TreeMap<Integer, String>();
	
	public Room() {
		
	}
	
	Room(int roomNumber, int noBeds, String roomName, boolean isDouble, boolean isSingle, boolean isTwin, boolean isFamily, double roomCost, boolean isOccupied){
		this.roomNumber = roomNumber;
		this.noBeds = noBeds;
		this.roomName = roomName;
		this.isDouble = isDouble;
		this.isSingle = isSingle;
		this.isTwin = isTwin;
		this.isFamily = isFamily;
		this.roomCost = roomCost;
		this.isOccupied = isOccupied;
		for(int i = 1; i <= 365; i++) {
			bookings.put(i, null);
		}
		for(int i = 1; i <= 365; i++) {
			customerOccupation.put(i, null);
		}
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public int getNoBeds() {
		return noBeds;
	}

	public void setNoBeds(int noBeds) {
		this.noBeds = noBeds;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public boolean isDouble() {
		return isDouble;
	}

	public void setDouble(boolean isDouble) {
		this.isDouble = isDouble;
	}

	public boolean isSingle() {
		return isSingle;
	}

	public void setSingle(boolean isSingle) {
		this.isSingle = isSingle;
	}

	public boolean isTwin() {
		return isTwin;
	}

	public void setTwin(boolean isTwin) {
		this.isTwin = isTwin;
	}

	public boolean isFamily() {
		return isFamily;
	}

	public void setFamily(boolean isFamily) {
		this.isFamily = isFamily;
	}

	public double getRoomCost() {
		return roomCost;
	}

	public void setRoomCost(double roomCost) {
		this.roomCost = roomCost;
	}
	
	public boolean IsBooked() {
		return isOccupied;
	}

	public void setRoomOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}
	
	public int getRoomOccupiedByCustomerId() {
		return customerId;
	}

	public void setRoomOccupiedByCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Room [ roomNo= "+ roomNumber + ", noBeds= " + noBeds + ", customerId=" + customerId + ", roomName=" + roomName + ", isDouble=" + isDouble
				+ ", isSingle=" + isSingle + ", isTwin=" + isTwin + ", isFamily=" + isFamily + ", isOccupied="
				+ isOccupied + ", roomCost=" + roomCost + "]" + bookedFrom + bookedTo;
	}

	public Date getBookedFrom() {
		return bookedFrom;
	}

	public void setBookedFrom(Date bookedFrom) {
		this.bookedFrom = bookedFrom;
	}

	public Date getBookedTo() {
		return bookedTo;
	}

	public void setBookedTo(Date bookedTo) {
		this.bookedTo = bookedTo;
	}

	public String getBookedDates() {
		return bookings.toString();
	}

//	public void setBookedDates(ArrayList<Date> bookedDates) {
//		this.bookedDates = bookedDates;
//	}
	
	
}

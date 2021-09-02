package com.mightybytes.bhd;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.*;
import javax.annotation.PostConstruct;


@ManagedBean(name = "bookingDetailsBean")
@SessionScoped
public class BookingDetails {

	public String noOfAdults;
	public String noOfChild;
	public String suite;
	private Date checkInDate;
	private Date checkOutDate;
	public String[] roomServiceEnable;
	public String[] cuisine;
	private BookingDetails bookingDetails = null;

	public BookingDetails(String noOfAdults, String noOfChild, String suite, Date checkInDate, Date checkOutDate,
			String[] roomServiceEnable, String[] cuisine) {
		this.noOfAdults = noOfAdults;
		this.noOfChild = noOfChild;
		this.suite = suite;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.roomServiceEnable = roomServiceEnable;
		this.cuisine = cuisine;
	}
	
	public BookingDetails() {
		
	}

	@PostConstruct
	public void init() {
		String[] array = { "local", "" };
		String[] roomService = { "yes" };
		//BookingDetails bookingDetails = new BookingDetails("5", "4", "2", new Date(), new Date(), roomService, array);
		//populateData(bookingDetails);
	}


	private void populateData(BookingDetails bookingDetails) {
		this.noOfAdults = bookingDetails.getNoOfAdults();
		this.noOfChild = bookingDetails.getNoOfChild();
		this.suite = bookingDetails.getSuite();
		this.checkInDate = bookingDetails.getCheckInDate();
		this.checkOutDate = bookingDetails.getCheckOutDate();
		this.roomServiceEnable = bookingDetails.getRoomServiceEnable();
		this.cuisine = bookingDetails.getCuisine();
		RoomBooking roomBooking = Helper.getBean("roomBookingBean", RoomBooking.class);
		bookingDetails = new BookingDetails();
		roomBooking.bookRoom(noOfAdults, noOfChild, checkInDate, checkOutDate);
		
	}

	public BookingDetails getBookingDetails() {
		return bookingDetails;
	}

	public void setBookingDetails(BookingDetails bookingDetails) {
		this.bookingDetails = bookingDetails;
	}

	public String getNoOfAdults() {
		return noOfAdults;
	}

	public void setNoOfAdults(String noOfAdults) {
		this.noOfAdults = noOfAdults;
	}

	public String getNoOfChild() {
		return noOfChild;
	}

	public void setNoOfChild(String noOfChild) {
		this.noOfChild = noOfChild;
	}

	public String getSuite() {
		return suite;
	}

	public void setSuite(String suite) {
		this.suite = suite;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {	
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {		
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public String[] getRoomServiceEnable() {
		return roomServiceEnable;
	}

	public void setRoomServiceEnable(String[] roomServiceEnable) {
		this.roomServiceEnable = roomServiceEnable;
	}

	public String[] getCuisine() {
		return cuisine;
	}

	public void setCuisine(String[] cuisine) {
		this.cuisine = cuisine;
	}

	public void saveOrUpdate() {

		BookingDetails bookingDetails = new BookingDetails(this.noOfAdults, this.noOfChild, this.suite,
				this.checkInDate, this.checkOutDate, this.roomServiceEnable, this.cuisine);
		System.out.println("Saving the details " + bookingDetails);
		populateData(bookingDetails);
	}

	@Override
	public String toString() {
		return "BookingDetails [noOfAdults=" + noOfAdults + ", noOfChild=" + noOfChild + ", suite=" + suite
				+ ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", roomServiceEnable="
				+ roomServiceEnable + ", cuisine=" + Arrays.toString(cuisine);
	}
	
	public String resetPassword() {
		return "resetPassword.html";
	}
}

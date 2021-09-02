package com.mightybytes.bhd;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.time.*;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;;

@ManagedBean(name = "roomBookingBean", eager = true)
@SessionScoped
public class RoomBooking {

	ArrayList<Room> roomsAvailableInHotel = new ArrayList<>();
	HashMap<Room, User> roomBookings = new HashMap<Room,User>();
	
	int price, roomNo, customerRoomNo;
	

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	@PostConstruct
	public void init() {
		Room room1 = new Room(1, 5, null, true, false, false, false, 100.0, false);
		Room room2 = new Room(2, 1, null, false, true, false, false, 150.0, false);
		Room room3 = new Room(3, 4, null, false, false, true, false, 200.0, false);
		Room room4 = new Room(4, 2, null, false, false, true, false, 200.0, false);
		roomsAvailableInHotel.add(room4);
		roomsAvailableInHotel.add(room3);
		roomsAvailableInHotel.add(room1);
		roomsAvailableInHotel.add(room2);
		System.out.println("showAllRoomBookings:");
		showAllRoomBookings();
		System.out.println("showAllRooms:");
		showAllRooms();
	}
	
	public int getNumberOfRooms() {
		return roomsAvailableInHotel.size();
	}
	
	public void addRoom(Room room) {
		roomsAvailableInHotel.add(room);
		System.out.println("Added room: " + room.toString());
	}

	public void showAllRooms() {
		for (Room rooms : roomsAvailableInHotel) {
			System.out.println(rooms.toString());
		}
	}
	
	public ArrayList<Room> showRooms() {
		return roomsAvailableInHotel;
	}

	public void removeRoom(Room room) {
		roomsAvailableInHotel.remove(room);
		System.out.println("Removed room: " + room.toString());
	}

	
	public String adjustRoomPrice() {
		String result = "";
		System.out.println("Room no receied is " + roomNo);
		iterative:
		for (Room rooms : roomsAvailableInHotel) {
			if (rooms.getRoomNumber() == roomNo) {
				System.out.println(rooms.getRoomNumber());
				rooms.setRoomCost(price);
				result = "dashboard.xhtml";
				break iterative;
			}
		}
		System.out.println("Updated room prices: ");
		showAllRooms();
		return result;
	}
	
	public void staffCheckCustomerIn() {
		UserData userData = Helper.getBean("userData", UserData.class);
		LocalDate today = LocalDate.now();
		System.out.println("LocalDate value is: " + today);
		for(User user : userData.getUsers()) {
			if(user.getBookings().contains(today)) {
				System.out.println("This customer is due today "+ user);
				System.out.println("They are due to stay in " + getCustomerRoomNo());
			}
		}
	}
	
	
	public boolean bookRoom(String noAdults, String noChildren, Date checkIn, Date checkOut) {
		boolean result = false;
		System.out.println("check in: " + checkIn);
		LoginBean loginBean = Helper.getBean("loginBean", LoginBean.class);
		User user = loginBean.getUser();
		System.out.println("Get user result: " + user.toString());
		for(Room room : roomsAvailableInHotel) {
			if(room.getNoBeds() < Integer.parseInt(noChildren) + Integer.parseInt(noAdults)) {
				continue;
			}
			LocalDate checkInDate = checkIn.toInstant().atZone(ZoneId.of("GB-Eire")).toLocalDate();
			LocalDate checkOutDate = checkOut.toInstant().atZone(ZoneId.of("GB-Eire")).toLocalDate();
			System.out.println("CheckInDate: " + checkInDate);
			int start = checkInDate.getDayOfYear();
			System.out.println("Start: " + start);
			int end = checkOutDate.getDayOfYear();
			
			SortedMap<Integer, Boolean> checkDates = room.bookings.subMap(start, end);
			if(checkDates.containsValue(true)) {
				System.out.println(room.getRoomNumber() + " is booked at this time");
				continue;
			} else {
				System.out.println("Booked room " +room.getRoomNumber());
				result = true;
				setCustomerRoomNo(room.getRoomNumber());
				//user.setBooking(checkInDate);
				for(int i = start; i <= end; i++) {
					room.bookings.replace(i, true);
					room.customerOccupation.replace(i, user.getUserName());
					LocalDate date = LocalDate.ofYearDay(2021, i);
					System.out.println("Setting customer booking day: " + date);
					user.setBooking(date);
					user.setRoomNo(room.getRoomNumber());
				}
				
				System.out.println("room.bookings.toString() " + room.bookings.toString());
				System.out.println("user.getBookings() " +user.getBookings());
				staffCheckCustomerIn();
				checkAllBookings();
				break;
			}
		}
		return result;
		
	}

	public void showAllRoomBookings() {
		System.out.println(roomsAvailableInHotel.toString());
	}
	
	
	public void removeBooking() {
		LoginBean loginBean = Helper.getBean("loginBean", LoginBean.class);
		User user = loginBean.getUser();
		for(Room room : roomsAvailableInHotel) {
			if(room.customerOccupation.containsValue(user.getUserName())) {
				user.bookings.clear();
				for(int i = 1; i <= 365; i++) {
					room.customerOccupation.put(i, null);
					room.bookings.replace(i, null);
				}
			}
		}
		
		System.out.println("user.getBookings() " +user.getBookings());
	}

	public int getCustomerRoomNo() {
		return customerRoomNo;
	}

	public void setCustomerRoomNo(int customerRoomNo) {
		this.customerRoomNo = customerRoomNo;
	}

	
	public ArrayList<User> checkAllBookings() {
		UserData userData = Helper.getBean("userData", UserData.class);
		
		for(User user: userData.getUsers()) {
			System.out.println(user.getBookings().toString());
		}
		return userData.getUsers();
		
	}

}
package com.mightbytes.bhd;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Date;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mightybytes.bhd.Helper;
import com.mightybytes.bhd.LoginBean;
import com.mightybytes.bhd.Room;
import com.mightybytes.bhd.RoomBooking;
import com.mightybytes.bhd.User;
import com.mightybytes.bhd.UserData;

class RoomBookingTest {
	RoomBooking rm;
	User user;
	@BeforeEach
	void setUp() throws Exception {
		user = new User("test@test", "test", true, false);
		rm = new RoomBooking();
	}

	@Test
	void testAddRoom() {
		RoomBooking rm = new RoomBooking();
		int sizeBeforeAdd = rm.getNumberOfRooms();
		Room room = new Room();
		rm.addRoom(room);
		int sizeAfterAdd = rm.getNumberOfRooms();
		assertEquals(sizeBeforeAdd+1, sizeAfterAdd);
	}
	
	@Test	
	void testRemoveRoom() {
		RoomBooking rm = new RoomBooking();
		Room room = new Room();
		Room room1 = new Room();
		rm.addRoom(room);
		rm.addRoom(room1);
		int sizeBeforeRemove = rm.getNumberOfRooms();
		rm.removeRoom(room1);
		int sizeAfterRemove = rm.getNumberOfRooms();
		assertEquals(sizeBeforeRemove-1, sizeAfterRemove);
	}
	
	@Test
	void bookingTest() {
		RoomBooking rm = new RoomBooking();
		LoginBean loginBean = Helper.getBean("loginBean", LoginBean.class);
		loginBean.setUser(user);
		Date checkIn = new Date(2021, 8, 26);
		Date checkOut = new Date(2021, 8, 27);
		Room room = new Room();
		room.setNoBeds(2);
		assertEquals(rm.bookRoom("2", "0", checkIn, checkOut), true);
		
	}
	
	
	@Test
	void getCustomerRoomNoTest() {
		rm.setCustomerRoomNo(5);
		assertEquals(rm.getCustomerRoomNo(), 5);
	}
	
	@Test
	void testAdjustRoomPrice() {
		rm.setRoomNo(4);
		rm.setPrice(500);
		rm.adjustRoomPrice();
		assertEquals(rm.getPrice(), 500);
	}
	
	@Test
	void testAdjustRoomPriceRoomNotFound() {
		rm.setRoomNo(100);
		rm.setPrice(500);
		rm.adjustRoomPrice();
		assertEquals(rm.getPrice(), 500);
	}
	
	@Test
	void testStaffCheckIn() {
		LocalDate date = LocalDate.now();
		user.setBooking(date);
		
	}
	
	@Test
	void testShowAll() {
		rm.showAllRoomBookings();
	}
}

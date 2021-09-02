package com.mightbytes.bhd;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.mightybytes.bhd.Room;
import com.mightybytes.bhd.User;

class RoomTest {
	Room room;
	User user;
	
	@BeforeEach
	void setUp() throws Exception {
		room = new Room();
		user = new User("test@test", "test", false, true);
		
	}



	@Test
	void testGetRoomNumber() {
		room.setRoomNumber(10);
		assertEquals(room.getRoomNumber(), 10);
	}

	@Test
	void testSetRoomNumber() {
		room.setRoomNumber(5);
		assertEquals(room.getRoomNumber(), 5);
	}

	@Test
	void testGetNoBeds() {
		room.setNoBeds(2);
		assertEquals(room.getNoBeds(),2);
	}

	@Test
	void testSetNoBeds() {
		room.setNoBeds(4);
		assertEquals(room.getNoBeds(), 4);
	}

	@Test
	void testGetRoomName() {
		room.setRoomName("test");
		assert(room.getRoomName().equalsIgnoreCase("test"));
	}

	@Test
	void testSetRoomName() {
		room.setRoomName("test");
		assert(room.getRoomName().equalsIgnoreCase("test"));
	}

	@Test
	void testIsDouble() {
		assertEquals(room.isDouble(), false);
	}

	@Test
	void testSetDouble() {
		room.setDouble(true);
		assertEquals(room.isDouble(), true);
	}

	@Test
	void testIsSingle() {
		assertEquals(room.isSingle(), false);
	}

	@Test
	void testSetSingle() {
		room.setSingle(true);
		assertEquals(room.isSingle(), true);
	}

	@Test
	void testIsTwin() {
		assertEquals(room.isTwin(), false);
	}

	@Test
	void testSetTwin() {
		room.setTwin(true);
		assertEquals(room.isTwin(), true);
	}

	@Test
	void testIsFamily() {
		assertEquals(room.isFamily(), false);
	}

	@Test
	void testSetFamily() {
		room.setFamily(true);
		assertEquals(room.isFamily(), true);
	}

	@Test
	void testGetRoomCost() {
		room.setRoomCost(200.0);
		assertEquals(room.getRoomCost(), 200.0);
	}

	@Test
	void testSetRoomCost() {
		room.setRoomCost(150);
		assertEquals(room.getRoomCost(), 150);
	}

	@Test
	void testIsBooked() {
		assertEquals(room.IsBooked(), false);
	}

	@Test
	void testSetRoomOccupied() {
		room.setRoomOccupied(true);
		assertEquals(room.IsBooked(), true);
	}

	@Test
	void testGetRoomOccupiedByCustomerId() {
		room.setRoomOccupiedByCustomerId(15);
		assertEquals(room.getRoomOccupiedByCustomerId(), 15);
	}

	@Test
	void testSetRoomOccupiedByCustomerId() {
		room.setRoomOccupiedByCustomerId(10);
		assertEquals(room.getRoomOccupiedByCustomerId(), 10);
	}

	@Test
	void testGetBookedFrom() {
		assertEquals(room.getBookedFrom(), null);
	}

	@Test
	void testSetBookedFrom() {
		Date date = new Date(2021, 8, 25);
		room.setBookedFrom(date);
		assertEquals(room.getBookedFrom(), date);
	}

	@Test
	void testGetBookedTo() {
		Date date = new Date(2021, 8, 25);
		room.setBookedTo(date);
		assertEquals(room.getBookedTo(), date);
	}

	@Test
	void testSetBookedTo() {
		Date date = new Date(2021, 8, 25);
		room.setBookedTo(date);
		assertEquals(room.getBookedTo(), date);
	}

	@Test
	void testGetBookedDates() {
		Date dateFrom = new Date(2021, 8, 25);
		Date dateTo = new Date(2021, 8, 26);
		room.setBookedTo(dateFrom);
		room.setBookedFrom(dateTo);
		assertNotNull(room.getBookedDates());
	}

}

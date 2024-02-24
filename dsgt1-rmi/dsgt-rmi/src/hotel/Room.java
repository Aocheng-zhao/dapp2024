package hotel;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class Room {

	private Integer roomNumber;
	private List<BookingDetail> bookings;

	public Room(Integer roomNumber) {
		this.roomNumber = roomNumber;
		bookings = new ArrayList<BookingDetail>();
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public List<BookingDetail> getBookings() {
		return bookings;
	}

	public void setBookings(List<BookingDetail> bookings) {
		this.bookings = bookings;
	}

	public boolean isAvailable(LocalDate date) {
		// Check if the room is available on the given date
		for (BookingDetail booking : bookings) {
			if (booking.getDate().equals(date)) {
				// If a booking is found for the given date, the room is not available
				return false;
			}
		}
		// If no booking is found for the given date, the room is available
		return true;
	}
}
package hotel;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BookingManager implements IBookingManager{

	private Room[] rooms;

	public BookingManager() {
		this.rooms = initializeRooms();
	}

	@Override
	public Set<Integer> getAllRooms() {
		Set<Integer> allRooms = new HashSet<Integer>();
		Iterable<Room> roomIterator = Arrays.asList(rooms);
		for (Room room : roomIterator) {
			allRooms.add(room.getRoomNumber());
		}
		return allRooms;
	}
	@Override
	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) {
		//implement this method
		for (Room room : rooms) {
			if (room.getRoomNumber().equals(roomNumber)) {
				// Check if the room is available on the given date

				return room.isAvailable(date);
			}
		}
		// If the room with the specified room number is not found, return false
		return false;
	}
	@Override
	public void addBooking(BookingDetail bookingDetail) {
		//implement this method
		if(isRoomAvailable(bookingDetail.getRoomNumber(),bookingDetail.getDate())){
			for(Room room : rooms){
				if(room.getRoomNumber().equals(bookingDetail.getRoomNumber())){

					room.getBookings().add(bookingDetail);
				}
			}
		}

	}
	@Override
	public Set<Integer> getAvailableRooms(LocalDate date) {
		//implement this method
		Set<Integer> availableRooms = new HashSet<Integer>();
		for(Room room : rooms){
			boolean isAvail = true;
			for(BookingDetail detail : room.getBookings()){
				if(detail.getDate().equals(date)){
					isAvail = false;
					break;
				}
			}
			if(isAvail)	availableRooms.add(room.getRoomNumber());
		}
		return availableRooms;
	}

	private static Room[] initializeRooms() {
		Room[] rooms = new Room[4];
		rooms[0] = new Room(101);
		rooms[1] = new Room(102);
		rooms[2] = new Room(201);
		rooms[3] = new Room(203);
		return rooms;
	}
}

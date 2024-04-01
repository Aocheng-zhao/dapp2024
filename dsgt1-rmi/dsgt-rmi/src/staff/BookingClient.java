package staff;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Set;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import hotel.BookingDetail;
import hotel.IBookingManager;
import hotel.BookingManager;


public class BookingClient extends AbstractScriptedSimpleTest {

	private IBookingManager bm = null;

	public static void main(String[] args) throws Exception {
		String host = (args.length < 1) ? null : args[0];
		int port = (args.length < 2) ? Registry.REGISTRY_PORT : Integer.parseInt(args[1]); // Default to the standard RMI Registry port if not provided
		try {
			Registry registry = LocateRegistry.getRegistry(host,port);
			IBookingManager stub = (IBookingManager) registry.lookup("BookingManager");
			String response = stub.getAllRooms().toString();
			System.out.println("response: " + response);
			BookingClient client = new BookingClient();
			client.bm = stub;
			client.run();
		}
		catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

	/***************
	 * CONSTRUCTOR *
	 ***************/
	public BookingClient() {
	}

	@Override
	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) throws RemoteException {
		//Implement this method
		return bm.isRoomAvailable(roomNumber,date);
	}

	@Override
	public void addBooking(BookingDetail bookingDetail) throws RemoteException {
		//Implement this method

		bm.addBooking(bookingDetail);
	}

	@Override
	public Set<Integer> getAvailableRooms(LocalDate date) throws RemoteException {
		//Implement this method
		return bm.getAvailableRooms(date);
	}

	@Override
	public Set<Integer> getAllRooms() throws RemoteException {
		return bm.getAllRooms();
	}
}

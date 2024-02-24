package hotel;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Set;

public interface IBookingManager extends Remote{
    Set<Integer> getAllRooms() throws RemoteException;
    boolean isRoomAvailable(Integer roomNumber, LocalDate date) throws RemoteException;
    void addBooking(BookingDetail bookingDetail) throws RemoteException;
    Set<Integer> getAvailableRooms(LocalDate date) throws RemoteException;
}

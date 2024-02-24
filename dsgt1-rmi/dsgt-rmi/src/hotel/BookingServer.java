package hotel;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BookingServer {
    public static void main(String[] args) throws Exception {
        //create
        BookingManager bm = new BookingManager();
        IBookingManager stub = (IBookingManager) UnicastRemoteObject.exportObject(bm,0);
        Registry registry = LocateRegistry.getRegistry();
        registry.rebind("IBookingManager",stub);
    }
}

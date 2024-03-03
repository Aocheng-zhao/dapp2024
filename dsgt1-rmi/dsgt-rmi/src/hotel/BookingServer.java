package hotel;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BookingServer {
    public static void main(String[] args) throws Exception {
        //create
        try{
            BookingManager bm = new BookingManager();
            IBookingManager stub = (IBookingManager) UnicastRemoteObject.exportObject(bm,0);
            //Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind("BookingManager",stub);

            System.err.println("Server ready");
        }catch (Exception e){
            System.err.println("Server exception:" + e.toString());
            e.printStackTrace();
        }


    }
}

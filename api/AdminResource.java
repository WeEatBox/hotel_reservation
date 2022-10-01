package api;

import java.util.*;
import service.*;
import model.*;

public class AdminResource{

    public static Customer getCustomer(String email) throws Exception{
        return CustomerService.getCustomer(email);
    }

    //public void addRoom(List<IRoom> rooms){
    //    for (Room room : rooms){
    //        if (!this.rooms.contains(room))
    //            ReservationService.addRoom(room);
    //    }
    //}
    public static void addRoom(IRoom room){
        ReservationService.addRoom(room);
    }

    public static Collection<IRoom> getAllRooms(){
        return ReservationService.getAllRoom();
    }

    public static Collection<Customer> getAllCustomers(){
        return CustomerService.getAllCustomers();
    }

    public static void displayAllReservations(){
        ReservationService.printAllReservation();
    }
}

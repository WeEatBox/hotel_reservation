package api;

import java.util.*;
import model.*;
import service.*;

public class HotelResource{

    public static Customer getCustomer(String email) throws Exception{
        return CustomerService.getCustomer(email);
    }

    public static void createACustomer(String email, String firstName, String lastName) throws Exception{
        CustomerService.addCustomer(email, firstName, lastName);
    }

    public static IRoom getRoom(String roomNumber) throws Exception{
        return ReservationService.getARoom(roomNumber);
    }

    public static Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) throws Exception{
        return ReservationService.reserveARoom(getCustomer(customerEmail), room, checkInDate, checkOutDate);
    }

    public static Collection<Reservation> getCustomersReservations(String customerEmail) throws Exception{
        return ReservationService.getCustomersReservation(getCustomer(customerEmail));
    }

    public static Collection<IRoom> findARoom(Date checkIn, Date checkOut){
        return ReservationService.findRooms(checkIn, checkOut);
    }
}

package service;

import java.util.*;
import model.*;

public class ReservationService{
    private static Collection<Reservation> reservations = new HashSet<>();
    private static Collection<IRoom> rooms = new HashSet<>();

    public static void addRoom(IRoom room){
        rooms.add(room);
    }

    public static Collection<IRoom> getAllRoom(){
        Collection<IRoom> rooms2 = new HashSet<>();
        for (IRoom room : rooms){
            rooms2.add(room);
        }
        return rooms2;
    }

    public static IRoom getARoom(String roomId) throws Exception{
        for (IRoom room : rooms){
            String roomNumber = room.getRoomNumber();
            if (roomNumber.equals(roomId))
                return room;
        }
        throw new IllegalArgumentException("invalid room ID!");

      //  if (rooms.containsKey(roomID)){
      //      Room room2 = rooms.get(roomID);
      //      return room2;
      //  }
      //  else{
      //      throw IllegalArgumentException("invalid room ID!");
      //  }
        // not sure what to return if invalid input
    }

    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
        // check date conflict by call findRooms
    }

    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate){
        Collection<IRoom> rooms2 = new HashSet<>();
        for (IRoom room : rooms)
            rooms2.add(room);
        for (Reservation res : reservations){
            if (rooms2.contains(res.room)){  
                // checkOutDate for new reservation should before the current checkInDate
                // checkInDate for new reservation should after the current checkOutDate
                if (checkOutDate.compareTo(res.checkInDate)<0 || checkInDate.compareTo(res.checkOutDate)>0){
                    continue;
                }
                else
                     rooms2.remove(res.room);
            }
            //else
            //    rooms2.add(res.room);
        } 
        return rooms2;
    }

    public static Collection<Reservation> getCustomersReservation(Customer customer){
        // for (Reservation res : reservations)
        // override customer.equals(reservation.customer)    
        //      if same customer add into return list
        Collection<Reservation> reservations2 = new HashSet<>();
        for (Reservation res : reservations){
            if (customer.equals(res.customer)){
                reservations2.add(res);
            }
        }
        return reservations2;
    }

    public static void printAllReservation(){
        for (Reservation res : reservations)
            System.out.println(res);
    }

}

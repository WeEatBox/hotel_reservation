package model;

import java.util.*;

public class Reservation{
    public final Customer customer;
    public final IRoom room;
    public final Date checkInDate;
    public final Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override 
    public String toString(){
        return "Reservation info:\n" +
                this.customer.toString() +
               "room# = " +
                this.room.toString() +
               "\ncheck in date: " +
                this.checkInDate +
               "\ncheck out date: " +
                this.checkOutDate +
                "\n";
    }
}

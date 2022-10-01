package model;

import java.util.*;
import model.RoomType;

public class Room implements IRoom{

    public final String roomNumber;
    public final Double price;
    public final RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration){
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    public String getRoomNumber(){
        String roomNumber2 = this.roomNumber;
        return roomNumber2;
    }
        

    public Double getRoomPrice(){
        Double price2 = price;
        return price2;
    }

    public RoomType getRoomType(){
        return this.enumeration;
    }

    public boolean isFree(){
        return ((this.price == 0) ? true : false);
    }

    @Override
    public String toString(){
        return "room#: " + this.roomNumber + "\t" +
               "price: $" + this.price + "\t" +
               "room type: " + this.enumeration +
               "\n";
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || this.getClass() != o.getClass()) return false;

        Room that = (Room) o;

        return this.roomNumber  == that.roomNumber; 
            //   same room number will consider as same room?
            //   this.price       == that.price &&
            //   this.enumeration == that.enumeration;
    }

    @Override
    public int hashCode(){
        return Objects.hash(roomNumber, price, enumeration);
    }
}

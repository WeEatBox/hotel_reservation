package model;

import java.util.*;
import model.RoomType;

public class FreeRoom extends Room{
    
    public FreeRoom(String roomNumber, RoomType enumeration){
        super(roomNumber, 0.0, enumeration);
    }

    @Override
    public String toString(){
        return "room#: " +
                super.roomNumber +
                "\nprice: $ Free!\nroom type: " +
                super.enumeration.name();
    }
}

package cli;

import java.util.*;
import java.util.regex.*;
import api.*;
import model.*;
import model.RoomType;

public class AdminMenu{
    Scanner scanner = new Scanner(System.in);
    StringBuilder input = new StringBuilder();

    public Integer showMenu(){
       // Scanner scanner = new Scanner(System.in);
       // String input = "";
        int usrInput;
        do{
            input.setLength(0);
            System.out.println("""
                                Admin Menu:\n
                                1.\tSee all Customers\n
                                2.\tSee all Rooms\n
                                3.\tSee all Reservations\n
                                4.\tAdd a Room\n
                                5.\tBack to Main Menu
                               """);
            input.append(scanner.nextLine());
            usrInput = Integer.valueOf(input.charAt(0)) - 48;
        }while(usrInput>5 || usrInput<1);
        
        return usrInput;
    }


    public void seeAllCustomers(){
        for (Customer cus :AdminResource.getAllCustomers())
            System.out.println(cus);
    }
    public void seeAllRooms(){
        for (IRoom room :AdminResource.getAllRooms())
            System.out.println(room);
    }

    public void seeAllReservations(){
        AdminResource.displayAllReservations();
    }
    public void addARoom(){
        String roomNumber;
        Double price;
        RoomType roomEnum;
        boolean anotherNewRoom = false;

        String numberRegex = "\\d{4}";
        Pattern patternRoomNumber = Pattern.compile(numberRegex);
        do{
            input.setLength(0);
            System.out.println("Enter 4-digit room number:\n");
            input.append(scanner.nextLine());
            roomNumber = input.toString();
        }while(!patternRoomNumber.matcher(input.toString()).matches());

        // xxxx or xxxx.xx
        String priceRegex = "\\d+(\\.)?\\d+";
        Pattern patternPrice = Pattern.compile(priceRegex);
        do{
            input.setLength(0);
            System.out.println("Enter price per night:\n");
            input.append(scanner.nextLine());
            price = Double.valueOf(input.toString());
        }while(!patternPrice.matcher(input.toString()).matches());

        String roomTypeRegex = "[1-2]{1}";
        Pattern patternRoomType = Pattern.compile(roomTypeRegex);
        do{
            input.setLength(0);
            System.out.println("Enter room type: 1 for single, 2 for double:\n");
            input.append(scanner.nextLine());
            roomEnum = (Integer.valueOf(input.toString()) == 1) ? RoomType.SINGLE : RoomType.DOUBLE;
        }while(!patternRoomType.matcher(input.toString()).matches());
        
        Room newRoom = new Room(roomNumber, price, roomEnum);
        // TODO if same room number...
        AdminResource.addRoom(newRoom);

        // ask if create another room    
        String yesNoRegex = "[yYnN]{1}";
        Pattern patternYesNo = Pattern.compile(yesNoRegex);
        do{
            input.setLength(0);
            System.out.println("would you like to add another room? y/n\n");
            input.append(scanner.nextLine());
        }while(!patternYesNo.matcher(input.toString()).matches());

        String yesNo = input.toString();
        if (yesNo.equalsIgnoreCase("y"))
            this.addARoom();
    }

    public void backToMainMenu(){
        System.out.println("loading Main Menu...");
    }
}

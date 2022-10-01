package cli;

import java.util.*;
import java.util.Scanner;
import java.util.regex.*;
import api.*;
import model.*;
import java.text.SimpleDateFormat;

public class MainMenu{
    Scanner scanner = new Scanner(System.in);
    StringBuilder input = new StringBuilder();

    public Integer showMenu(){

        int usrInput;
        do{
            input.setLength(0);
            System.out.println("""
                                Menu:\n
                                1.\tFind and reserve a room\n
                                2.\tSee my reservations\n
                                3.\tCreate an account\n
                                4.\tAdmin\n
                                5.\tExit
                               """);
            input.append(scanner.nextLine());
            usrInput = Integer.valueOf(input.charAt(0)) - 48;
        }while(usrInput>5 || usrInput<1);
        
        return usrInput;
    }

    public void findAndReserveARoom() throws Exception{
        //String sDate1="31/12/1998";
        //Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1); 
        Date checkIn;
        Date checkOut;
        String email = "";
        String roomNumber = "";

        Scanner scanner = new Scanner(System.in);

        //regex for date check
        String dateRegex = "(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[0-2])\\/\\d{4}";
        //String dateRegex = "\\d{2}\\/\\d{2}\\/\\d{4}";
        Pattern patternDate = Pattern.compile(dateRegex);
        do{
            input.setLength(0);
            System.out.println("please enter your check in date: dd/MM/yyyy\n");
            input.append(scanner.nextLine());
        }while(!patternDate.matcher(input.toString()).matches());
        checkIn = new SimpleDateFormat("dd/MM/yyyy").parse(input.toString()); 

        do{
            input.setLength(0);
            System.out.println("enter your check out date: dd/MM/yyyy\nplease note that check out date needs to be later than check in date\n");
            input.append(scanner.nextLine());
        }while(!patternDate.matcher(input.toString()).matches());
        checkOut = new SimpleDateFormat("dd/MM/yyyy").parse(input.toString()); 
        // check check out date earlier than check in date
        if (checkIn.compareTo(checkOut)>=0){
            System.out.println("check-in date need to be eariler than check-out date!");
            findAndReserveARoom();
        }

        String yesNoRegex = "[yYnN]{1}";
        Pattern patternYesNo = Pattern.compile(yesNoRegex);
        do{
            input.setLength(0);
            System.out.println("do you have an account with us? y/n\n");
            input.append(scanner.nextLine());
            //TODO handle wrong account or no account exists
        }while(!patternYesNo.matcher(input.toString()).matches());
        String yesNo = input.toString();

        if (yesNo.equalsIgnoreCase("n")){
            System.out.println("please create an account first:\n");
            createAnAcount();
        }
        System.out.println("\nplease enter your account email:\n");
        String emailRegex = "^(.+)@(.+).com$";
        Pattern patternEmail = Pattern.compile(emailRegex);
        do{
            input.setLength(0);
            System.out.println("Enter email format: name@domain.com \n");
            input.append(scanner.nextLine());
        }while(!patternEmail.matcher(input.toString()).matches());
        email = input.toString();

        Collection<String> roomsAvailable = new HashSet<>();
        for (IRoom room : HotelResource.findARoom(checkIn, checkOut)){
            System.out.println(room);
            roomsAvailable.add(room.getRoomNumber());
        }

        // handle if no room available given date
        if (roomsAvailable.size()<1){
            System.out.println("no room available, please search another date...");
            findAndReserveARoom();
        }
        
        // show rooms avalible
        System.out.println("which room do you like to book?\n");
        String roomNumberRegex = "\\d{4}";
        Pattern patternRoomNumber = Pattern.compile(roomNumberRegex);
        do{
            input.setLength(0);
            System.out.println("Enter 4-digit room number you'd like to book:\n");
            input.append(scanner.nextLine());
        }while(!patternRoomNumber.matcher(input.toString()).matches() || !roomsAvailable.contains(input.toString()));
        roomNumber = input.toString();

        Reservation res = HotelResource.bookARoom(email, HotelResource.getRoom(roomNumber), checkIn, checkOut);
        System.out.println("book successful! below are the booking info:\n");
        System.out.println(res);
    }

    public void seeMyReservations() throws Exception{
        System.out.println("please enter your email:\n");
        String emailRegex = "^(.+)@(.+).com$";
        Pattern patternEmail = Pattern.compile(emailRegex);
        Scanner scanner = new Scanner(System.in);
        do{
            input.setLength(0);
            System.out.println("Enter email format: name@domain.com \n");
            input.append(scanner.nextLine());
        }while(!patternEmail.matcher(input.toString()).matches());
        String email = input.toString();

        System.out.println(HotelResource.getCustomersReservations(email));
    }

    public void createAnAcount() throws Exception{
        String email = "";
        String first = "";
        String last = "";
        String emailRegex = "^(.+)@(.+).com$";
        Pattern patternEmail = Pattern.compile(emailRegex);
        do{
            input.setLength(0);
            System.out.println("Enter email format: name@domain.com \n");
            input.append(scanner.nextLine());
        }while(!patternEmail.matcher(input.toString()).matches());
        email = input.toString();

        String nameRegex = "[a-zA-Z]+";
        Pattern patternName = Pattern.compile(nameRegex);
        do{
            input.setLength(0);
            System.out.println("First name: \n");
            input.append(scanner.nextLine());
        }while(!patternName.matcher(input.toString()).matches());
        first = input.toString();

        do{
            input.setLength(0);
            System.out.println("Last name: \n");
            input.append(scanner.nextLine());
        }while(!patternName.matcher(input.toString()).matches());
        last = input.toString();

        HotelResource.createACustomer(email, first, last);
    }

    public void admin(){
        System.out.println("loading admin menu...");
    }

    public void exit(){
        System.out.println("Thank you for using the hotel reservation system!\nSee you next time.");
    }

}

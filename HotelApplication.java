import cli.*;

public class HotelApplication{

    public static void main(String args[]) throws Exception{
        boolean exit = false;
        boolean exitAdmin = false;
        MainMenu mainMenu = new MainMenu();
        AdminMenu adminMenu = new AdminMenu();
        int usrInput;
        

        while(!exit){
            exit = false;
            exitAdmin = false;

            usrInput = mainMenu.showMenu();

            switch(usrInput){
                case 1:
                    mainMenu.findAndReserveARoom();
                    break;
                case 2:
                    mainMenu.seeMyReservations();
                    break;
                case 3:
                    mainMenu.createAnAcount();
                    break;
                case 4:
                    mainMenu.admin();
                    while(!exitAdmin){
                        usrInput = adminMenu.showMenu();
                        switch(usrInput){
                            case 1:
                                adminMenu.seeAllCustomers();
                                break;
                            case 2:
                                adminMenu.seeAllRooms();
                                break;
                            case 3:
                                adminMenu.seeAllReservations();
                                break;
                            case 4:
                                adminMenu.addARoom();
                                break;
                            case 5:
                                adminMenu.backToMainMenu();
                                exitAdmin = true;
                                break;
                        }
                    }
                    break;
                case 5: 
                    mainMenu.exit();
                    exit = true;
                    break;
            } // end of switch
        } // end of while
    } // end of main
}

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 * Main class. Creates hotel object and has the console app.
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 1/19/2019
 */
public class Main
{
    private static Scanner input;
    private static String selection; //should be a String, if it's hardcoded as an int, then the program will crash if in int is not entered.
    private static Hotel hotel;
    
    public static void main(String[] args) throws FileNotFoundException 
    {
        // instantiate a Hotel object using the constructor that takes a text file of Room info
        hotel = new Hotel("HotelBurgerwithRooms.txt");
        
        // add all existing/saved reservations (from a text file) to the Hotel object
        hotel.fillReservationArrayList("HotelBurgerReservations.txt");
        
        // print some hotel details (name, address, phone number) to the console
        System.out.println(hotel);
        
        // start keyboard input for the console app
        input = new Scanner(System.in);
        
        // show the main menu for the console app to the user
        mainMenu();        
    }

    /* HELPER METHODS */
    
    /** 
     * Returns true if input from the keyboard is an integer.
     * 
     * @param s (String) keyboard input
     * @return true if input is an integer
     */
    private static boolean isInt(String s) 
    {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }        
    }

    /**
     * Method getUserInputInt 1/2: Prompts the user to enter an int greater than the 
     * lowest value allowed.
     * 
     * @param lowest (int) user input
     * @return Integer.parseInt(selection)
     */
    public static int getUserInputInt(int lowest)
    {
        selection = input.next();        

        while (!isInt(selection) || Integer.parseInt(selection) < lowest) 
        {
            System.out.println(" Input not recognized, please try again");
            selection = input.next();  
        } 
        return Integer.parseInt(selection);
    }
    
    /**
     * Method getUserInputInt 2/2: Prompts the user to enter an int greater than the 
     * lowest value allowed.
     * 
     * @param lowest (int) user input
     * @param highest (int) user input
     * @return Integer.parseInt(selection)
     */
    public static int getUserInputInt(int lowest, int highest) 
    {
        selection = input.next();        

        while ( !isInt(selection) || 
                Integer.parseInt(selection) < lowest || 
                Integer.parseInt(selection) > highest ) 
        {
            System.out.println(" Input not recognized, please try again");
            selection = input.next();  
        } 
        return Integer.parseInt(selection);
    }
    
    /**
     * Prompts the user to get back to the main menu by pressing 0. 
     * This is going to be used anytime a menu gets to a dead end, 
     * such as when a task is complete or there is nothing else to do in that menu.
     */
    private static void returnToMainMenuPrompt() 
    {
        System.out.println();
        System.out.println(" Press 0 to return to the main menu");
        selection = input.next();
        
        while ( !isInt(selection) || Integer.parseInt(selection) != 0 ) 
        {
            selection = input.next();  
        }        
        mainMenu();  
    }
    
    /**
     * Looks up all reservations under a last name and finds the right one. 
     * Used a couple times by the menus.
     * 
     * @return reservation (Reservation) a reservation object matched by last name
     */
    private static Reservation getReservationByLastName() 
    {
        Reservation reservation = null;        
        String lastName = "";        
        int reservationID = -1;
        ArrayList<Reservation> reservationsByName = new ArrayList<Reservation>();        
                   
        System.out.println(" To lookup the reservation, we need last name");
        System.out.println(" What is your last name?");  
        lastName = input.next();        
        reservationsByName = hotel.getReservationsByLastName(lastName);
        
        while ( (reservationsByName.size() < 1) && !lastName.equals("0") ) 
        {
            System.out.println(" Reservation not found, please try again, or press 0 to exit");
            System.out.println(" What is your last name?");  
            lastName = input.next();
            reservationsByName = hotel.getReservationsByLastName(lastName);
        }
        
        if (lastName.equals("0")) 
        {
            mainMenu();
        }          
        
        System.out.println(" Here are the reservations under that last name, " +
            "which would you like to choose(enter ID# from below list)? " +
            "Or press 0 to return to main menu");
        for (Reservation res: reservationsByName) 
        {
            System.out.println(res);
        }
                
        reservationID = getUserInputInt(0);
        reservation = hotel.getReservation(reservationID);
            
        while ( (reservation == null) && (reservationID != 0) ) 
        {
            System.out.println(" ReservationID was not entered correctly, try again. " +
                "Or press 0 to return to main menu");
            reservationID = getUserInputInt(1);
            reservation = hotel.getReservation(reservationID);
        }
        
        if (reservationID == 0) 
        {
            mainMenu();
        }          
       
        return reservation;    
    }
    
    //** MENU METHODS **/
    
    /**
     * Menu to show main options.
     */
    private static void mainMenu() 
    {
        // print a blank line followed by menu title
        System.out.println();
        System.out.println(" Please make a selection from the following options and press enter");
        System.out.println(" 1. Make a new reservation");
        System.out.println(" 2. Change/Cancel existing reservation");          
        System.out.println(" 3. See an existing reservation's checkin/out/cancel status"); 
        System.out.println(" 4. Guest info");
        System.out.println(" 5. See an existing reservation's invoice info");        
        System.out.println(" 6. View all available rooms");        
        System.out.println(" 7. See help menu");
        System.out.println(" 8. Quit/close +  save state");

        int selectionInt = getUserInputInt(1,8); 
        
        switch( selectionInt )
        {
            case 1:
                makeReservationMenu();
                break;
            case 2:
                changeReservationMenu();
                break;
            case 3:
                reservationStatusMenu();               
                break;
            case 4:
                guestMenu();
                break;
            case 5:
                invoiceMenu();           
                break;
            case 6:
                availableRoomsMenu();
                break; 
            case 7:
                helpMenu();
                break;        
            case 8:                    
                input.close();
                hotel.save();
                System.exit(0);                
                break;                
        }
    }          
        
    /**
     * Menu to create a new reservation.
     */
    private static void makeReservationMenu()
    {
        // variables needed to make reservations
        int partySize = 0;
        int nights = 0;
        String roomNumber;
        Room room;
        String firstName;
        String lastName;
        String phoneNumber;
        boolean isMilitary;
        boolean isGov;
        boolean isMember;
        Guest guest;
        ArrayList<Room> availableRooms;
        
        // print a blank line followed by menu title
        System.out.println();        
        System.out.println(" Make a Reservation Menu");
        
        System.out.println(" How many adults?");
        
        // get the number of adults, must be at least 1
        partySize = getUserInputInt(1);
        
        System.out.println(" How many nights?");  
        
        // get the number of nights, must be at least 1
        nights = getUserInputInt(1);
        
        availableRooms = hotel.getEmptyRooms();
        
        System.out.println( " These rooms are available:");        
        System.out.println(availableRooms); 
        System.out.println( " Enter the roomNumber you want to reserve or 0 to cancel:");
        roomNumber = input.next();   
        room = hotel.getRoom(roomNumber);
        
        /* validates the roomNumber that was typed matches what is available in Hotel.
         * if this check passes, we have a valid room about to be reserved, 
         * -- just need guest info to make the reservation */
        while ( !availableRooms.contains(room) && !roomNumber.equals("0") ) 
        {
            System.out.println(" Input not recognized, please try again, or press 0 to exit");
            roomNumber = input.next();
            room = hotel.getRoom(roomNumber);
        }
        
        if (roomNumber.equals("0")) 
        {
            mainMenu();
        }          
        
        /* get the guest's personal info. 
         * I don't think we need to perform any validation on name fields
         * because, remember, the hotel staff ask this questions and enter the data, 
         * so if the guest says a weird name, the hotel staff can figure it out */
        System.out.println(" What is your first name?");
        firstName = input.next();
        System.out.println(" What is your last name?");  
        lastName = input.next();
        System.out.println(" What is your cellphone number?");
        phoneNumber = input.next();
        
        // next get discount statuses
        System.out.println(" Are you active military? Enter 1 for yes, or 0 for no");        
        int in = getUserInputInt(0,1);
        isMilitary = (in == 1);
        
        System.out.println(" Are you an active government employee? Enter 1 for yes, or 0 for no");
        in = getUserInputInt(0,1);
        isGov = (in == 1); 
        
        System.out.println(" Are you a rewards member? Enter 1 for yes, or 0 for no"); 
        in = getUserInputInt(0,1);
        isMember = (in == 1); 
        
        // construct the Guest object
        guest = new Guest(firstName, lastName, phoneNumber, 
            partySize, nights, isMilitary, isGov, isMember);
        
        // construct the Reservation object (note: it's also possible to set status to waiting)
        Reservation reservation = new Reservation(room, guest, Status.IN);
        
        // add the reservation to the hotel
        hotel.addReservation(reservation);
        
        System.out.println( "Your reservation was successfully created:");         
        System.out.println(reservation);
        
        returnToMainMenuPrompt();
    }
    
    /**
     * Menu to allow changes to some data in a reservation.
     */
    private static void changeReservationMenu() 
    {
        Reservation reservation = null;        
        String newRoomNumber = "";
        Room newRoom = null;
        
        // print a blank line followed by menu title
        System.out.println(" Change Reservation Menu");
        
        while (reservation == null) 
        {
            reservation = getReservationByLastName();
        }
        
        System.out.println(" For this reservation, what would you like to change?");
        System.out.println(" 1. Cancel it");        
        System.out.println(" 2. Change room");
        System.out.println(" 0. Return to the main menu");                  
        
        int selectionInt = getUserInputInt(0,2); 
        
        switch( selectionInt )
        {
            case 0:
                mainMenu();
                break;
            case 1:                
                reservation.setStatus(Status.CANCELED);
                System.out.println("Successfully cancelled");
                break;
            case 2:
                System.out.println(" Which room from the following do want to change to?");
                System.out.println(hotel.getEmptyRooms());
                newRoomNumber = input.next();                
                newRoom = hotel.getRoom(newRoomNumber);
                while ( newRoom == null || !newRoom.isAvailable() )
                {
                    System.out.println(" Room not entered correctly or already reserved, try again");
                    newRoomNumber = input.next();                
                    newRoom = hotel.getRoom(newRoomNumber);
                }
                    
                reservation.setRoom(newRoom);
                System.out.println("Successfully changed room to room# " + newRoomNumber);
                break;
        }
        returnToMainMenuPrompt();
    }       
    
    /**
     * Menu to search for an invoice and view the information in it.
     */
    private static void invoiceMenu() 
    { 
        Reservation reservation = null;
        
        // print a blank line followed by menu title
        System.out.println(" Invoice Menu");        
        System.out.println(" 1. Search invoices on Guest Last Name");        
        System.out.println(" 2. See all paid invoices");
        System.out.println(" 3. See all unpaid invoices");      
        System.out.println(" 0. Return to the main menu");                  
        
        int selectionInt = getUserInputInt(0,3); 
        
        switch( selectionInt )
        {
            case 0:
                mainMenu();
                break;
            case 1:
                while (reservation == null) 
                {
                    reservation = getReservationByLastName();
                }                                
                System.out.println(reservation.getInvoice());
                break;
            case 2:
                ArrayList<String> invoicesPaid = hotel.getAllInvoicesPaid();
                System.out.println("\n" + "All paid invoices: ");
                System.out.println(invoicesPaid); 
                break;     
            case 3:
                ArrayList<String> invoicesUnpaid = hotel.getAllInvoicesUnpaid();
                System.out.println("\n" + "All unpaid invoices: ");
                System.out.println(invoicesUnpaid); 
                break;    
        }
        
        returnToMainMenuPrompt();     
    }
    
    /** 
     * Menu to see an existing reservation's guest information.
     */
    private static void guestMenu() 
    { 
        // print a blank line followed by menu title
        System.out.println(" Guest Menu");
        System.out.println(" What is the intended functionality of this menu supposed to be?");
        
        returnToMainMenuPrompt();
    }
            
    /**
     * Menu to view information about the status of a reservation. 
     * Is it checked in, checked out, or canceled?
     */
    private static void reservationStatusMenu() 
    {
        Reservation reservation = null;        
        
        // print a blank line followed by menu title
        System.out.println(" Reservation Status Menu");        
        
        while(reservation == null) 
        {
            reservation = getReservationByLastName();
        }
        
        System.out.println(" For this reservation, what would you like to change?");
        
        System.out.println(" 1. Cancel it");        
        System.out.println(" 2. Checkin");
        System.out.println(" 3. Checkout");
        System.out.println(" 0. Return to the main menu");                  
        
        int selectionInt = getUserInputInt(0,3); 
        
        switch(selectionInt)
        {
            case 0:
                mainMenu();
                break;
            case 1:                
                reservation.setStatus(Status.CANCELED);
                System.out.println("Successfully canceled");
                break;
            case 2:
                reservation.setStatus(Status.IN);
                System.out.println("Successfully checked in");
                break;
            case 3:
                reservation.setStatus(Status.OUT);
                System.out.println("Successfully checked out");
                break;
        }
        
        returnToMainMenuPrompt();
    }
    
    /**
     * Menu to see all available rooms.
     */
    private static void availableRoomsMenu() 
    {
        ArrayList<Room> availableRooms = hotel.getEmptyRooms();
        
        // print a blank line followed by menu title
        System.out.println(" Available Rooms Menu");
        
        System.out.println( " These rooms are available:");        
        System.out.println(availableRooms); 
        
        returnToMainMenuPrompt();
    }
    
    /**
     * Menu to display information on how to use this program.
     */
    private static void helpMenu() 
    {
        // print a blank line followed by menu title
        System.out.println(" Help Menu");
        System.out.println(" 1. Reserving a room: you cannot reserve a room unless " + 
                                "it is available(no one else has reserved it). " + 
                                "Currently dates are not yet factored in.");
        System.out.println(" 2. Changing a reservation: you must enter the last name of " + 
                                "the guest who made the reservation to find that reservation.");
        System.out.println("\t If a guest has multiple reservations with us, " + 
                                "you then need to find the room number");
        System.out.println(" 3. Discounts: discounts are applied for active duty military, " + 
                                "active government employees, and hotel members");
        returnToMainMenuPrompt();
    }
}
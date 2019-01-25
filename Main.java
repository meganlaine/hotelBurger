import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Iterator;

/**
 * Main class. Creates hotel object and has the console app.
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 1/21/2019
 */
public class Main
{
    private static Scanner input;
    /* String selection: should be a String, if it's hardcoded as an int, 
     * then the program will crash if in int is not entered. */
    private static String selection; 
    private static Hotel hotel;
    
    public static void main(String[] args) throws FileNotFoundException 
    {
        Hotel.test();
        // instantiate a Hotel object using the constructor that takes a text file of Room info
        hotel = new Hotel("hotelrooms.txt");
        
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
    private static void returnToMainMenuPrompt() throws FileNotFoundException 
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
     * Used a couple times by the menus. (For ex: menu(3))
     * 
     * @return reservation (Reservation) a reservation object matched by last name
     */
    private static Reservation getReservationByLastName() throws FileNotFoundException 
    {
        Reservation reservation = null;        
        String lastName = "";        
        int reservationID = -1;
        ArrayList<Reservation> reservationsByName = new ArrayList<Reservation>();        
                   
        System.out.println(" To look up the reservation, we'll need the guest's last name.");
        System.out.println(" What is the guest's last name?");  
        lastName = input.next();        
        reservationsByName = hotel.getReservationsByLastName(lastName);
        
        while ( (reservationsByName.size() < 1) && !lastName.equals("0") ) 
        {
            System.out.println(" Sorry! No matching reservations were found.");
            System.out.println(" Type 0 to exit -OR- Enter a different last name to try again:");  
            lastName = input.next();
            reservationsByName = hotel.getReservationsByLastName(lastName);
        }
        
        if (lastName.equals("0")) 
        {
            mainMenu();
        }          
        
        System.out.println(" Here are the reservations under that last name; " + "\n" +
            "    Which would you like to choose? " + "\n" +
            "    -- Enter ID# from below list " + "\n" +
            "    -- Or press 0 to return to main menu");
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
     * Main Menu (0) to show main options.
     */
    private static void mainMenu() throws FileNotFoundException 
    {
        // print a blank line followed by menu title
        System.out.println();
        System.out.println("+ + + + + + + + + + + + + + + + + + + + + + + + + + + + + + +");
        System.out.println(" Please make a selection from the following options and press enter");
        System.out.println(" 1. Make a new reservation");
        System.out.println(" 2. Change/Cancel existing reservation (ie check in/out)");          
        System.out.println(" 3. See an existing reservation's checkin/out/cancel status"); 
        System.out.println(" 4. Guest info");
        System.out.println(" 5. See an existing reservation's invoice info");        
        System.out.println(" 6. View all available rooms");
        System.out.println(" 7. View hotel report");        
        System.out.println(" 8. See help menu");
        System.out.println(" 9. Quit/close +  save state");
        System.out.println("+ + + + + + + + + + + + + + + + + + + + + + + + + + + + + + +");

        int selectionInt = getUserInputInt(1,9); 
        
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
                reportMenu();
                break;   
            case 8:
                helpMenu();
                break;        
            case 9:                    
                input.close();
                hotel.save();
                System.exit(0);                
                break;                
        }
    }          
        
    /**
     * Menu to create a new reservation.
     */
    private static void makeReservationMenu() throws FileNotFoundException 
    {
        // variables needed to make reservations
        int partySize = 0;
        int nights = 0;
        int priceRange = 0;
        String roomNumber;
        Room room;
        String firstName;
        String lastName;
        String phoneNumber;
        boolean isMilitary;
        boolean isGov;
        boolean isMember;
        Guest guest;
        ArrayList<Room> availableRooms = hotel.getEmptyRooms();
        Reservation reservation = null;
        
        // print a blank line followed by menu title
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = =");
        System.out.println(" MAKE A RESERVATION MENU");
        
        System.out.println(" How many adults?");
        
        // get the number of adults, must be at least 1
        partySize = getUserInputInt(1);
        
        System.out.println(" How many nights?");  
        
        // get the number of nights, must be at least 1
        nights = getUserInputInt(1);
        
                // get price range of user
        System.out.println("What is you price range?");
        System.out.println("Enter you selection: 1. $150 - $175 or 2. greater than $175");
        priceRange = getUserInputInt(1,2);

        availableRooms = roomOptionsTrimmer(partySize, priceRange, hotel.getEmptyRooms());

        System.out.println( " These rooms are available:");        
        // We want to a new line after printing 4 rooms, so we need a counter to keep track
        int newLineCounter = 0;
        if(availableRooms.isEmpty()) {
            System.out.println("Couldn't find any rooms matching this criteria, try again");
            makeReservationMenu();
        }
        else{
            for(Room r : availableRooms) {
                System.out.print("[ " + r + " ]");
                newLineCounter++;
                if(newLineCounter == 2) {
                    System.out.println();
                    newLineCounter = 0;
                }
            }
        }
        
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
        
        //try to construct the objects and catch exceptions gracefully
        try
        {
            guest = new Guest(firstName, lastName, phoneNumber, 
                isMilitary, isGov, isMember);
            
            // construct the Reservation object (note: it's also possible to set status to waiting)
            reservation = new Reservation(room, guest, Status.IN, partySize, nights);
            
            // add the reservation to the hotel
            hotel.addReservation(reservation);
        } catch(Exception e) {
            System.out.println("Error: " + e);
            System.out.println("Please try again");
            makeReservationMenu();
        }
        
        System.out.println( "Your reservation was successfully created:");         
        System.out.println(reservation);
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = =");
        
        returnToMainMenuPrompt();
    }
    
    /**
     * Menu to allow changes to some data in a reservation.
     * 
     * @throw FileNotFoundException if file of prior reservations is not found
     */
    private static void changeReservationMenu() throws FileNotFoundException 
    {
        Reservation reservation = null;        
        String newRoomNumber = "";
        Room newRoom = null;
        
        // print a blank line followed by menu title
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = =");
        System.out.println(" CHANGE RESERVATION MENU");
        
        while (reservation == null) 
        {
            reservation = getReservationByLastName();
        }
        
        System.out.println(" For this reservation, what would you like to change?");
        System.out.println(" 1. Cancel it");        
        System.out.println(" 2. Change room");
        System.out.println(" 3. Check in");
        System.out.println(" 4. Check out");
        System.out.println(" 0. Return to the main menu");
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = =");
        
        int selectionInt = getUserInputInt(0,4); 
        
        switch( selectionInt )
        {
            case 0:
                mainMenu();
                break;
            case 1:
                try {
                    reservation.setStatus(Status.CANCELED);
                    System.out.println("Successfully cancelled");
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Hmm, that won't work because: " + e);
                    System.out.println("You'll have to type the name again.");
                    changeReservationMenu();
                }
                
            case 2:
                System.out.println(" EMPTY ROOMS:");
                for (Room r : hotel.getEmptyRooms())
                {
                    System.out.println(r);
                }
                System.out.println(" Enter the room number you want to change to (ie 301) or 0 to cancel:");
                
                //System.out.println(hotel.getEmptyRooms());
                newRoomNumber = input.next();                
                newRoom = hotel.getRoom(newRoomNumber);
                if(newRoomNumber.equals("0")) {
                    mainMenu();             
                }
                
                while ( newRoom == null || !newRoom.isAvailable())
                {
                    System.out.println(" Room not entered correctly or already reserved, try again");
                    newRoomNumber = input.next();                
                    newRoom = hotel.getRoom(newRoomNumber);
                }if(newRoomNumber.equals("0")) {
                    mainMenu();
                    break;    
                }                
                
                reservation.setRoom(newRoom);
                System.out.println("Successfully changed room to room # " + newRoomNumber);
                break;
                
            case 3:
                try {
                    reservation.setStatus(Status.IN);
                    System.out.println("Successfully checked in.");
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Hmm, that won't work because: " + e);
                    System.out.println("You'll have to type the name again.");
                    changeReservationMenu();
                }
                
            case 4:
                try {
                    reservation.setStatus(Status.OUT);
                    System.out.println("Successfully checked out.");
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Hmm, that won't work because: " + e);
                    System.out.println("You'll have to type the name again.");
                    changeReservationMenu();
                }
        }
        returnToMainMenuPrompt();
    }       
    
    /**
     * Menu to view information about the status of a reservation. 
     * Is it checked in, checked out, or canceled?
     */
    private static void reservationStatusMenu() throws FileNotFoundException 
    {
        Reservation reservation = null;        
        
        // print a blank line followed by menu title
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = =");
        System.out.println(" RESERVATION STATUS MENU");        
        
        while(reservation == null) 
        {
            reservation = getReservationByLastName();
        }
        
        System.out.println(" For this reservation, what would you like to change?");
        
        System.out.println(" 1. Cancel it");        
        System.out.println(" 2. Checkin");
        System.out.println(" 3. Checkout");
        System.out.println(" 0. Return to the main menu");
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = =");
        
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
     * Menu to see an existing reservation's guest information.
     */
    private static void guestMenu() throws FileNotFoundException 
    { 
        System.out.println("please make a selection: ");
        System.out.println("1. Look up a guest by name");
        System.out.println("2. See all guest information in our system");
        int selection = getUserInputInt(1,2);
        ArrayList<Reservation> guestReservations = new ArrayList<>(); // this will hold all reservations the guest has ever made

        if(selection == 1) {
            System.out.println("Please enter the last name of the guest");
            String name = input.next();
            guestReservations = hotel.getReservationsByLastName(name); // fill the array
            if(guestReservations.isEmpty()) {
                // will be empty if we don't find the guest
                System.out.println("We couldn't find any guests with that name");
                returnToMainMenuPrompt();
            }
            else {
                // the first print statement prints the guest (all the same guest, so idx 0)
                System.out.println(guestReservations.get(0).getGuest());
                //this prints their resrvation history
                for(Reservation r : guestReservations) {
                    System.out.println(guestReservations);
                    System.out.println();
                }
            }
        }
        else {
            // this saves us adding a method elsewhere
            guestReservations = hotel.getInactiveReservations();
            guestReservations.addAll(hotel.getActiveReservations());
            for(Reservation r : guestReservations) {
                Guest g = r.getGuest();
                System.out.println(g);
                System.out.println();
            }
        }

        returnToMainMenuPrompt();
    }
    
    /**
     * Menu to search for an invoice and view the information in it.
     */
    private static void invoiceMenu() throws FileNotFoundException 
    { 
        Reservation reservation = null;
        
        // print a blank line followed by menu title
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = =");
        System.out.println(" INVOICE MENU");        
        System.out.println(" 1. Search invoices on Guest Last Name");        
        System.out.println(" 2. See all paid invoices");
        System.out.println(" 3. See all unpaid invoices");      
        System.out.println(" 0. Return to the main menu");
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = =");
        
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
     * Menu to see all available rooms.
     */
    private static void availableRoomsMenu() throws FileNotFoundException 
    {   
        // print a blank line followed by menu title
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = =");
        System.out.println(" AVAILABLE ROOMS MENU:");
        
        System.out.println( " These rooms are available:");
        for (Room r: hotel.getEmptyRooms())
        {
            System.out.println(r);
        }
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = =");
        returnToMainMenuPrompt();
    }
    
    /**
    * Menu to display a report for internal hotel use.
    * check hotel occupancy (how many guests are currently in hotel)
    * check how many rooms are occupied
    * check how many checkouts have occured
    * check how many cancellations for occured
    * check total sales and total amountDue
    */
    public static void reportMenu()  throws FileNotFoundException 
    {                
        // print a blank line followed by menu title
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = =");
        System.out.println(" HOTEL REPORT MENU:");
        System.out.println(" Total rooms in hotel: " + hotel.getAllRoomsCount());
        System.out.println(" Total rooms reserved (not checked in): " + hotel.getTotalReservedRooms());
        System.out.println(" Total rooms checked in: " + hotel.getTotalOccupiedRooms());
        System.out.println(" Total guests checked into hotel: " + hotel.getTotalGuestsInHotel());
        System.out.println(" Total checkouts: " + hotel.getTotalCheckedOutReservations());
        System.out.println(" Total cancellations: " + hotel.getTotalCanceledReservations());
        
        System.out.println(" Total amount due on active unpaid reservations: " + String.format("$ %.2f", hotel.getTotalPaymentDue()));
        System.out.println(" Total sales: " + String.format("$ %.2f", hotel.getTotalSales()));
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = =");
        returnToMainMenuPrompt();
    }
    
    /**
     * Menu to display information on how to use this program.
     */
    private static void helpMenu() throws FileNotFoundException 
    {
        // print a blank line followed by menu title
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = =");
        System.out.println(" HELP MENU:");
        System.out.println(" 1. Reserving a room: " + '\n' +
            '\t' + "You cannot reserve a room unless it is available (no one else has reserved it). " + '\n' +
            '\t' + "At this time, dates are not factored into our program.");
        System.out.println(" 2. Changing a reservation: " + '\n' +
            '\t' + "To locate an existing reservation, access it by the Guest's last name." + '\n' +
            '\t' + "If a guest has multiple reservations with us, then access it by room number.");
            // this only works if the guest hasn't stayed in the same room each time.
            // what about by reservation ID, is that too difficult to set up?
        System.out.println(" 3. Discounts: (only the highest rate is applied)" + '\n' +
            '\t' + "Active duty military: 7% discount" + '\n' +
            '\t' + "Government employees: 9% discount" + '\n' +
            '\t' + "Hotel members: 5% discount");
        System.out.println(" 4. Room Types:" + '\n' +
            '\t' + "Regular Room: $150.00/night" + '\n' +
            '\t' + "Large Room: $170.00/night" + '\n' +
            '\t' + "Suite: $190.00/night" + '\n' +
            '\t' + "Floors 6-10: Additional 3% per night");
        System.out.println("= = = = = = = = = = = = = = = = = = = = = = = =");
        returnToMainMenuPrompt();
    }
    
    
    // this is going to trim our list of available rooms based on user input
    private static ArrayList<Room> roomOptionsTrimmer(int partySize, int priceRange, ArrayList<Room> rooms) {
        ArrayList<Room> returnList = new ArrayList<>();
        //System.out.println(rooms.size());
        Iterator<Room> itr = rooms.iterator();

        while(itr.hasNext()) {
            Room rm = itr.next();
            if(partySize <= rm.getCapacity()) {
                if(priceRange == 1 && rm.getRate() <= 175) {
                    returnList.add(rm);
                }
                else if(priceRange == 2 && rm.getRate() > 175) {
                    returnList.add(rm);
                }
            }

        }
        return returnList;
    }
}

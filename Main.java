import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Main class. Opens a file and scans the contents to create hotel object. And has the console app.
 *
 * @author Nick Coyle
 * @version 1/13/2019
 */
public class Main
{
    private static Scanner input;
    private static String selection; //should be a String, if it's hardcoded as an int, then the program will crash if in int is not entered.

    public static Hotel main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("HotelBurgerwithRooms.txt"));
        String name = scanner.nextLine();
        String address = scanner.nextLine();
        String phoneNumber = scanner.nextLine();
        Hotel hotelBurger = new Hotel(name, address, phoneNumber);

        System.out.println(hotelBurger);
        
        String roomNumber;
        int floor, numBeds;
        String bedType, roomType;
        Room room;
        while(scanner.hasNextLine()) {
            roomNumber = scanner.next();
            floor = scanner.nextInt();
            numBeds = scanner.nextInt();
            bedType = scanner.next();
            roomType = scanner.next();            
            switch(roomType) 
            {
                case "REGULAR":                     
                    room = new RegularRoom(roomNumber, floor, numBeds, BedType.DOUBLE, RoomType.REGULAR);
                    break;
                case "LARGE":
                    room = new LargeRoom(roomNumber, floor, numBeds, BedType.QUEEN, RoomType.LARGE);
                    break;
                case "SUITE":
                    room = new Suite(roomNumber, floor, numBeds, BedType.KING, RoomType.SUITE);
                    break;
                default:
                    room = new RegularRoom(roomNumber, floor, numBeds, BedType.DOUBLE, RoomType.REGULAR);
            }
            hotelBurger.addRoom(room);           
        }
        input = new Scanner(System.in);
        mainMenu(hotelBurger);  
        return hotelBurger;
    }

    private static boolean isInt(String s) {
        try 
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }        
    }

    private static void mainMenu(Hotel h) {    
        
        selection  = "";
        
        System.out.println();
        System.out.println(" Please make a selection from the following options and press enter");
        System.out.println(" 1. Make a reservation and checkin");
        System.out.println(" 2. Change a reservation or checkout"); 
        System.out.println(" 3. Cancel a reservation"); 
        System.out.println(" 4. Register a new guest"); 
        System.out.println(" 5. View all available rooms");
        System.out.println(" 6. Show invoice");        
        System.out.println(" 7. See help menu");        
        System.out.println(" 8. Quit/close +  save state");

        selection = input.next();        

        while(!isInt(selection) || Integer.parseInt(selection) < 1 || Integer.parseInt(selection) > 11) {
            System.out.println("Input not recognized, please try again");
            selection = input.next();  
        }        

        int selectionInt = Integer.parseInt(selection); 
        switch(selectionInt)
        {
            case 1:
                //makeReservationMenu();
                break;
            case 2:
                //changeReservationMenu();
                break;
            case 3:
                //cancelReservationMenu();
                break;
            case 4:
                //registerNewGuestMenu();
                break;
            case 5:
                //roomsMenu();
                break;
            case 6:
                invoiceMenu(h);
                break;
            case 7:
                helpMenu(h);
                break;            
            case 8:
                input.close();
                System.exit(0);                
                break;
        }
    }
    
    private static void invoiceMenu(Hotel h) {                 
        
        System.out.println(" Invoice Menu");        
        System.out.println(" 1. Search invoices on Guest Last Name");        
        System.out.println(" 2. See all unpaid invoices");
        System.out.println(" 0. Return to the main menu");
        
    
        selection = input.next();        

        while(!isInt(selection) || Integer.parseInt(selection) < 0 || Integer.parseInt(selection) > 2) {
            System.out.println("Input not recognized, please try again");
            selection = input.next();  
        }
        
        int selectionInt = Integer.parseInt(selection); 
        switch(selectionInt)
        {
            case 0:
                mainMenu(h);
                break;
            case 1:
                System.out.println();
                System.out.println(" Enter Guest's Last Name");
                selection = input.next();                
                //System.out.println(getInvoice(selection)); //returns a String
                break;
            case 2:
                //System.out.println(getUnpaidInvoices()); //returns a String                            
                break;
        }
        
        System.out.println();
        System.out.println(" Press 0 to exit");
        selection = input.next();
        while(!isInt(selection)) {
            selection = input.next();  
        }        
        
        invoiceMenu(h);            
    }
    
    private static void helpMenu(Hotel h) {    
            
        System.out.println(" Help Menu");
        System.out.println(" Press any key to return to the main menu");
    
        if(input.hasNext()) {
            selection = input.next();
            mainMenu(h);
        }
    }
    
 }

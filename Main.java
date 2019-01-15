import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Main class. Opens a file and scans the contents to create hotel object. Then has the console app.
 *
 * @author Nick Coyle
 * @version 1/13/2019
 */
public class Main
{
    private static Scanner input;
    private static String selection; //should be a String, if it's hardcoded as an int, then the program will crash if in int is not entered.

    public static void main(String[] args) throws FileNotFoundException {
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

        mainMenu();        
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

    private static void mainMenu() {    
        input = new Scanner(System.in);
        selection  = "";

        System.out.println(" Please make a selection from the following options and press enter");
        System.out.println(" 1. Make a reservation/ check guest in");
        System.out.println(" 2. Cancel reservation, check guest out");
        System.out.println(" 3. Modify existing reservation");
        System.out.println(" 4. See available rooms"); 
        System.out.println(" 5. See current guest occupancy");
        System.out.println(" 6. Look up an invoice");
        System.out.println(" 7. Look up a room");
        System.out.println(" 8. See all unpaid invoices");
        System.out.println(" 9. See help menu");
        System.out.println(" 10. Add guest");
        System.out.println(" 11. Quit/close +  save state");

        selection = input.next();        

        while(!isInt(selection) || Integer.parseInt(selection) < 1 || Integer.parseInt(selection) > 11) {
            System.out.println("Input not recognized, please try again");
            selection = input.next();  
        }        

        int selectionInt = Integer.parseInt(selection); 
        switch(selectionInt)
        {
            case 1:
                //reservation menu
                break;
            case 2:
                //
                break;
            case 3:
                //
                break;
            case 4:
                //
                break;
            case 5:
                //
                break;
            case 7:
                //
                break;
            case 8:
                //
                break;
            case 9:
                //
                break;
            case 10:
                //
                break;
            case 11:
                input.close();
                System.exit(0);                
                break;
        }
    }  

}
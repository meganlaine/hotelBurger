import java.util.*;
/**
 * Write a description of class GuestHandler here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GuestHandler
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class GuestHandler
     */
    public GuestHandler()
    {

    }

    public Guest guestFinder(String[] nameArr, ArrayList<Guest> guests) {
        Guest currGuest = null; // needs to be declared outside loop
        boolean guestFound = false;
        String name = nameArr[0] + " " + nameArr[1] + " " + nameArr[2];

        // This will iterate through all known guests and check if they are already in our DB
        for(Guest g : guests) { // loop goes through each guest 
            if(g.getFullName().compareTo(name) == 0) { // Checks to see if the name exists
                System.out.println("We found this guest in our database!"); 
                System.out.println();
                currGuest = g; // sets curr guest to the name given to console
                guestFound =true;
                break;
            }
        }

        if(!guestFound) {
            currGuest = new Guest(nameArr[0],nameArr[1],nameArr[2]); // makes new guest
            guests.add(currGuest); // adds the new guest to our ArrayList of guests
            System.out.println("We added this guest to our database!");
            System.out.println();
            // break; // need this to avoid concurrent exception
        }

        return currGuest;
    }

    /**
     * This method uses a scanner to build an array of strings which constitutes the guests
     * name. It concats these strings into a full name such as John X. Doe.
     */
    public String[] getGuestInfo(Scanner console) {
        String[] nameArr = new String[3]; // Need to store name somehow, may as well be this
        System.out.print("Please input first name of guest: ");
        nameArr[0] = console.next().trim();
        System.out.print("Please input middle initial of guest like this P. or Q.: ");
        nameArr[1] = console.next().trim();
        System.out.print("Please input last name of guest: ");
        nameArr[2] = console.next().trim();
        String name = nameArr[0] + " " + nameArr[1] + " " + nameArr[2];
        System.out.println();
        System.out.println("Guest name is: " + name);
        return nameArr;
    }

    public boolean[] getGuestBooleans(Scanner console) {
        boolean[] boolArr = new boolean[4];
        String input ="";
        System.out.print("is this guest Military? (y/N): ");
        input = console.next();
        if(input.equalsIgnoreCase("y"))  {
            boolArr[0] = true;
        }
        else {
            boolArr[0] = false;
        }

        System.out.println();
        System.out.print("Is this guest a government worker? (y/N): ");
        input = console.next();
        if(input.equalsIgnoreCase("y"))  {
            boolArr[1] = true;
        }
        else {
            boolArr[1] = false;
        }
        System.out.println();
        System.out.print("Is this guest a member? (y/N): ");
        input = console.next();
        if(input.equalsIgnoreCase("y"))  {
            boolArr[0] = true;
        }
        else {
            boolArr[0] = false;
        }
        boolArr[3] = false; // impossible for them to be checked in at this point, but still need this value;
        System.out.println();
        return boolArr;
    }
    
    public int getNumGuests(Scanner console) {
        System.out.print("How many guests will be occupying the room? ");
        System.out.println("Type an integer and press enter: ");
        int numGuests = console.nextInt();
        while(numGuests < 1 || numGuests > 4) {
            System.out.println("You must choose a number between 1 and 4");
            System.out.println("Type an integer and press enter: ");
            numGuests = console.nextInt();
        }
        
        return numGuests;
    }
    
    public int getGuestRoomNum(Scanner console) {
        System.out.print("Please input desired room number and press enter: ");
        int roomNumber = console.nextInt();
        return roomNumber;
    }
}

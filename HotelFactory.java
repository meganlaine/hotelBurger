import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This code reads a .txt file to generate an ArrayList of Room objects for a Hotel.
 * 
 * note: https://stackoverflow.com/questions/604424/how-to-get-an-enum-value-from-a-string-value-in-java
 *  
 * @author Megan Laine
 * @version 1/13/2019 CSC 143 Winter 2019
 */

public class HotelFactory
{
    // instance variables
    private ArrayList<Room> rooms;
    private String name;
    private String address;
    private String phoneNumber;

    // constructor method (1 total)
    /**
     * Constructor for objects of class Hotel.
     * 
     * @param fileName (String) representing a .txt file.
     * @throws FileNotFoundException if the file cannot be read or doesn't exist.
     */
    public HotelFactory( String fileName ) throws FileNotFoundException
    {
        fillArray(fileName);
    }

    /**
     * Reads data from a .txt file and stores it in this Room-object ArrayList.
     * Assumes that the text file is in a correct template. (Assume no mistakes in .txt file)
     *
     * @param fileName (String) representing a .txt file.
     * @throws FileNotFoundException if the file doesn't exist or cannot be read.
     * @throws IllegalArgumentException if the file doesn't match expected format.
     */
    public void fillArray( String fileName ) throws FileNotFoundException
    {
        File inFile = new File(fileName);

        if (!inFile.canRead()) {
            throw new FileNotFoundException("File doesn't exist or can't be read.");
        }

        Scanner input = new Scanner(inFile);

        if (!input.hasNext()) {
            throw new IllegalArgumentException("File doesn't match expected format.");
        }

        // We expect line 1 to have the name of the hotel
        this.name = input.nextLine();

        // We expect line 2 to have the address of the hotel
        this.address = input.nextLine();

        // We expect line 3 to have the phone number of the hotel
        this.phoneNumber = input.nextLine();

        // We expect line 4 to have an int representing # of rooms in the hotel
        if (!input.hasNextInt()) {
            throw new IllegalArgumentException("File doesn't match expected format.");
        }
        int numberOfRooms = input.nextInt();
        input.nextLine();

        // Instantiate this Room-object ArrayList.
        this.rooms = new ArrayList<Room>();

        // Loop thru these lines to create Room objects to store in the ArrayList
        for (int i = 0; i < numberOfRooms; i++)
        {
            // We expect String roomNum.
            String roomNum = input.next();

            // We expect int floor.
            int floor = input.nextInt();

            // We expect double pricePerNight.
            double pricePerNight = input.nextDouble();

            // We expect int numberBeds.
            int numberBeds = input.nextInt();

            // We expect BedType BEDTYPE. SEE NOTE above for parsing Strings as enum values
            BedType bedtype = BedType.valueOf( input.next() );

            // We expect RoomType ROOMTYPE. SEE NOTE above for parsing Strings as enum values
            RoomType roomtype = RoomType.valueOf( input.next() );

            input.nextLine();

            // Construct a Room object and store it in this ArrayList<Room>.
            // since we decided numebr beds won't be included, i removed this from the call
            this.rooms.add(new Room( roomNum, floor, pricePerNight, bedtype, roomtype ));
        }

        input.close();

        for (int i = 0; i < rooms.size(); i++) {
            System.out.println(rooms.get(i));
        }

    }

    public ArrayList<Room> getRoomList() {
        return rooms;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getPhneNumber() {
        return phoneNumber;
    }
    

}

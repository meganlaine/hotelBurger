import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class to represent a Hotel. Has storage containers for all the Rooms and all the Reservations.
 * 
 * @field name:String the name of the Hotel
 * @field address:String the address of the Hotel
 * @field phoneNumber:String the phonenumber you can use to call the Hotel
 * @field rooms:ArrayList<Room> the container for all Rooms in the Hotel
 * @field reservations:ArrayList<Reservation> the container for all the reservations in the Hotel
 * 
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 1/17/2019
 */
public class Hotel
{
    private static Scanner input;
    private String name;
    private String address;
    private String phoneNumber;

    public ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations; 

    /**
     * This is the constructor that all other constructors will use.
     */
    public Hotel(String name, String address, String phoneNumber) {
        setName(name);
        setAddress(address);
        setPhoneNumber(phoneNumber);

        rooms = new ArrayList<Room>();

        reservations = new ArrayList<Reservation>();
    }

    /**
     * This is the default constructor if you pass no parameters, it just calls the other constructor will some default testing values.
     */
    public Hotel() {
        this("Burger Hotel!", "North Seattle College", "8675309");        
    }

    /**
     * Constructor used from Main client code. do not delete.
     * Reads data from the hotel text file, and populate the ArrayList<Room>
     */
    public Hotel( String fileName ) throws FileNotFoundException
    {     
        //call default constructor so all fields get initialized to something, including the ArrayLists
        this();
        File inFile = new File(fileName);
        input = new Scanner(inFile);

        if (!input.hasNext()) {
            throw new IllegalArgumentException("File doesn't match expected format.");
        }

        // We expect line 1 to have the name of the hotel
        String name = input.nextLine();
        // We expect line 2 to have the address of the hotel
        String address = input.nextLine();
        // We expect line 3 to have the phone number of the hotel
        String phoneNumber = input.nextLine();
        setName(name);
        setAddress(address);
        setPhoneNumber(phoneNumber);

        fillRoomArrayList();
    }

    /**
     * Reads data from a .txt file and stores it in this Room-object ArrayList.
     * Assumes that the text file is in a correct template. (Assume no mistakes in .txt file)
     *
     * @param fileName (String) representing a .txt file.
     * @throws FileNotFoundException if the file doesn't exist or cannot be read.
     * @throws IllegalArgumentException if the file doesn't match expected format.
     */
    public void fillRoomArrayList() throws FileNotFoundException
    {
        String roomNum;
        int floor;
        int capacity;
        String bedtype;
        String roomtype;
        Room room;
        
        while(input.hasNextLine()) {

            // We expect String roomNum.
            roomNum = input.next();

            // We expect int floor.
            floor = input.nextInt();            

            // We expect int capacity.
            capacity = input.nextInt();

            // We expect BedType BEDTYPE. SEE NOTE above for parsing Strings as enum values
            //bedtype = BedType.valueOf( input.next() );
            bedtype = input.next();

            // We expect RoomType ROOMTYPE. SEE NOTE above for parsing Strings as enum values
            roomtype = input.next();
            
            switch(roomtype)
            {
                case "REGULAR":
                room = new RegularRoom(roomNum, floor, capacity, bedtype);
                break;
                case "LARGE":
                room = new LargeRoom(roomNum, floor, capacity, bedtype);
                break;
                case "SUITE":
                room = new Suite(roomNum, floor, capacity, bedtype);
                break;
                default:
                room = new RegularRoom(roomNum, floor, capacity, bedtype);
                break;
            }            
            
            this.addRoom(room);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }  

    public void addRoom(Room room)
    {
        rooms.add(room);
    }

    // Checks every room and returns how many are empty
    public ArrayList<Room> getEmptyRooms() {
        ArrayList<Room> rms = new ArrayList<>();
        for(Room rm: rooms) {
            if(rm.isAvailable()) {
                rms.add(rm);
            }
        }
        return rms;
    }

    // Checks every room and returns how many are empty
    public ArrayList<Room> getOccupiedRoomsList() {
        ArrayList<Room> rms = new ArrayList<>();
        for(Room rm: rooms) {
            if(!rm.isAvailable()) {
                rms.add(rm);
            }
        }
        return rms;
    }

    // Checks every room and returns how many are full
    public int getOccupiedRooms() {
        int accum = 0;
        for(Room rm : rooms) {
            if(!rm.isAvailable()) { // check if room is occupied
                accum++;
            }
        }
        return accum;
    }

    public ArrayList<Room> getAllRooms() {
        return rooms;
    }

    // this is a method which allows us to search rooms by room number
    /**
     * This enables us to search through all rooms in the hotel and returns the room object
     * which corresponds to the room number argument.
     */
    public Room getRoom(String roomNumber) {
        for(Room rm: rooms) {
            if(rm.getRoomNumber().equals(roomNumber)) {
                return rm;
            }
        }
        return null;
    }

    public int getNumReservations() {
        return reservations.size();
    }
    
     public ArrayList<Reservation> getActiveReservations() {
        ArrayList<Reservation> arr = new ArrayList<>();
        for(Reservation r : reservations) {
            if(r.getStatus().equals(Status.IN) || r.getStatus().equals(Status.WAITING)) {
                arr.add(r);
            }
        }
        return arr;
    }

    public ArrayList<Reservation> getInactiveReservations() {
         ArrayList<Reservation> arr = new ArrayList<>();
        for(Reservation r : reservations) {
            if(r.getStatus().equals(Status.OUT) || r.getStatus().equals(Status.CANCELED)) {
                arr.add(r);
            }
        }
        return arr;
    }


    @Override
    public String toString() {
        String hotelString = "Hotel: ";
        hotelString += name + "\n";
        hotelString += address + "\n";
        hotelString += phoneNumber + "\n";
        return hotelString;
    }
}

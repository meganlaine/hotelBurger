import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * Class Hotel represents a hotel. It has (ArrayLists) for Rooms and Reservations.
 * 
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 1/22/2019
 */
public class Hotel
{
    /* INSTANCE VARIABLES */
    private static Scanner input;
    private String name;
    private String address;
    private String phoneNumber;
    private ArrayList<Reservation> reservations;
    private ArrayList<Room> rooms;
    
    /**
     * Hotel Constructor 1/1 (Constructor used from Main client code.)
     * Reads data from the hotel text file, and populates the ArrayList<Room>
     */
    public Hotel( String fileName ) throws FileNotFoundException
    {     
        rooms = new ArrayList<Room>();
        reservations = new ArrayList<Reservation>();
        
        File inFile = new File(fileName);
        input = new Scanner(inFile);

        if (!input.hasNext()) 
        {
            throw new IllegalArgumentException("File doesn't match expected format.");
        }

        // We expect line 1 to have the name of the hotel
        String name = input.nextLine();
        
        // We expect line 2 to have the address of the hotel
        String address = input.nextLine();
        
        // We expect line 3 to have the phone number of the hotel
        String phoneNumber = input.nextLine();
        
        // reset the name/address/phone number of the hotel
        setName(name);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        
        // 'fill' the room arraylist with different rooms available in the hotel
        fillRoomArrayList();
    }
    
    /* METHODS UTILIZED BY THE CONSTRUCTORS */

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
        
        while (input.hasNextLine())
        {
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
            
            switch( roomtype )
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
    
    public void addRoom(Room room)
    {
        rooms.add(room);
    }

    /**
     * Reads data from a .txt file and stores it in this Room-object ArrayList.
     * Assumes that the text file is in a correct template. (Assume no mistakes in .txt file)
     *
     * @param fileName (String) representing a .txt file.
     * @throws FileNotFoundException if the file doesn't exist or cannot be read.
     * @throws IllegalArgumentException if the file doesn't match expected format.
     */
    public void fillReservationArrayList(String fileName) throws FileNotFoundException
    {
        File inFile = new File(fileName);
        input = new Scanner(inFile);
        
        //variables needed to make reservations
        int partySize = 0;
        int nights = 0;
        String roomNumber;        
        String firstName;
        String lastName;
        String phoneNumber;
        boolean isMilitary;
        boolean isGov;
        boolean isMember;
        Status status;
        Room room;
        Guest guest;        
        Reservation reservation;
        
        while(input.hasNextLine()) 
        {
            // We expect int partySize.
            partySize = input.nextInt();            

            // We expect int nights.
            nights = input.nextInt();
            
            // We expect String roomNum.
            roomNumber = input.next();            
            
            // We expect String first name.
            firstName = input.next(); 
            
            // We expect String last name.
            lastName = input.next(); 
            
            // We expect String  phoneNumber.
            phoneNumber = input.next(); 
            
            //next get discount statuses
            isMilitary = input.nextBoolean(); 
            isGov = input.nextBoolean(); 
            isMember = input.nextBoolean(); 
            
            // We expect Status status.
            status = Status.valueOf( input.next() );
            
            //get the room from the roomNumber
            room = this.getRoom(roomNumber);

            //make a guest object
            guest = new Guest(firstName, lastName, phoneNumber, 
                isMilitary, isGov, isMember);
            
            // make the reservation
            reservation = new Reservation(room, guest, status, partySize, nights);
            
            this.addReservation(reservation);
        }
    }
    
    /**
     * This method "saves" persists the hotel data back to the text files from where the 
     * construction data were first read.
     * 
     * ArrayList of reservations needs to be sorted with canceled ones first before we save.
     * This is because when the program starts and reservations 
     * are read in from the txt file, if a room is reserved with status other than canceled, 
     * then the room will be flagged unavailable, but if the same room has a canceled reservation
     * later in the text file, it will think the room is unavailable (which is true)
     * and will crash trying to create the reservation on a room that is not available.
     */
    public void save() throws FileNotFoundException 
    {
        // sortReservations();
        PrintStream output = new PrintStream(new File("output.txt"));
        for(Reservation r : reservations) {
            Guest g = r.getGuest();
            Room room = r.getRoom();
            output.println();
            output.print(r.getPartySize() + " ");
            output.print(r.getNights() + " ");
            output.print(room.getRoomNumber() + " ");
            output.print(g.getFirstName() + " ");
            output.print(g.getLastName() + " ");
            output.print(g.getPhoneNum() + " ");
            output.print(g.isMilitary() + " ");
            output.print(g.isGovernment() + " ");
            output.print(g.isMember() + " ");
            output.print(r.getStatus());
        }
    }
    
    /* ACCESSOR METHODS */
    
    /**
     * Returns the hotel's name
     *
     * @return name (String) the hotel's name
     */
    public String getName() 
    {
        return name;
    }

    /**
     * Returns the hotel's address as a string.
     *
     * @return address (String) the hotel's address
     */
    public String getAddress() 
    {
        return address;
    }

    /**
     * Returns the hotel's phone number as a string.
     *
     * @return phoneNumber (String) the hotel's phone number
     */
    public String getPhoneNumber() 
    {
        return phoneNumber;
    }  

    /**
     * Returns an arraylist of Room objects that are available.
     * A room is considered available IF there is no reservation on it, and IF there are no
     * guests checked into the room.
     *
     * @return rms (ArrayList<Room>) ArrayList of available rooms in the hotel.
     */
    public ArrayList<Room> getEmptyRooms() 
    {
        ArrayList<Room> rms = new ArrayList<Room>();
        
        for (Room rm: rooms) 
        {
            if ( rm.isAvailable() ) 
            {
                rms.add(rm);
            }
        }
        return rms;
    }
    
    /**
     * Returns an arraylist of Room objects that are unavailable.
     * A reserved room is considered unavailable. 
     * A room with a checked in guest is considered unavailable.
     *
     * @return rms (ArrayList<Room>) ArrayList of unavailable rooms in the hotel.
     */
    public ArrayList<Room> getReservedRoomsList()
    {
        ArrayList<Room> rms = new ArrayList<Room>();
        
        for (Reservation r: reservations) 
        {
            if ( !r.getStatus().equals(Status.WAITING) ) 
            {
                rms.add(r.getRoom());
            }
        }
        return rms;
    }

    /**
     * Returns the number of reserved rooms in the hotel. 
     * A room is considered reserved if the reservation status is WAITING. 
     * 
     * @return accum (int) representing number of reserved rooms in the hotel.
     */
    public int getTotalReservedRooms() 
    {
        return getReservedRoomsList().size();
    }

     /**
     * Returns the number of occupied rooms in the hotel. 
     * A room with a reservation with status IN is considered occupied. 
     * 
     * @return accum (int) representing number of occupied rooms in the hotel.
     */
    public int getTotalOccupiedRooms() {
        int totalOccupied = 0;
        
        for (Reservation r: reservations) 
        {
            if (r.getStatus().equals(Status.IN)) 
            {
                ++totalOccupied;
            }
        }
        
        return totalOccupied;
    }
    
    /**
     * Returns the ArrayList<Room> that 'contains' all Room objects in this hotel.
     *
     * @return rooms (ArrayList<Room>) representing all Room objects in the hotel.
     */
    public ArrayList<Room> getAllRooms() 
    {
        return rooms;
    }

     /**
     * Returns the ArrayList<Room> that 'contains' all Room objects in this hotel.
     *
     * @return rooms (ArrayList<Room>) representing all Room objects in the hotel.
     */
    public int getAllRoomsCount() 
    {
        return rooms.size();
    }
    
    /**
     * Returns a room object when the room number matches the argument.
     * 
     * @param roomNumber (String) the room number being searched
     * @return rm or null (Room) the room object that matches the search. or null, if no match.
     */
    public Room getRoom(String roomNumber)
    {
        for (Room rm: rooms) 
        {
            if (rm.getRoomNumber().equals(roomNumber)) 
            {
                return rm;
            }
        }
        return null;
    }
    
    /**
     * Returns an arraylist of all reservations matching a guest's last name from 
     * hotel's list of reservations.
     * 
     * @param guestLastName (String) the last name to search by
     * @return reservationsByName (ArrayList<Reservation>) list of reservations whose last name matches the search criteria.
     */
    public ArrayList<Reservation> getReservationsByLastName(String guestLastName) 
    {
        ArrayList<Reservation> reservationsByName = new ArrayList<Reservation>();
        
        for (Reservation res: reservations) 
        {
            if (res.getGuest().getLastName().equals(guestLastName)) 
            {
                reservationsByName.add(res);
            }
        }
        return reservationsByName;
    }    
    
    /**
     * Returns a Reservation object when the reservationIDs match. Returns null if no match.
     * 
     * @param reservationID (int) the reservation ID to search for.
     * @return res or null (Reservation) the reservation object whose ID matches the search.
     */
    public Reservation getReservation(int reservationID) 
    {
        for (Reservation res: reservations)
        {
            if (res.getReservationID() == reservationID) 
            {
                return res;
            }
        }
        return null;
    }    
    
    /**
     * Returns the number of all reservations that the Hotel has (includes all statuses).
     *
     * @return (int) the number of all hotel reservations (all statuses)
     */
    public int getNumReservations()
    {
        return reservations.size();
    }

    /**
     * Returns an ArrayList of Reservation objects from the hotel if Reservation status is 
     * 'active'. 'Active' reservation = hotel is waiting for guest, OR guest is checked in.
     * 
     * @return ArrayList<Reservation> of all active reservations.
     */
    public ArrayList<Reservation> getActiveReservations() 
    {
        ArrayList<Reservation> arr = new ArrayList<Reservation>();
        
        for (Reservation r : reservations) 
        {
            if (r.getStatus().equals(Status.IN) || r.getStatus().equals(Status.WAITING)) 
            {
                arr.add(r);
            }
        }
        return arr;
    }

    /**
     * Returns an ArrayList of Reservation objects from the hotel if Reservation status is 
     * 'inactive'. 'Inactive' reservation = guest checked out, OR reservation canceled.
     * 
     * @return ArrayList<Reservation> of all inactive reservations.
     */
    public ArrayList<Reservation> getInactiveReservations()
    {
        ArrayList<Reservation> arr = new ArrayList<Reservation>();
        
        for (Reservation r : reservations) 
        {
            if (r.getStatus().equals(Status.OUT) || r.getStatus().equals(Status.CANCELED)) 
            {
                arr.add(r);
            }
        }
        return arr;
    }  
  
    /**
     * Returns an ArrayList of strings representing 'invoices' in the hotel 
     * where there is NO balance due.
     * 
     * @return ArrayList<String> of all 'invoices' that have been paid.
     */
    public ArrayList<String> getAllInvoicesPaid() 
    {
        ArrayList<String> invoices = new ArrayList<String>();
        
        for (Reservation res : reservations) 
        {
            if ( res.getPaymentDue() == 0 ) 
            {
                invoices.add( res.getInvoice() );
            }
        }
        return invoices;
    }
    
    /**
     * Returns an ArrayList of strings representing 'invoices' in the hotel 
     * where there is a balance due.
     * 
     * @return ArrayList<String> of all 'invoices' with outstanding balance.
     */
    public ArrayList<String> getAllInvoicesUnpaid() 
    {
        ArrayList<String> invoices = new ArrayList<String>();
        
        for (Reservation res : reservations) 
        {
            if (res.getPaymentDue() > 0)
            {
                invoices.add( res.getInvoice() );
            }
        }
        return invoices;
    }
    
    public Reservation findReservation(Guest guest){
        for(Reservation reserve: reservations) {
            if(reserve.getGuest().equals(guest)) {
                return reserve;
            }
        }
        return null;
    }
    
    public ArrayList<Reservation> getReservations(Status status){
        ArrayList<Reservation> res = new ArrayList<>();
        for(Reservation reserve: reservations) {
            if(reserve.getStatus() == status) {
                res.add(reserve);
            }
        }
        return res;
    }
    
    public ArrayList<String> getOccupiedRoomNum() {
        ArrayList<String> rms = new ArrayList<>();
        for(Room rm: rooms) {
            if(rm.isAvailable()) {
                rms.add(rm.getRoomNumber());
            }
        }
        return rms;
    }
    
    /**
     *  methods used in GUI
     */
    
    public ArrayList<String> getEmptyRoomNum() {
        ArrayList<String> rms = new ArrayList<>();
        for(Room rm: rooms) {
            if(!rm.isAvailable()) {
                rms.add(rm.getRoomNumber());
            }
        }
        return rms;
    }
    
    /* MUTATOR METHODS */
    
    /**
     * Method setName sets this hotel's name field.
     *
     * @param name (String) hotel name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Method setAddress sets this hotel's address field.
     *
     * @param address (String) hotel address
     */
    public void setAddress(String address) 
    {
        this.address = address;
    }

    /**
     * Method setPhoneNumber sets the hotel's phone number
     *
     * @param phoneNumber (String) hotel phone number
     */
    public void setPhoneNumber(String phoneNumber) 
    {
        // no validation...
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * Adds a new Reservation object to the Hotel's arrayList of reservation objects
     * 
     * @param r (Reservation) reservation object
     */
    public void addReservation(Reservation r) 
    {
        reservations.add(r);
    }
    
    /* OTHER METHODS */
    
    public void sortReservations() {
        for(int i = 0; i < reservations.size(); i++) {
            Reservation res = reservations.get(i);
            if(res.getStatus().equals(Status.CANCELED)) {
                reservations.remove(i); // removes from the list
                reservations.add(0, res); // adds back to list at position 0
                if(i > 0) {
                    i--;
                }

            }

        }
    }
    
    /**
     * Calculates the total of all paid reservations.
     */
    public double getTotalSales() {     
        double totalSales = 0.0;
        
        for(Reservation res : reservations) {
            totalSales += res.getAmountPaid();
        }   
        
        return totalSales;
    }
    
    /**
     * Calculates the total of all unpaid reservations
     */
    public double getTotalPaymentDue() {        
        double totalAmountDue = 0.0;
        
        for(Reservation res : reservations) {
            totalAmountDue += res.getPaymentDue();
        }
        
        return totalAmountDue;
    }
    
    /**
     * Gets a count of all the guests currently checked into the hotel accounting for party size
     * 
     */
    public int getTotalGuestsInHotel() {
        int countGuests = 0;
        
        for(Reservation res : reservations) {
            if(res.getStatus().equals(Status.IN)) countGuests += res.getPartySize();
        }
        
        return countGuests;
    }
    
     /**
     * Calculates how many reservations with Status OUT
     */
    public int getTotalCheckedOutReservations() {
        int totalCheckouts = 0;
        
        for(Reservation res : reservations) {
            if(res.getStatus().equals(Status.OUT)) ++totalCheckouts;
        }
        
        return totalCheckouts;
    }
    
    /**
     * Calculates how many reservations with Status CANCELED
     */
    public int getTotalCanceledReservations() {
        int totalCancellations = 0;
        
        for(Reservation res : reservations) {
            if(res.getStatus().equals(Status.CANCELED)) ++totalCancellations;
        }
        
        return totalCancellations;
    }
    
    /**
     * Method toString overrides Object class's toString method; returns info about the hotel.
     *
     * @return (String) with info about name, address, and phone number.
     */
    @Override
    public String toString() 
    {
        return "=========================" + "\n" +
            "Hotel: " + name + "\n" +
            address + "\n" +
            phoneNumber + "\n" +
            "=========================" + "\n";
    }
    
    /**
     * A method to test basic functionality of this class
     */
    public static void test() throws FileNotFoundException {
        Hotel testHotel = new Hotel("hotelrooms.txt");
        //do some stuff
        if (!testHotel.getName().equals("Hotel Burger"))
        {
            System.out.println("Hotel name is supposed to be Hotel Burger, but is " + testHotel.getName());
        }
    }
    
    public Reservation findReservation(String roomNum){
        for(Reservation reserve: reservations) {
            if(reserve.getRoom().getRoomNumber().equals(roomNum)&&(reserve.getStatus() == Status.IN || reserve.getStatus() == Status.WAITING)) {
                return reserve;
            }
        }
        return null;
    }
}

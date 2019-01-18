import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
public class Hotel
{

    private String name;
    private String address;
    private String phoneNumber;
    
    public ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;

    /**
     * 
     */
    public Hotel() {
        name = "Burger Hotel!";
        address = "North Seattle College";
        phoneNumber = "8675309";
        
        rooms = new ArrayList<Room>();
        reservations = new ArrayList<Reservation>();
    }

    /**
    * Nick's constructor
    */
    public Hotel(String name, String address, String phoneNumber) {
        setName(name);
        setAddress(address);
        setPhoneNumber(phoneNumber);

        rooms = new ArrayList<Room>();
        reservations = new ArrayList<Reservation>();
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
    public ArrayList<Room> getOccupiedRooms() {
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

     @Override
    public String toString() {
        String hotelString = "Hotel: ";
        hotelString += name + "\n";
        hotelString += address + "\n";
        hotelString += phoneNumber + "\n";
        return hotelString;
    }
    
 //   public ArrayList<Integer> getEmptyRoomNum() {
   //     ArrayList<Integer> rms = new ArrayList<>();
     //   for(Room rm: rooms) {
       //     if(!rm.isAvailable()) {
         //       rms.add(Integer.parseInt(rm.getRoomNumber()));
           // }
        //}
        //return rms;
   // }
    
  //  public ArrayList<Integer> getOccupiedRoomNum() {
    //    ArrayList<Integer> rms = new ArrayList<>();
      //  for(Room rm: rooms) {
        //    if(rm.isAvailable()) {
          //      rms.add(Integer.parseInt(rm.getRoomNumber()));
          //  }
        //}
        //return rms;
    //}

}

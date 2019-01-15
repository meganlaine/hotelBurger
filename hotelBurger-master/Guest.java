import java.util.*;
/**
 * Write a description of class Guest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Guest
{
    private String firstName;
    private String lastName;
    private String middleInitial;
    private String birthday;
    private String phoneNumber;
    private String email;
    private boolean isMilitary;
    private boolean isGovernment;
    private boolean isCheckedIn;
    private String roomReserved;
    private ArrayList<Room> history;

    /**
     * Constructor for objects of class Guest
     */

    // most basic constructor - mostly for testing and emergency registration
    public Guest(String first, String middle, String last) {
        firstName = first;
        lastName = last;
        middleInitial = middle;
        isMilitary = false;
        isGovernment = false;
        isCheckedIn = false;
        // membershipNumber = Membership.getNewNumber();
        //membershipNumber = -1;
        roomReserved = "";
        history = new ArrayList<>(); 
    }

    // most comprehensive constructor - best case scenario
    public Guest(String first, String middle, String last,String bDay, String phoneNum,
                 String guestEmail, boolean isMil, boolean isGov, boolean checkedIn, String roomReserved)
    {
        firstName = first;
        middleInitial = middle;
        lastName = last;
        birthday = bDay;
        phoneNumber = phoneNum;
        email = guestEmail;
        isMilitary = isMil;
        isGovernment = isGov;
        isCheckedIn = checkedIn;
        history = new ArrayList<>();
    }

    public String getFirst() {
        return firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        String name = firstName + " " + middleInitial + ". " + lastName;
        return name;
    }
    

    public String getEmail() {
        return email;
    }
    
    public String getRoomReserved() {
        return roomReserved;
    }

    public boolean isMilitary() {
        return isMilitary;
    }

    public boolean isGovernment() {
        return isGovernment;
    }
    
    public boolean isCheckedIn() {
        return isCheckedIn;
    }

    public void setRoomReserved(String rmNum) {
        roomReserved = rmNum;
    }
    
    public void setCheckedIn(boolean checkedIn) {
        isCheckedIn = checkedIn;
    }
    
    // we should have something for this when 
    public void makeMember() {
        
        }
   
    // simple method to add room to history
    public  void addRoomToHistory(Room rm) {
        history.add(rm);
    }
    
    public void showGuestInfo() {
        System.out.println("Name: " + firstName + " " + middleInitial + " " + lastName);
        System.out.println("Birthday: " + birthday);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Room reserved: " + roomReserved);
        
    }
}

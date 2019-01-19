import java.util.ArrayList;
import java.lang.IllegalArgumentException;

/**
 * Guest class models a customer of a hotel. The guest is a person with a name and various personal information. 
 * Guests might have discounts applied based on membership to the hotel or status as a veteran or senior citizen so
 * we have fields for those data.
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 1/17/2019
 */
public class Guest
{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int partySize;
    private int nights;
    private boolean isMilitary;
    private boolean isGovernment;
    private boolean isMember;

    /** this is a constructor for testing, so that we don't have to create a full guest object
     * eveytime we make a new reservation. We can take it out before submission.
     **/
    public Guest(String first, String last) {
        firstName = first;
        lastName = last;
        phoneNumber = "";
        partySize = -1;
        nights = -1;
        isMilitary = false;
        isGovernment = false;
        isMember = false;

    }

    // more comprehensive constructor 
    public Guest(String first, String last, String phoneNum,
    int partySize, int nights, boolean isMil, boolean isGov, boolean member)
    {
        firstName = first;
        lastName = last;
        phoneNumber = phoneNum;
        this.partySize = partySize;
        this.nights = nights;
        phoneNumber = phoneNum;
        isMilitary = isMil;
        isGovernment = isGov;
        isMember = member;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNum() {
        return phoneNumber;
    }

    public String getFullName() {
        String name = firstName + " " + lastName;
        return name;
    }

    public int getPartySize() {
        return partySize;
    }

    public int getNights() {
        return nights;
    }

    public boolean isMilitary() {
        return isMilitary;
    }

    public boolean isGovernment() {
        return isGovernment;
    }

    public boolean isMember() {
        return isMember;
    }

    // sets first name, if String is empty, throws exception
    public void setFirstName(String first) {
        if(first.isEmpty()) {
            throw new IllegalArgumentException("Empty String");
        }
        firstName = first;
    }

    public void setLastName(String last) {
        if(last.isEmpty()) {
            throw new IllegalArgumentException("Empty String");
        }
        lastName = last;
    }

    // This checks if every character is an integer and if the number is 10 digits.
    // If it is, the method sets the phone number field to the argument provided
    public void setPhoneNum(String phoneNum) {
        for (char c : phoneNum.toCharArray())
        {
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("String is not numeric");
            }
        }
        if(phoneNum.length() != 10) {
            throw new IllegalArgumentException("Too many/ too few numbers");
        }

        phoneNumber = phoneNum;
    }

    public void setPartySize(int num) {
        if(num < 1 || num > 6) {
            throw new IllegalArgumentException("The number must be between 1 and 6");
        }

        partySize = num;
    }

    public void setMil(boolean m) {
        isMilitary = m;
    }

    public void setGovt(boolean g) {
        isGovernment = g;
    }

    public void setMembership(boolean m) {
        isMember = m;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public String toString() {
        return firstName + " " + lastName + " , " + phoneNumber + "\n" +
        "Party: " + partySize + ", nights: " + nights +  "\n" + 
        "Military: " + isMilitary + " , " + "Government: " + isGovernment + "\n" +
        "Member: " + isMember;
    }
    
    public boolean equals(Guest guest){
    boolean equals = false;
    if(this.getFullName().equals(guest.getFullName()) && this.getPhoneNum().equals(guest.getPhoneNum()))
        equals = true;
    return equals;
}


    public String getRoomReserved(Hotel h){
        return h.findReservation(this).getRoom().getRoomNumber();
    }
    
    public boolean isCheckedIn(Hotel h){
Status status = h.findReservation(this).getStatus();
    if(status == Status.IN)
        return true;
        return false;
    }
}

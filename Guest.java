import java.util.ArrayList;
import java.lang.IllegalArgumentException;

/**
 * Guest class models a customer of a hotel. The guest is a person with a name and various 
 * personal information. Guests might have discounts applied based on membership to the 
 * hotel or status as a veteran or government employee so we have fields for those data.
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 1/19/2019
 */
public class Guest
{
    /* INSTANCE VARIABLES */
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int partySize;
    private int nights;
    private boolean isMilitary;
    private boolean isGovernment;
    private boolean isMember;

    /**
     * Guest Constructor 1/2: "this is a version for testing, so we don't have to create a 
     * full guest object everytime we make a new reservation. 
     * we can take it out before submission. -dale"
     *
     * @param first (String) guest's first name
     * @param last (String) guest's last name
     */
    public Guest(String first, String last)
    {
        setFirstName(first);
        setLastName(last);
        phoneNumber = "";
        partySize = -1;
        nights = -1;
        isMilitary = false;
        isGovernment = false;
        isMember = false;
    }
    
    /**
     * Guest Constructor 2/2: The full Guest constructor that takes into account all 
     * parameters about a Guest.
     *
     * @param first (String) guest's first name
     * @param last (String) guest's last name
     * @param phoneNum (String) guest's phone number
     * @param partySize (int) how many people in the group, including reserving guest.
     * @param nights (int) how many nights the guest+party will stay at hotel
     * @param isMil (boolean) true if the guest qualifies for military discount
     * @param isGov (boolean) true if the guest qualifies for government discount
     * @param member (boolean) true if the guest qualifies for membership discount
     */
    public Guest( String first, String last, String phoneNum,
                    int partySize, int nights, 
                    boolean isMil, boolean isGov, boolean member )
    {
        setFirstName( first );
        setLastName( last );
        setPhoneNum( phoneNum );
        setPartySize( partySize );
        setNights( nights );
        setMil( isMil );
        setGovt( isGov );
        setMembership( member );
    }
    
    /* ACCESSOR METHODS */
    
    /**
     * Method getFirstName returns the guest's first name
     *
     * @return (String) guest's first name
     */
    public String getFirstName()
    {
        return firstName;
    }
    
    /**
     * Method getLastName returns the guest's last name
     *
     * @return (String) guest's last name
     */
    public String getLastName() 
    {
        return lastName;
    }

    /**
     * Method getPhoneNum returns the guest's phone number
     *
     * @return (String) guest's phone number
     */
    public String getPhoneNum() 
    {
        return phoneNumber;
    }

    /**
     * Method getFullName returns guest's first and last name combined in one string
     *
     * @return (String) guest's first and last name combined in one string
     */
    public String getFullName() 
    {
        return firstName + " " + lastName;
    }

    /**
     * Method getPartySize returns how many people are in the guest's party (including guest)
     *
     * @return (int) the size of the guest's party
     */
    public int getPartySize() 
    {
        return partySize;
    }

    /**
     * Method getNights returns how many nights the guest is staying
     *
     * @return (int) the number of nights the guest is staying
     */
    public int getNights() 
    {
        return nights;
    }

    /**
     * Method isMilitary returns true if the guest qualifies for military discount
     *
     * @return true if the guest qualifies for military discount
     */
    public boolean isMilitary() 
    {
        return isMilitary;
    }

    /**
     * Method isGovernment returns true if the guest qualifies for government discount
     *
     * @return true if the guest qualifies for government discount
     */
    public boolean isGovernment() 
    {
        return isGovernment;
    }

    /**
     * Method isMember returns true if the guest qualifies for membership discount
     *
     * @return true if the guest qualifies for membership discount
     */
    public boolean isMember() 
    {
        return isMember;
    }
    
    /* MUTATOR METHODS */
    
    /**
     * Method setFirstName sets the guest's first name. An empty string is an invalid name.
     *
     * @param first (String) representing guest's first name
     * @throw IllegalArgumentException if an empty string is passed
     */
    public void setFirstName(String first) 
    {
        if (first.isEmpty())
        {
            throw new IllegalArgumentException("First Name can't be an Empty String");
        }
        
        firstName = first;
    }
    
    /**
     * Method setLastName sets the guest's last name. An empty string is an invalid name.
     *
     * @param last (String) representing guest's last name
     * @throw IllegalArgumentException if an empty string is passed
     */
    public void setLastName(String last) 
    {
        if (last.isEmpty())
        {
            throw new IllegalArgumentException("Last Name can't be an Empty String");
        }
        
        lastName = last;
    }
    
    /**
     * Method setPhoneNum sets the guest's phone number, if valid. a valid phone number is
     * 10 digits, like 2061234567.
     *
     * @param phoneNum (String) representing guest's phone number
     * @throw IllegalArgumentException if the phone number does not match a 10digit string.
     */
    public void setPhoneNum(String phoneNum) 
    {
        for (char c : phoneNum.toCharArray())
        {
            if (!Character.isDigit(c))
            {
                throw new IllegalArgumentException("String is not numeric");
            }
        }
        
        if (phoneNum.length() != 10)
        {
            throw new IllegalArgumentException("Too many/ too few numbers");
        }

        phoneNumber = phoneNum;
    }

    /**
     * Method setPartySize sets the guest's party size, if valid. a valid party size is 
     * 1-6. The higher number, 6, could depend on the hotel's fire codes.
     *
     * @param num (int) representing how many people are in the reserving party
     * @throw IllegalArgumentException if the party is smaller than 1 or bigger than 6
     */
    public void setPartySize(int num) 
    {
        if (num < 1 || num > 6)
        {
            throw new IllegalArgumentException("The number must be between 1 and 6");
        }

        partySize = num;
    }
    
    /**
     * Method setNights sets the number of nights the guest is staying, if valid. a valid 
     * stay cannot be less than 1 night, and cannot be more than 90 days.
     *
     * @param nights (int) representing how many nights the guest will stay
     * @throw IllegalArgumentException if the number of nights is less than 1, more than 90.
     */
    public void setNights(int nights) 
    {
        if ( nights < 1 || nights > 90 ) 
        {
            throw new IllegalArgumentException("Night must be between 1 and 90");
        }
        
        this.nights = nights;
    }

    /**
     * Method setMil sets the military discount status of the guest.
     *
     * @param m (boolean) representing military discount status of guest.
     */
    public void setMil(boolean m) 
    {
        isMilitary = m;
    }

    /**
     * Method setGovt sets the government discount status of the guest.
     *
     * @param g (boolean) representing government discount status of guest.
     */
    public void setGovt(boolean g) 
    {
        isGovernment = g;
    }

    /**
     * Method setMembership sets the membership discount status of the guest.
     *
     * @param m (boolean) representing membership discount status of guest.
     */
    public void setMembership(boolean m) 
    {
        isMember = m;
    }
    
    /* OTHER METHODS */
    
    /**
     * Method toString overrides Class Object's toString() method. it returns information 
     * about this Guest, including their name, phone number, party size, nights stayed, 
     * and discount status(es).
     *
     * @return (String) with information about the guest.
     */
    public String toString() 
    {
        return this.getFullName() + " , Phone:" + phoneNumber + "\n" +
            "Party: " + partySize + ", Nights: " + nights +  "\n" + 
            "Military: " + isMilitary + ", " + 
                "Government: " + isGovernment + ", " +
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

import java.lang.IllegalArgumentException;

/**
 * Class Reservation.
 * Allows creation of a Reservation object, modification of a Reservation object
 * (you can change its status from WAITING, IN, OUT, or CANCELED).
 * Allows access to see information about the Reservation state, and invoice-like
 * information.
 * The best way to access objects of Type Reservation is thru its unique ID.
 * NOTE: you can't change a Guest on the reservation at this moment. The logic 
 * behind that is you would cancel the reservation and create a new one for a new * guest. (of course the goodness of this is debatable)
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 01/17/2019
 */
public class Reservation
{
    // counter allows you to check how many reservation objects have been created
    private static int counter = 0;
    
    // instance variables
    private int reservationID; // should this be a String?? we might not want people to be able to alter this.
    private Room r;
    private Guest g;
    private Status status;
    private double paymentDue;
    
    /**
     * Constructor for Reservation object: it assigns a Room and a Guest object
     * to the reservation. It also assigns an ID number to the Reservation
     * 
     * @param r (Room) the Room listed on the reservation
     * @param g (Guest) the Guest listed on the reservation
     * @param status (Status) if WAITING or IN (when constructing a Reservation it doesn't make sense to have a Reservation that is already CANCELED or CHECKED OUT)
     */
    public Reservation( Room r, Guest g, Status status )
    {
        // call setRoom first 
        // (which will validate if room is available)
        // (it will also change room availability)
        setRoom( r );
        
        // initialize the status of the Reservation 
        // (it will validate the status change)
        setStatus( status );
        
        // initialize the Guest object
        // (no validation on guest object thus far)
        setGuest( g );
        
        // initialize setPaymentDue
        // (applies discounts based on Guest's booleans)
        setPaymentDue();
        
        // Everytime a new reservation object is made, increment a counter first and then assign that number to the reservationID
        // should reservationID be a String so that people can't alter it in later uses?
        this.reservationID = ++counter;
        
    }
    
    /* ACCESSOR METHODS */
    
    /**
     * Returns an int representing the Reservation object's id number.
     * 
     * @return (int) the Reservation object's id number.
     */
    public int getReservationID() {
    
        return this.reservationID;
        
    }
    
    /**
     * Returns a string that represents the charges for the room based on the
     * Room object on the reservation. Takes into account the guest's discounts
     * 
     * @return double representing nightly price of Room
     */
    public String getPaymentDue() {
    
        return this.paymentDue;
        
    }
    
    
    /**
     * Returns the status of the Reservation in string format.
     * 
     * @return (String) the reservation status.
     */
    public String getStatus() {
    
        return this.status;
        
    }
    
    /* MUTATOR METHODS */
    
    /**
     * sets the guest object. at the moment doesnt have any safety measures.
     *
     * @param g (Guest) the guest to assign to this Reservation object
     */
    public void setGuest( Guest g ) {
        
        this.g = g;
        
    }
    
    /**
     * Calculates and sets what payment is due based on this Reservation's
     * Room type and Guest discounts.
     * Assumptions: govt = 9%, mil = 7%, member = 5%;
     * highest rate only is applied.
     */
    public void setPaymentDue() {
        
        double result = r.getRate() * g.getNights();
            
        // if guest is government employee, apply the highest discount: 9%
        if ( g.isGovernment() ) {
            
            result *= 0.91;
        
        }
        
        // if guest is not government but military, apply second highest discount: 7%
        else if ( !g.isGovernment() && g.isMilitary() ) {
            
            result *=  0.93;
                
        }
        
        // if guest is member only (not govt, not mil), apply the lowest discount (5%)
        else ( !g.isGovernment && !g.isMilitary && g.isMember ) {
            
            result *= 0.95;
            
        }
        
        // if guest is neither govt nor mil nor member, no discount is applied

        this.paymentDue = result;
        
    }
    
    /**
     * Sets the status of the Reservation. 
     * As a reminder, a status can be: (IN, OUT, CANCELED, WAITING)
     *
     * @param status (Status) the reservation status.
     * @throw IllegalArgumentException if the status change is illegal (the status already matches the change requested).
     */
    public void setStatus( Status s ) {
        
        // validate first that the Reservation status is not already what the user wants to change it to.
        if (this.Status == s) {
                
            throw new IllegalArgumentException("The reservation status is already what you're trying to change it to.");
                
        }
    
        // if wanting to change Status to CANCELED,
        // make the room available, and zero out room charges
        if ( s == Status.CANCELED ) {
            
            r.setAvailable(true);
            
            this.setPaymentDue(0.0);
            
        }
        
        // case OUT, set the Reservation status to OUT, and free up the room
        if ( s == Status.OUT ) {
            
            r.setAvailable(true);
            
        }

        // case WAITING, set the Reservation status to WAITING, and hold the room for guest
        if ( s == Status.WAITING ) {
            
            r.setAvailable(false);
            
        }
        
        // if you've made it here, it means you can safely change the status of the reservation
        this.Status = s;
        
    }
    
    /**
     * Sets the Reservation's room. Validates that the room in question is available. Sets the room availability to false.
     * 
     * @param r (Room) the room to be placed on the Reservation.
     * @throw IllegalArgumentException if the room is not available.
     */
    public void setRoom( Room r ) {
    
        if ( !r.isAvailable() ) {
            throw new IllegalArgumentException("You can't make a reservation for this room; it is not available.")
        }
        
        this.r = r;
        
        r.setAvailable(false);
        
    }
    
    /**
     * Changes the Reservation's room from this to other by calling 
     * the pre-existing method "setRoom(Room r)";
     * Frees this room up before the change.
     * 
     * @param other (Room) the room to be placed on the Reservation.
     * @throw IllegalArgumentException if the room is not available.
     */
    public void changeRoom( Room other ) {
    
        if ( !other.isAvailable() ) {
            throw new IllegalArgumentException("You can't change to the room you requested; it is not available.")
        }
        
        // free up this Room for other guests.
        this.r.setAvailable(true);
        
        // calling this method will change the room on the reservation
        // it will also make it reserved/not-bookable by other guests.
        setRoom(other);
        
    }
    
    /**
     * Sets the Reservation's paymentDue field to 0.0; returns a string confirming payment.
     * Assume that payment is made in 1 lump sum.
     * Assume that payment can be made at any time of the reservation process.
     */
    public String payBill() {
    
        this.paymentDue = 0.0;
        
        return "Thank you, payment received. Balance is 0.";
        
    }
    
    /**
     * A better option?? This would require small changes to Class Hotel
     * Sets the Reservation's paymentDue field to 0.0 (simulates the action that
     * the hotel guest has paid the bill). Returns a double to the Hotel class
     * so that Hotel Class can increment a revenue field.
     *
     * Assume that payment is made in 1 lump sum.
     * Assume that payment can be made at any time of the reservation process.
     */
    /* A better option??
    public double payBill() {
    
        double hotelRevenue = this.paymentDue;
        
        this.paymentDue = 0.0;
        
        return hotelRevenue;
        
    } */
    
    /* OTHER METHODS */
    
    /**
     * Static method; Returns how many instances of Class Reservations have been 
     * made.
     *
     * @return counter (int) representing the # of Reservation objects made.
     */
    public static int countInstances() {
    
        return counter;
        
    }
    
    /**
     * Returns a String with information about this Reservation instance.
     * Shows all aspects about the reservation: 
     * Guest info, room info, reservation status, paymentDue info.
     *
     * @return (String) representing information about the Reservation.
     */
    @Override
    public String toString() {
        
        return "= = = = = = = = = = =" + '\n' +
               "Reservation #: " + getReservationID() + '\n' +
               "Status: " + getStatus() + '\n' +
               r.toString() + '\n' +
               "Guest: " + g.toString() + '\n' +
               "Payment due: " + getPaymentDue() + '\n' +
               "= = = = = = = = = = =";
        
    }
    
}

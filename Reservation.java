import java.lang.IllegalArgumentException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Reservation class models a reservation in a hotel. A reservation is a guarantee to a 
 * guest that they will have a room set aside for them during a specified range of dates. 
 * When they come to the hotel, they can checkin using that reservation. Then, they get the 
 * room key and can stay in the hotel for as many nights as they agreed to in the reservation.
 * Modifying a reservation means the guest can cancel the reservation, 
 * change any parameters about it, or checkout.
 * 
 * Reservation object has a Status: you can change WAITING, IN, OUT, or CANCELED.
 * Waiting = reservation has been made, but guest is not presently in the hotel.
 * In = reservation made, and guest is in the hotel.
 * Out = reservation completed, and guest is out of hotel.
 * Canceled = reservation canceled.
 * 
 * The best way to access objects of Type Reservation is thru its unique ID.
 * 
 * NOTE: you can't change a Guest on the reservation at this moment.
 * The logic behind that is you would cancel the reservation and 
 * create a new one for a new guest. (of course the goodness of this is debatable)
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 01/19/2019
 */
public class Reservation
{
    /* CLASS CONSTANTS */
    // how many reservation objects have been created - to generate reservation IDs
    private static int counter = 100;
    
    /* INSTANCE VARIABLES */
    private int reservationID; // should this be a String?? we might not want people to be able to alter this.
    private Room r;
    private Guest g;
    private Status status;
    private double paymentDue;
    
    /**
     * Constructor 1/1 for Reservation object: it assigns a Room and a Guest object
     * to the reservation. It also assigns an ID number to the Reservation
     * 
     * @param r (Room) the Room listed on the reservation
     * @param g (Guest) the Guest listed on the reservation
     * @param status (Status) status of the reservation.
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
        setGuest( g );
        
        // initialize setPaymentDue; (applies discounts based on Guest's booleans)
        if (this.status != Status.CANCELED) 
        {       
            setPaymentDue();
        }
        
        /* Everytime a new reservation object is made, increment a counter first and then 
         * assign that number to the reservationID;
         * Should reservationID be a String so that people can't alter it in later uses?? */
        this.reservationID = ++counter;
    }
    
    /* ACCESSOR METHODS */
    
    /**
     * Returns an int representing the Reservation object's id number.
     * 
     * @return (int) the Reservation object's id number.
     */
    public int getReservationID()
    {
        return this.reservationID;
    }
    
    /**
     * Returns a string that represents the charges for the room based on the
     * Room object on the reservation. Takes into account the guest's discounts
     * 
     * @return (double) representing nightly price of Room
     */
    public double getPaymentDue()
    {
        return this.paymentDue;
    }
    
    /**
     * Returns the status of the Reservation in string format.
     * 
     * @return (String) the reservation status.
     */
    public Status getStatus() 
    {
        return this.status;
    }
    
    /* MUTATOR METHODS */
    
    /**
     * Sets the guest object. at the moment doesnt have any safety measures.
     *
     * @param g (Guest) the guest to assign to this Reservation object
     */
    public void setGuest( Guest g ) 
    {
        this.g = g;
    }
    
    /**
     * setPaymentDue 1/2: overloaded method; Calculates and sets what payment is due based on 
     * this Reservation's Room type and Guest discounts. 
     * Assumptions: govt=9%, mil=7%, member=5%; only highest rate applied.
     */
    public void setPaymentDue() 
    {
        double result = r.getRate() * g.getNights();
            
        // if guest is government employee, apply the highest discount: 9%
        if ( g.isGovernment() ) 
        {
            result *= (1 - 0.09);
        }
        
        // if guest is not government but military, apply second highest discount: 7%
        else if ( !g.isGovernment() && g.isMilitary() )
        {
            result *= (1 - 0.07);
        }
        
        // if guest is member only (not govt, not mil), apply the lowest discount (5%)
        else if ( !g.isGovernment() && !g.isMilitary() && g.isMember() ) 
        {
            result *= (1 - 0.05);
        }
        
        // if guest is neither govt nor mil nor member, no discount is applied
        this.paymentDue = result;        
    }
    
    /**
     * setPaymentDue 2/2: overloaded method that sets paymentDue to 0.0 if the reservation 
     * gets cancelled.
     * 
     * @param amount (double) the amount to set paymentDue to
     * @throw IllegalArgumentException if the amount set is less than 0.0.
     */
    private void setPaymentDue(double amount)
    {
        if (amount < 0) 
        {
            throw new IllegalArgumentException("Amount due cannot be less than 0");
        }
        
        this.paymentDue = amount;
    }
    
    /**
     * Sets the status of the Reservation. 
     * As a reminder, a status can be: (IN, OUT, CANCELED, WAITING)
     * An illegal status change is if trying to set the status to what it is already.
     *
     * @param status (Status) the reservation status, if valid.
     * @throw IllegalArgumentException if status change is illegal.
     */
    public void setStatus( Status s )
    {
        // validate first that the Reservation status is not already the argument.
        if (this.status == s)
        {
            throw new IllegalArgumentException("The reservation status is " + 
                "already what you're trying to change it to.");
        }
        
        // if wanting to change Status to CANCELED,
        // check first that the reservation is not already completed.
        // if a valid request: make the room available, and zero out room charges
        if ( s == Status.CANCELED )
        {
            if (this.getStatus() == Status.OUT) 
            {
                throw new IllegalArgumentException("You can't cancel an already completed reservation.");
            }
            r.setAvailable(true);
            
            this.setPaymentDue(0.0);
        }
        
        // to set status to IN, check first that this reservation was not already canceled.
        // if a valid request, set the room to 'unavailable'.
        if ( s == Status.IN )
        {
            if (this.getStatus() == Status.CANCELED) 
            {
                throw new IllegalArgumentException("Reservation was already canceled.");
            }            
            r.setAvailable(false);
        }
        
        // to set status to OUT, check first that this reservation was not already canceled.
        // if a valid request, free up the room
        if ( s == Status.OUT ) 
        {
            if (this.getStatus() == Status.CANCELED)
            {
                throw new IllegalArgumentException("The reservation was already canceled.");
            }            
            r.setAvailable(true);
        }

        // to set the status to WAITING, check first that this reservation was not 
        // already canceled or completed.
        // (if status was IN, ok to reset it to WAITING, if the guest was accidentally checked in)
        // if a valid request, hold the room for guest
        if ( s == Status.WAITING ) 
        {
            if (this.getStatus() == Status.CANCELED )
            {
                throw new IllegalArgumentException("Reservation already canceled.");
            }
            r.setAvailable(false);
        }
        
        // if you've made it here, it means you can safely change the status of the reservation
        this.status = s;
    }
    
    /**
     * This method returns the room object of this reservation.
     */
    public Room getRoom() 
    {
        return this.r;
    }
    
    /**
    * This method returns the guest object of this reservation.
    */
    public Guest getGuest() 
    {
        return this.g;
    }    
    
    /**
     * Sets the Reservation's room. Validates that the room in question is available. 
     * Sets the room availability to false.
     * 
     * @param r (Room) the room to be placed on the Reservation.
     * @throw IllegalArgumentException if the room is not available.
     */
    public void setRoom( Room r )
    {
        if ( !r.isAvailable() ) 
        {
            throw new IllegalArgumentException("You can't make a reservation for this room; " + 
                "it is not available.");
        }
        
        this.r = r;
        
        r.setAvailable(false);
    }
    
    /**
     * Changes the Reservation's room from this to other by calling the pre-existing method 
     * "setRoom(Room r)"; Frees this room up before the change.
     * 
     * @param other (Room) the room to be placed on the Reservation.
     * @throw IllegalArgumentException if the room is not available.
     */
    public void changeRoom( Room other )
    {
        if ( !other.isAvailable() ) 
        {
            throw new IllegalArgumentException("You can't change to the room you requested;"
                + " it is not available.");
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
    public String payBill() 
    {
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
     * Method to get invoice-like info on this reservation
     * 
     * @return (String) representing invoice information from the reservation.
     */
    public String getInvoice() 
    {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        
        return "\n" +
               "= = = = = = = = = = = = = = = = =" + "\n" +
               "Invoice# " + getReservationID() + 
                    " Reservation ID#: " + getReservationID() + "\n" +   
               "= = = = = = = = = = = = = = = = =" + "\n" +
               "Invoice Date: " + dateFormat.format(date) + "\n" +                           
               "Guest: " + g.getLastName() + "\n" +
               "Reservation status: " + status + "\n" + 
               "Payment due: " + getPaymentDue() + "\n" +
               "= = = = = = = = = = = = = = = = =";
    }   
    
    /**
     * Returns a String with information about this Reservation instance.
     * Shows all aspects about the reservation: 
     * Guest info, room info, reservation status, paymentDue info.
     *
     * @return (String) representing information about the Reservation.
     */
    @Override
    public String toString()
    {
        return "\n" +
               "= = = = = = = = = = = = = = = = = = = = = = = =" + '\n' +
               "Reservation ID#: " + getReservationID() + '\n' +
               "Status: " + getStatus() + '\n' +
               r.toString() + '\n' +
               "Guest: " + g.toString() + '\n' +
               "Payment due: " + getPaymentDue() + '\n' +
               "= = = = = = = = = = = = = = = = = = = = = = = =";
    }
}

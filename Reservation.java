
/**
 * Write a description of class Reservation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Reservation
{
    // instance variables - replace the example below with your own
    private int numberGuests;
    private Guest guest;
    private Room room;
    private Status status;
    /**
     * Constructor for objects of class Reservation
     */
    public Reservation(int numberGuests, Guest g, Room rm) {
        numberGuests = numberGuests;
        guest = g;
        room = rm;
        guest.setCheckedIn(true);
        room.setAvailable(false);
        status = Status.CHECKEDIN;
        rm.setGuestLastName(g.getLastName());
    }
    
    // Alternately, can create with just guest and room, which defaults to one
    public Reservation(Guest g, Room rm) {
        numberGuests = 1;
        guest = g;
        room = rm;
        guest.setCheckedIn(true);
        room.setAvailable(false);
        rm.setGuestLastName(g.getLastName());
    }
    
    public Reservation(int numberGuests, Guest g, Room rm, Status status) {
        numberGuests = numberGuests;
        guest = g;
        room = rm;
        guest.setCheckedIn(true);
        room.setAvailable(false);
        this.status = status;
        rm.setGuestLastName(g.getLastName());
    }
    
    public void cancelReservation() {
        guest.setCheckedIn(false);
        room.setAvailable(false);
    }

    public int getNumberGuests() {
        return numberGuests;
    }
    
    public Guest getGuest() {
        return guest;
    }
    
    public Room getRoom() {
        return room;
    }
    // This one is tricky - the room class currently has room number as an int, but the
    // proposal says String.
    public String getRoomNumber() {
        return room.getRoomNumber();
    }
    
    public String getReserver() {
        return guest.getFullName();
    }
}


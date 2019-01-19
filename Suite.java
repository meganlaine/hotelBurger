
/**
 * Class Suite for Hotel Burger.
 * $40.00/night more than abstract class Room
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 01/17/2019
 */
public class Suite extends Room
{
    private static final String ROOM_TYPE = "Suite";
    
    /**
     * Constructor for Suite object
     * Calls super method to construct Room object
     * 
     * @param roomNum (String) the room number of the room
     * @param floor (int) the floor where room located
     * @param capacity (int) the human capacity of the room
     * @param bedType (String) the bedtype of the room
     */
    public Suite(String roomNum, int floor, int capacity, String bedType)
    {
        super(roomNum, floor, capacity, bedType);
    }
    
    /* ACCESSOR METHODS */
    
    /**
     * Returns the price of the room per night, accounting for 
     * which floor the room is on and any special charges based on
     * Room - Suite (Suite room costs 40.0/night more than base Room).
     * 
     * @return double representing nightly price of Suite
     */
     @Override
     public double getRate()
     {
        return super.getBaseRate() + 40.0;
    
    }
    
    /* OTHER METHODS */
    
    /**
     * Overrides the abstract Room Class's toString() method. 
     * For example: 'Suite Room: 10SB, Floor: 10, BedType: KING, Capacity: 6,
     * Available?: true, PricePerNight: XXX.00'
     * 
     * @return String representing information about this Room
     */
    @Override
    public String toString() {
        
        return ROOM_TYPE + " " + super.toString() + 
                ", PricePerNight: " + this.getRate() +
                "\n";
        
    }
}


/**
 * Class Regular Room for Hotel Burger.
 * No additional costs from abstract class Room.
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 01/17/2019
 */
public class RegularRoom extends Room
{
    private static final String ROOM_TYPE = "Regular";
    
    /**
     * Constructor for RegularRoom object
     * Calls super method to construct Room object
     * 
     * @param roomNum (String) the room number of the room
     * @param floor (int) the floor where room located
     * @param capacity (int) the human capacity of the room
     * @param bedType (String) the bedtype of the room
     */
    public RegularRoom(String roomNum, int floor, int capacity, String bedType)
    {
        super(roomNum, floor, capacity, bedType);
    }
    
    /* ACCESSOR METHODS */
    
    /**
     * Returns the price of the room per night, accounting for 
     * which floor the room is on and any special charges based on
     * Room - Regular (Regular room has no extra charges).
     * 
     * @return double representing nightly price of RegularRoom
     */
     @Override
     public double getRate()
     {
        return super.getBaseRate();
    
    }
    
    @Override
    public String getRoomType(){
        return ROOM_TYPE;
    }
    /* OTHER METHODS */
    
    /**
     * Overrides the abstract Room Class's toString() method. 
     * For example: 'Regular Room: 405, Floor: 4, BedType: DOUBLE, Capacity: 4,
     * Available?: true, PricePerNight: XXX.00'
     * 
     * @return String representing information about this Room
     */
    @Override
    public String toString() {
        
        return ROOM_TYPE + " " + super.toString() + 
                ", PricePerNight: " + this.getRate();
        
    }
}

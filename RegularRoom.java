
/**
 * Write a description of class Suite here.
 *
 * @author Nick Coyle
 * @version 1/14/2019
 */
public class RegularRoom extends Room
{   
     /**
     * Constructor for objects of class RegularRoom
     */
    public RegularRoom(String roomNum, int floor,int capacity, BedType bedType, RoomType roomType) {
        super(roomNum, floor, capacity, bedType, roomType);
    }
    
    @Override
    public double getRate() {
        return super.getBaseRate() + 00.00;
    }
}

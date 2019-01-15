
/**
 * Write a description of class Suite here.
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 1/15/2019
 */
public class LargeRoom extends Room
{       
    /**
     * Constructor for objects of class LargeRoom
     */
    public LargeRoom(String roomNum, int floor,int capacity, BedType bedType, RoomType roomType) {
        super(roomNum, floor, capacity, bedType, roomType);
    }
    
    @Override
    public double getRate() {
        return super.getBaseRate() + 20.00;
    }
}

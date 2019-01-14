
/**
 * The class of the room object.
 *
 * @author Xingyu Liu
 * @version (a version number or a date)
 */
public class Room
{
    private String roomNum;
    private int floor;
    private double price;
    private BedType bedType;
    private RoomType roomType;
    private String guestLastName;
    private boolean isAvailable;
    private int guestNum;
    
    /**
     * Constructor for objects of occupied Room
     * 
     * @param roomNum the room number of the room
     * @param floor the floor where room located
     * @param price the price of the room
     * @param capacity capacity of the room
     * @param bedType the bedtype of the room
     * @param roomType the roomtype of the room
     * @param guestName the guest stays in the room
     * @param isAvailable the availability of the room
     * 
     * @throws IllegalArgumentException if the number of guests exceeds the capacity of the room
     */
    public Room(String roomNum, int floor,int guestNum, double price, BedType bedType, RoomType roomType, String guestLastName, boolean isAvailable)
    {
        //if(guestNum > capacity)
        //    throw new IllegalArgumentException("Exceeding the room capacity!");
        
        this.roomNum = roomNum;
        this.floor = floor;
        this.price = price;
        this.bedType = bedType;
        this.roomType = roomType;
        this.guestLastName = guestLastName;
        this.guestNum = guestNum;
        this.isAvailable = true;
    }
    
    /**
     * Constructor for objects of an empty room
     * 
     * @param roomNum the room number of the room
     * @param floor the floor where room located
     * @param price the price of the room
     * @param bedType the bedtype of the room
     * @param roomType the roomtype of the room
     * @param isAvailable the availability of the room
     */
    public Room(String roomNum, int floor, double price, BedType bedType, RoomType roomType, boolean isAvailable)
    {
        this.roomNum = roomNum;
        this.floor = floor;
        this.price = price;
        this.bedType = bedType;
        this.roomType = roomType;
        this.guestLastName =  "";
        this.isAvailable = true;
    }
    

    public Room( String roomNum, int floor, double pricePerNight, BedType bedType, RoomType roomType) {
        this.roomNum = roomNum;
        this.floor = floor;
        this.price = pricePerNight;
        this.bedType = bedType;
        this.roomType = roomType;
        this.guestLastName = " ";
        this.isAvailable = true;
    }
    
    
    /**
     * get the bed type of the room
     * 
     * @return the bed type of the room
     */
    public BedType getBedType(){
        return bedType;
    }
    
    /**
     * get the room type of the room
     * 
     * @return the room type of the room
     */
    public RoomType getRoomType(){
        return roomType;
    }
    
    
    /**
     * get the room number
     * 
     * @return the room number
     */
    public String getRoomNumber(){
        return roomNum;
    }
    
    /**
     * get the price of the room
     * 
     * @return the price of the room
     */
    public double getPrice(){
        return price;
    }
    
    public String getGuestLastName() {
        return guestLastName;
    }
    
    public void setAvailable(boolean bool) {
        isAvailable = bool;
    }
    
    /**
     * set the bed type of the room
     * 
     * @param bedType the bed type of the room
     */
    public void setBedType(BedType bedType){
        this.bedType = bedType;
    }
    
    /**
     * set the room type of the room
     * 
     * @param roomType the room type of the room
     */
    public void setRoomType(RoomType roomType){
        this.roomType = roomType;
    }
    
    
    /**
     * set the price of the room
     * 
     * @param price the price of the room
     */
    public void setPrice(int price){
        this.price = price;
    }
    
    public void setGuestLastName(String name) {
        guestLastName = name;
    }
    
    /**
     * check the availability of the room 
     *
     * @return ture if available
     */
    public boolean isAvailable()
    {
        return isAvailable;
    }
    
    /**
     * let guests check in the room 
     * 
     * @param guests the list of guest names checking in.
     * 
     * @throws IllegalArgumentException if the room is already in use.
     * @throws IllegalArgumentException if the number of guests exceeds the capacity of the room
     */
    public void checkIn(String guestLastName){
        if(!this.isAvailable())
            throw new IllegalArgumentException("The room is already in use!");
        
        guestLastName = guestLastName;
        isAvailable = false;
    }
    
    /**
     * get the number of guests in the room
     * 
     * @return the number of guests in the room
     */
    public int getGuestNum(){
        return guestNum;
    }
    
    /**
     * add one or more guests to the room
     * 
     * @param guests the list of guest names 
     * 
     * @throws IllegalArgumentException if the number of guests exceeds the capacity of the room
     */
    public void addGuest(int guestNum){
        
        this.guestNum += guestNum;
    }
    
    /**
     * checking out the guests in the room.
     * 
     * @throws IllegalArgumentException if the room is currently empty
     */
    public void checkOut(){
        if(isAvailable)
            throw new IllegalArgumentException("The room is already empty!");
            
        isAvailable = true;
        guestLastName = "";
        guestNum = 0;
    }
    
    /**
     * ckecking out the guests in the room, and return their names in a String[]
     * 
     * @return the name list for the guests.
     * 
     * @throws IllegalArgumentException if the room is currently empty
     */
    public String checkOutName(){
        if(isAvailable)
            throw new IllegalArgumentException("The room is already empty!");        
        
        isAvailable = true;
        String temp = guestLastName;
        guestLastName = "";
        guestNum = 0;
        return temp;
    }
    
    public void showRoomInfo() {
        System.out.println("Room number " + roomNum);
        System.out.println("Room price: " + price);
        System.out.println("Room bed type: " + bedType);
        System.out.println("Room Type: " + roomType);
        System.out.println("Current Occupant: " + guestLastName);
        System.out.println("currently occupied: " + isAvailable);
        System.out.println("Number of guests: " + guestNum);
    }
    
}
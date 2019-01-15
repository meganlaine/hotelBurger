import java.util.*;
public class Hotel
{

    private String name;
    private String address;
    private String phoneNumber;
    
    private ArrayList<Guest> guests;
    public ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;
    private ArrayList<Invoice> invoices;
    private GuestHandler gh;

    /**
     * 
     */
    public Hotel() {
        name = "Burger Hotel!";
        address = "North Seattle College";
        phoneNumber = "8675309";
        
        guests = new ArrayList<Guest>();
        rooms = new ArrayList<Room>();
        reservations = new ArrayList<Reservation>();
        invoices = new ArrayList<Invoice>();
        gh = new GuestHandler();
    }
    
    public Hotel(String hotelName, String hotelAddress, String phoneNumber, ArrayList<Room> rms) {
        name = hotelName;
        address = hotelAddress;
        phoneNumber = phoneNumber;
        
        rooms = rms;
        guests = new ArrayList<Guest>();
        reservations = new ArrayList<Reservation>();
        invoices = new ArrayList<Invoice>();
        gh = new GuestHandler();
    }

    /**
    * Nick's constructor
    */
    public Hotel(String name, String address, String phoneNumber) {
        setName(name);
        setAddress(address);
        setPhoneNumber(phoneNumber);

        guests = new ArrayList<Guest>();
        rooms = new ArrayList<Room>();
        reservations = new ArrayList<Reservation>();
        invoices = new ArrayList<Invoice>();
        gh = new GuestHandler();
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }  
    
    public void addRoom(Room room)
    {
        rooms.add(room);
    }
    
    // I'm not sure we need guest number and capactiy here, but for now, i'll leave it as is.
    public void addRoom(String roomNum, int floor,int capacity, BedType bedType, RoomType roomType)
    {
        rooms.add( new RegularRoom(roomNum, floor,capacity, bedType, roomType));
    }   

    public void addGuest(String first, String middle, String last, String bDay, String phoneNum,
    String guestEmail, boolean isMil, boolean isGov,
    boolean isMem, boolean isCheckedIn, String roomReserved) {
        guests.add(new Guest(first, middle, last, bDay, phoneNum,
                guestEmail, isMil, isGov,isCheckedIn, roomReserved));
    }
    
    public void addGuest(Guest g) {
        guests.add(g);
    }

    public void addGuest(String first, String middle, String last) {
        guests.add(new Guest(first, middle, last));
    }
    //this is only for testing, it makes our life easier.
    public void addReservation(Reservation r) {
        reservations.add(r);
    }

    /**
    public void checkOut(String name) { 
    for(Room rm: rooms) {
    if(rm.getOccupant().compareTo(name) == 0) {
    rm.setOccupied(false);
    rm.setOccupant("x");
    }
    }
    }
     **/

    // this works as an i/o method using a scanner
    public void makeReservation() {
        Scanner console = new Scanner(System.in);
        String[] nameArr = gh.getGuestInfo(console); // using an array is easiest to format

        // since Guest handler can only return one thing, we have to ask about number of guests here
        int numGuests = gh.getNumGuests(console);

        ArrayList<Room> choices = new ArrayList<>(); // this is making a deep copy of rooms (might be bad idea).
        for(Room rm : rooms) {
            choices.add(rm);
        }

        // this call manages our arraylist of choices which we will give to the customer at
        // the end of the prompt sequence.
        choices = choicesTrimmer(numGuests, choices);

        System.out.println();
        System.out.println("Do you want to create a profile with us? there may be discounts!");
        System.out.print("Type y for yes or N for no and press enter: ");                
        String input = console.next(); // Get the Y/N
        System.out.println();
        if(input.equalsIgnoreCase("y")){
            boolean[] boolArr = gh.getGuestBooleans(console); // case doesn't matter
        }

        showChoices(choices);
        int roomNumber = gh.getGuestRoomNum(console);

        String name = nameArr[0] + " " + nameArr[1] + " " + nameArr[2];
        Guest currGuest = gh.guestFinder(nameArr,guests);

        for(Room rm : rooms) {
            if(rm.getRoomNumber().equals(roomNumber)) { // finds room, checks if occupied
                rm.setGuestLastName(currGuest.getLastName());
                rm.setAvailable(false);
                System.out.println("Your room has been reserved! " + name +
                    " will be staying in room " + rm.getRoomNumber());
                System.out.println();
                reservations.add( new Reservation(numGuests,currGuest, rm));
                currGuest.setRoomReserved(rm.getRoomNumber());
                currGuest.addRoomToHistory(rm);
                break; // saves the program from too much checking
            }
            else if(rm.getRoomNumber().equals(roomNumber) && !rm.isAvailable()) {
                System.out.println("We found your room! " + roomNumber);
                System.out.print("this room is already occupied"); // only way we can get to this is if first test failed
                break;
            }
        }

        currGuest.showGuestInfo();

        continuePrompt(console);

        invoices.add(new Invoice(reservations.get(reservations.size()-1)));
        console.close();
    }

    public void displayMenu() {

        Scanner console = new Scanner(System.in);
        System.out.println(" Welcome to Burger Hotel!");
        System.out.println(" Please make a selection from the following options and press enter");
        System.out.println(" 1. Make a reservation/ check guest in");
        System.out.println(" 2. Cancel reservation, check guest out");
        System.out.println(" 3. Modify existing reservation");
        System.out.println(" 4. See available rooms"); 
        System.out.println(" 5. See current guest occupancy");
        System.out.println(" 6. Look up an invoice");
        System.out.println(" 7. Look up a room");
        System.out.println(" 8. See all unpaid invoices");
        System.out.println(" 9. See help menu");
        System.out.println(" 10. Add guest");
        System.out.println(" 11. Quit/close +  save state");
        int selection = console.nextInt();
        menuDirector(selection, console);
        console.close();
    }

    // Checks every room and returns how many are empty
    public ArrayList<Room> getEmptyRooms() {
        ArrayList<Room> rms = new ArrayList<>();
        for(Room rm: rooms) {
            if(!rm.isAvailable()) {
                rms.add(rm);
            }
        }
        return rms;
    }

    // Checks every room and returns how many are full
    public int getOccupiedRooms() {
        int accum = 0;
        for(Room rm : rooms) {
            if(!rm.isAvailable()) { // check if room is occupied
                accum++;
            }
        }
        return accum;
    }

    public int getNumberRooms() {
        return rooms.size();
    }

    public int getNumGuests() {
        return guests.size();
    }

    // this is a method which allows us to search rooms by room number
    /**
     * This enables us to search through all rooms in the hotel and returns the room object
     * which corresponds to the room number argument.
     */
    public Room getRoom(String roomNumber) {
        for(Room rm: rooms) {
            if(rm.getRoomNumber().equals(roomNumber)) {
                return rm;
            }
        }
        return null;
    }

    /**
     * This gives the entire rooms list
     */
    public ArrayList<Room> getRoomList() {
        return rooms;
    }

    public int getNumReservations() {
        return reservations.size();
    }

    public ArrayList<Guest> getGuestList() {
        return guests;
    }

    public Guest getGuest(String lastName) {
        for(Guest g : guests) {
            if(g.getLastName().compareTo(lastName) == 0) {
                return g;
            }
        }
        return null;
    }

    // public Guest getGuest(int memNumber) {
    // Scanner console = new Scanner(System.in);
    // for(Guest g : guests) {
    // if(g.getFullName().equals(name)) {
    // return g;
    // }
    // }
    // return null;
    // }

    private void menuDirector(int selection, Scanner console) {
        while(selection < 1 || selection > 11) {
            System.out.println("Input not recognized, please try again");
            System.out.println("Please enter your selection (a number from 1 - 4:");
            selection = console.nextInt();
        }

        if(selection == 1) {
            makeReservation();
        }

        else if(selection == 2){
            System.out.print("Please enter room number and press enter: ");
            String rmNum = console.next();
            
            // We need a room object to do stuff with 
            Room checkOutRoom = getRoom(rmNum);
            
            
            checkOutRoom.showRoomInfo();
            
            Guest checkOutGuest = getGuest(checkOutRoom.getGuestLastName());
            checkOutGuest.showGuestInfo();
            checkOutGuest.setCheckedIn(false);
            checkOutRoom.checkOut();
            
            System.out.print("This reservation has been cancelled and the guest has been checked out");
        }

        else if(selection == 3){

            System.out.println("Implement me!");
        }

        else if(selection == 4) {
            System.out.println("To look up the state specific room, enter 1");
            System.out.println("To see a list of all available rooms, enter 2");
            System.out.println("To return to the main menu, enter 3");
            System.out.println("Please enter a selection: ");
            int input = console.nextInt();
            if(input == 1) {
                System.out.println("You chose to look up A specific room! please enter your room number: ");
                String roomSelection = console.next();
                Room room = getRoom(roomSelection);
                room.showRoomInfo();
            }
            else if(input == 2) {            
                ArrayList<Room> emptyRooms = getEmptyRooms();
                for(Room rm : emptyRooms) {
                    rm.showRoomInfo();
                }
            }
            else if(input == 3) {
                displayMenu();
            }
        }

        else if(selection == 5) {

            System.out.println("To look up the info of a specific guest, enter 1");
            System.out.println("To see a list of all current occupants, enter 2");
            System.out.println("Please enter a selection: ");
            int lookUpInput = console.nextInt();
            if(lookUpInput == 1) {
                System.out.print("please enter the last name of the guest: ");
                String guestInfo = console.next();

                Guest result = getGuest(guestInfo);
                if(result == null) {
                    System.out.print("We couldn't find this guest");
                    // **** search reservations for this person's name and get room
                    //checkIn(input);
                }
                else {
                    result.showGuestInfo();
                }

            }

            if(lookUpInput == 2) {
                for(Guest g : guests) {
                    System.out.println(g.getFullName());
                    if(g.isCheckedIn()) {
                        g.showGuestInfo();
                        System.out.println();
                    }
                }

            }
        }

        else if(selection == 6) {

        }
        else if(selection == 7) {
            System.out.print("Please enter room number and press enter: ");
            String rmNum = console.next();
            Room r = getRoom(rmNum);
            System.out.println("Room number " + r.getRoomNumber() + " is " + r.isAvailable());
        }
        else if(selection == 8) {
            System.out.println("implement me!");
        }

        else if(selection == 9) {
            System.out.println("implement me!");
        }

        else if(selection == 10) {
            System.out.println("implement me!");
        }

        else {
            System.out.println("Goodbye!");
        }
    }

    private void continuePrompt(Scanner console) {
        System.out.println("To quit the program enter 1, or press any other key to return to");
        System.out.print(" the menu screen: ");
        String selection = console.next();
        if(selection.equals("1")) {
            System.out.println();
            System.out.println();
            System.out.println("GoodbyeGoodbyeGoodbyeGoodbyeGoodbyeGoodbyeGoodbyeGoodbye");
        }
        else{
            displayMenu();
        }
    }

    // currently this is nonsense from a logic POV, but we can tweak as we firm up our classes
    private ArrayList<Room> choicesTrimmer(int numGuests, ArrayList<Room> choices) {
        if(numGuests > 2) {
            for(int i = 0; i < choices.size(); i++) {
                Room rm = choices.get(i);
                if(rm.getBedType() == BedType.DOUBLE || rm.getBedType() == BedType.DOUBLE) {
                    choices.remove(rm);
                }
            }
        }
        else {
            for(int i = 0; i < choices.size(); i++) {
                Room rm = choices.get(i);
                if(rm.getBedType() == BedType.QUEEN || rm.getBedType() == BedType.KING) {
                    choices.remove(rm);
                }
            }
        }      
        return choices;
    }

    private void showChoices(ArrayList<Room> choices) {
        int counter = 0;
        for(Room rm: choices) {
            System.out.print("[" + rm.getRoomNumber() + ", " + rm.getBedType() + ", $"  + rm.getPrice() + "]");
            counter ++;
            if(counter == 5) {
                System.out.println();
                counter = 0;
            }
        }   
    }
    
     @Override
    public String toString() {
        String hotelString = "Hotel: ";
        hotelString += name + "\n";
        hotelString += address + "\n";
        hotelString += phoneNumber + "\n";
        return hotelString;
    }
    
        
    public ArrayList<Integer> getEmptyRoomNum() {
        ArrayList<Integer> rms = new ArrayList<>();
        for(Room rm: rooms) {
            if(!rm.isAvailable()) {
                rms.add(Integer.parseInt(rm.getRoomNumber()));
            }
        }
        return rms;
    }
    
    public ArrayList<Integer> getOccupiedRoomNum() {
        ArrayList<Integer> rms = new ArrayList<>();
        for(Room rm: rooms) {
            if(rm.isAvailable()) {
                rms.add(Integer.parseInt(rm.getRoomNumber()));
            }
        }
        return rms;
    }
    
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Write a description of class Simulator here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Simulator
{
    public static Hotel main(String[] args )throws FileNotFoundException {

        HotelFactory hotelFactory = new HotelFactory("hotelburger.txt");

        Hotel hotel = new Hotel(hotelFactory.getName(), hotelFactory.getAddress(), hotelFactory.getPhneNumber(), hotelFactory.getRoomList());
        
        for(int i = 0; i < hotel.getNumberRooms(); i++) {
            Room rm = hotel.rooms.get(i);
            rm.showRoomInfo();
            System.out.println();
            System.out.println();
    }

    // public static void main(String[] args) {
    // Hotel h = new Hotel();

    // Guest testGuest = new Guest("Dale","C","Berg");
    // Room testRoom = new Room(999,10,2,200.00,4,BedType.KING, RoomType.WEDNESDAY,"",true);
    // Reservation testReservation = new Reservation(1, testGuest, testRoom);
    // h.addGuest(testGuest);
    // h.addGuest(testGuest);
    // h.addGuest(testGuest);
    // h.addGuest(testGuest);
    // h.addRoom(testRoom);

    // h.addReservation(testReservation);

    // for(int i = 100; i < 1000; i+=100) {
    // for(int j = 1; j < 11; j++) {
    // if(j % 2 == 0) { 
    // h.addRoom(i+j,i,2,200.00,4,BedType.QUEEN, RoomType.LARGE,"",true);
    // } 
    // else {
    // h.addRoom(i+j,i,2,200.00,4,BedType.KING, RoomType.LARGE,"",true);
    // }
    // } 
    // }

    // System.out.println("This hotel has " + h.getNumberRooms() + " rooms");
    // System.out.println(h.getNumGuests());
    // h.displayMenu();
    // //h.makeReservation();
    // }
    return hotel;
}
}


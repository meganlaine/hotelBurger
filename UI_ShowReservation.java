import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.IllegalArgumentException;

/**
 * Write a description of class UI_ReservationConfirmation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UI_ShowReservation extends JFrame
{
    private JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp9, jp10, jp11, jp12;
    private JButton jb1, jb2, jb3, jb4;
    private JLabel jl0, jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9, jl10, jl11, jl12, jl13, jl14;

    /**
     * Constructor for objects of class UI_ReservationConfirmation
     */

    public static void main(Hotel h, Reservation reservation)
    {
        UI_ShowReservation window = new UI_ShowReservation(h, reservation);
        JFrame frame = new JFrame("Make Reservation");
    }

    public UI_ShowReservation(Hotel h, Reservation reservation){
        Guest guest = reservation.getGuest();
        Room room = reservation.getRoom();       
        jb1 = new JButton("Close");
        jb3 = new JButton("Check in/ Check out");
        jb4 = new JButton("Cancel reservation");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();
        jp7 = new JPanel();
        jp8 = new JPanel();
        jp9 = new JPanel();
        jp10 = new JPanel();
        jp11 = new JPanel();

        jl0 = new JLabel("Guest Information");
        jl1 = new JLabel("Name: " + guest.getFullName());
        jl2 = new JLabel("Phone Number: " + guest.getPhoneNum());
        jl6 = new JLabel("Is military: " + yesOrNo(guest.isMilitary()));
        jl14 = new JLabel("Party Size: " + guest.getPartySize());
        jl7 = new JLabel("Is government: " + yesOrNo(guest.isGovernment()));
        jl3 = new JLabel("Is Member: " + yesOrNo(guest.isMember()));
        jl8 = new JLabel("Reservation Status: " + getStatus(reservation));

        jl9 = new JLabel("Room: "+ room.getRoomNumber());
        jl10 = new JLabel("Room type: " + room.getRoomType());
        jl11 = new JLabel("Bed type: " + room.getBedType()); 
        jl12 = new JLabel("Capacity: " + room.getCapacity());
        jl13 = new JLabel("Room information");

        jp11.setLayout(new GridLayout(5,1));
        jp11.add(jl13);
        jp11.add(jl9);
        jp11.add(jl10);
        jp11.add(jl11);
        jp11.add(jl12);

        jp1.add(jb1);
        jp2.add(jb3);
        jp7.add(jb4);
        jp3.setLayout(new GridLayout(1,3));
        jp3.add(jp1);
        jp3.add(jp2);
        jp3.add(jp7);

        jp4.setLayout(new GridLayout(7,1));

        jp4.add(jl0);
        jp4.add(jl1);
        jp4.add(jl2);
        jp4.add(jl14);
        jp4.add(jl6);
        jp4.add(jl7);
        jp4.add(jl3);

        jp5.setLayout(new GridLayout(1,4));
        jp5.add(jp10);
        jp5.add(jp4);
        jp5.add(new JPanel());
        jp5.add(jp11);

        jp6.add(jl8);
        jp8.setLayout(new BorderLayout());
        jp8.add(jp6, BorderLayout.NORTH);
        jp8.add(jp5, BorderLayout.CENTER);
        jp8.add(jp3, BorderLayout.SOUTH);

        this.add(jp8);
        this.setSize(800,600);
        this.setLocation(300,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        jb1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    dispose();
                }});

        jb3.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    if (reservation.getStatus() == Status.WAITING){ 
                        int temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to check in ?", "Warning", JOptionPane.YES_NO_OPTION);
                        if(temp == 0){
                            try{
                                reservation.setStatus(Status.IN);
                                JOptionPane.showMessageDialog(null, "Check in successful", "Message", JOptionPane.PLAIN_MESSAGE);
                            }
                            catch(IllegalArgumentException ex){

                            }
                        }
                    }

                    else if(reservation.getStatus() == Status.IN){
                        int temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to check out ?", "Warning", JOptionPane.YES_NO_OPTION);
                        if(temp == 0){
                            try{
                                reservation.setStatus(Status.OUT);
                                room.setAvailable(true);
                                JOptionPane.showMessageDialog(null, "Check out successful", "Message", JOptionPane.PLAIN_MESSAGE);
                            }
                            catch(IllegalArgumentException ex){

                            }
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "This reservation cannot be checked in/out", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    jl8 = new JLabel("Reservation Status: " + getStatus(reservation));
                }});

        jb4.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    if (reservation.getStatus() == Status.WAITING){ 
                        int temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel reservation ?", "Warning", JOptionPane.YES_NO_OPTION);
                        if(temp == 0){
                            try{
                                reservation.setStatus(Status.CANCELED);
                                JOptionPane.showMessageDialog(null, "Cancellation successful", "Message", JOptionPane.PLAIN_MESSAGE);
                            }
                            catch(IllegalArgumentException ex){

                            }
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "This reservation cannot be cancelled", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    jl8 = new JLabel("Reservation Status: " + getStatus(reservation));
                }});

    }

    private static String yesOrNo(boolean b){
        if(b)
            return "Yes";
        else
            return "No";
    }

    private static String getStatus(Reservation reservation){
        Status status = reservation.getStatus();
        String str = "";
        switch(status){
            case WAITING:
            str = "Waiting";
            break;
            case IN:
            str = "Checked in";
            break;
            case OUT:
            str = "Checked out";
            break;
            case CANCELED:
            str = "Cancelled";
            break;
        }
        return str;
    }
}

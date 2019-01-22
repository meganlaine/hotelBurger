import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Write a description of class UI_ReservationConfirmation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UI_ReservationConfirmation extends JFrame
{
    private JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp9, jp10, jp11, jp12, jp13, jp14, jp15, jp16, jp17, jp18;
    private JButton jb1, jb2, jb3;
    private JLabel jl0, jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9, jl10, jl11, jl12, jl13, jl14;

    /**
     * Constructor for objects of class UI_ReservationConfirmation
     */

    public static void main(Hotel h, int guestNum, Guest guest, Room room, boolean isChk)
    {
        UI_ReservationConfirmation window = new UI_ReservationConfirmation(h, guestNum, guest, room, isChk);
        JFrame frame = new JFrame("Make Reservation");
    }

    public UI_ReservationConfirmation(Hotel h, int guestNum, Guest guest, Room room, boolean isChk){
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
        
        jb1 = new JButton("Proceed to Invoice");
        jb2 = new JButton("Back to Main Menu");
        jb3 = new JButton();

        jl0 = new JLabel("Guest Information");
        jl1 = new JLabel("Name: " + guest.getFullName());
        jl2 = new JLabel("Phone Number: " + guest.getPhoneNum());
        jl4 = new JLabel("Room reserved: " + room.getRoomNumber()); 
        jl14 = new JLabel("Party Size: " + guest.getPartySize());
        jl5 = new JLabel("Checked In: " + yesOrNo(isChk));
        jl6 = new JLabel("Is military: " + yesOrNo(guest.isMilitary()));
        jl7 = new JLabel("Is government: " + yesOrNo(guest.isGovernment()));
        jl3 = new JLabel("Is Member: " + yesOrNo(guest.isMember()));
        jl8 = new JLabel("Reservation Overview");
        
        
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
        jp2.add(jb2);
        jp3.setLayout(new GridLayout(1,2));
        jp3.add(jp1);
        jp3.add(jp2);

        jp4.setLayout(new GridLayout(10,1));

        
        jp4.add(jl0);
        jp4.add(jl1);
        jp4.add(jl2);
        jp4.add(jl4);
        jp4.add(jl14);
        jp4.add(jl5);
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
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        
        jb1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                 Status status = Status.WAITING;
                 if (isChk)
                    status = Status.IN;
                 Reservation reservation = new Reservation(room, guest, status);
                 UI_Invoice.main(h, reservation);
                 dispose();

            }});
            
        jb2.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    int temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to back to main menu?", "Warning", JOptionPane.YES_NO_OPTION);
                    if(temp == 0){
                        Menu.main(h);
                        dispose();
                    }

                }
            });
            
        this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent we)
                { 
                    int temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to back to main menu?", "Warning", JOptionPane.YES_NO_OPTION);
                    if(temp == 0){
                        Menu.main(h);
                        dispose();
                    }
                }
            });
    }

    private static String yesOrNo(boolean b){
        if(b)
            return "Yes";
        else
            return "No";
    }
}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.IllegalArgumentException;

/**
 * Show the information for the reservation object
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 1/28/2019
 */
public class UI_ShowReservation extends JFrame
{
    private JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp9, jp10, jp11, jp12, jp13, jp14, jp15;
    private JButton jb1, jb2, jb3, jb4, jb5, jb6;
    private JLabel jl0, jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9, jl10, jl11, jl12, jl13, jl14, jl15;

    /**
     * Constructor for objects of class UI_ReservationConfirmation
     */

    public static void main(Hotel h, Reservation reservation)
    {
        UI_ShowReservation window = new UI_ShowReservation(h, reservation);
        JFrame frame = new JFrame("Make Reservation");
    }

    public UI_ShowReservation(Hotel h, Reservation reservation){
        //get guest and room object from reservation
        Guest guest = reservation.getGuest();
        Room room = reservation.getRoom();       

        //instantiate components
        jb1 = new JButton("Close");
        jb2 = new JButton("Refresh");
        jb3 = new JButton("Check in/ Check out");
        jb4 = new JButton("Cancel reservation");
        jb5 = new JButton("Guest Info");
        jb6 = new JButton("Pay Bill");
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
        jp12 = new JPanel();
        jp13 = new JPanel();
        jp14 = new JPanel();
        jp15 = new JPanel();

        jl0 = new JLabel("Guest Information");
        jl1 = new JLabel("Name: " + guest.getFullName());
        jl2 = new JLabel("Phone Number: " + guest.getPhoneNum());
        jl6 = new JLabel("Is military: " + yesOrNo(guest.isMilitary()));
        jl14 = new JLabel("Party Size: " + reservation.getPartySize());
        jl7 = new JLabel("Is government: " + yesOrNo(guest.isGovernment()));
        jl3 = new JLabel("Is Member: " + yesOrNo(guest.isMember()));
        jl8 = new JLabel("Reservation Status: " + getStatus(reservation));

        jl9 = new JLabel("Room: "+ room.getRoomNumber());
        jl10 = new JLabel("Room type: " + room.getRoomType());
        jl11 = new JLabel("Bed type: " + room.getBedType()); 
        jl12 = new JLabel("Capacity: " + room.getCapacity());
        jl15 = new JLabel("Payment Due: $" +  String.format("%.2f",reservation.getPaymentDue()));
        jl13 = new JLabel("Room information");

        //set fonts for components
        jl0.setFont(jl0.getFont ().deriveFont (16.0f));
        jl1.setFont(jl1.getFont ().deriveFont (16.0f));
        jl2.setFont(jl2.getFont ().deriveFont (16.0f));
        jl14.setFont(jl14.getFont ().deriveFont (16.0f));
        //jl5.setFont(jl5.getFont ().deriveFont (16.0f));
        jl6.setFont(jl6.getFont ().deriveFont (16.0f));
        jl7.setFont(jl7.getFont ().deriveFont (16.0f));
        jl8.setFont(jl8.getFont ().deriveFont (16.0f));
        jl3.setFont(jl3.getFont ().deriveFont (16.0f));
        jl9.setFont(jl9.getFont ().deriveFont (16.0f));
        jl10.setFont(jl10.getFont ().deriveFont (16.0f));
        jl11.setFont(jl11.getFont ().deriveFont (16.0f));
        jl12.setFont(jl12.getFont ().deriveFont (16.0f));
        jl13.setFont(jl13.getFont ().deriveFont (16.0f));
        jl14.setFont(jl14.getFont ().deriveFont (16.0f));
        jl15.setFont(jl15.getFont ().deriveFont (16.0f));

        jb1.setFont(jb1.getFont ().deriveFont (12.0f));
        jb2.setFont(jb2.getFont ().deriveFont (12.0f));
        jb3.setFont(jb3.getFont ().deriveFont (12.0f));
        jb4.setFont(jb4.getFont ().deriveFont (12.0f));
        jb5.setFont(jb5.getFont ().deriveFont (12.0f));
        jb6.setFont(jb6.getFont ().deriveFont (12.0f));

        //setting up layout
        jp11.setLayout(new GridLayout(7,1));
        jp11.add(jl13);
        jp11.add(jl9);
        jp11.add(jl10);
        jp11.add(jl11);
        jp11.add(jl12);
        jp11.add(jl15);

        jp1.add(jb1);
        jp2.add(jb3);
        jp7.add(jb4);
        jp12.add(jb5);
        jp14.add(jb6);
        jp15.add(jb2);
        jp3.setLayout(new GridLayout(1,6));
        jp3.add(jp1);
        jp3.add(jp2);
        jp3.add(jp7);
        jp3.add(jp14);
        jp3.add(jp12);
        jp3.add(jp15);

        jp4.setLayout(new GridLayout(7,1));

        jp4.add(jl0);
        jp4.add(jl1);
        jp4.add(jl2);
        jp4.add(jl14);
        jp4.add(jl6);
        jp4.add(jl7);
        jp4.add(jl3);

        jp13.add(new JPanel());
        jp5.setLayout(new GridLayout(1,2));
        //jp5.add(jp10);
        jp5.add(jp4);

        jp5.add(jp11);

        jp6.add(jl8);
        jp8.setLayout(new BorderLayout());
        jp8.add(jp6, BorderLayout.NORTH);
        jp8.add(jp5, BorderLayout.CENTER);
        jp8.add(jp13, BorderLayout.WEST);
        jp8.add(jp3, BorderLayout.SOUTH);
        
        this.setTitle("Reservation " + guest.getFullName() + " " + room.getRoomNumber());
        this.add(jp8);
        this.setSize(800,600);
        this.setLocation(300,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        //close the window
        jb1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    dispose();
                }});
                
        //refresh the window by close the current one and reopen a new one
        jb2.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    dispose();
                    main(h, reservation);
                }});                

        /*
         * check in & check out the reservation 
         * cannot check out unless the reservation is paid
         **/
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
                            if(reservation.getPaymentDue() == 0.0){
                                try{
                                    reservation.setStatus(Status.OUT);
                                    room.setAvailable(true);
                                    JOptionPane.showMessageDialog(null, "Check out successful", "Message", JOptionPane.PLAIN_MESSAGE);
                                }
                                catch(IllegalArgumentException ex){

                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Must be paid before checking out", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "This reservation cannot be checked in/out", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }});
                
       
        //open the guest information window
        
        jb5.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    UI_Guest.main(h, reservation.getGuest());
                }});
                
        // cancel a reservation
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

                }});

        //pay the bill
        jb6.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    if (reservation.getPaymentDue() != 0.0){ 
                        int temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to pay the bill?", "Warning", JOptionPane.YES_NO_OPTION);
                        if(temp == 0){             
                            reservation.payBill();
                            JOptionPane.showMessageDialog(null, "Payment successful", "Message", JOptionPane.PLAIN_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "There is no payment due", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }});
    }

    /**
     * converts boolean to string
     * 
     * @param b the given boolean value
     * @return yes for true, no for false
     */
    private static String yesOrNo(boolean b){
        if(b)
            return "Yes";
        else
            return "No";
    }

    /**
     * convert the status of reservation to String
     * 
     * @param reservation the reservation object
     * @return the string of the status of the reservation
     */
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

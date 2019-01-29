import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * THe invoice page after the reservation
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 1/28/2019
 */
public class UI_Invoice extends JFrame
{

    private JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7;
    private JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8;
    private JButton jb1, jb2, jb3;

    /**
     * Constructor for objects of class UI_Invoice
     */
    public static void main(Hotel h, Reservation reservation)
    {
        UI_Invoice window = new UI_Invoice(h, reservation);
        JFrame frame = new JFrame("Invoice");
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public UI_Invoice(Hotel h, Reservation reservation)
    {
        //instantiate components 
        Guest guest= reservation.getGuest();
        Room room = reservation.getRoom();
        jl1 = new JLabel("Invoice #" + reservation.getReservationID());
        jl2 = new JLabel("Base Rate: $" + String.format("%.2f",room.getRate()));
        jl3 = new JLabel("Night Stays: " + reservation.getNights());
        jl4 = new JLabel("Guest: " + guest.getFullName());
        jl8 = new JLabel("Party Size: " + reservation.getPartySize());
        double price = room.getRate()*reservation.getNights()*reservation.getDiscount();
        jl5 = new JLabel(discountString(guest)+" Discount: " + (100 - reservation.getDiscount()*100) + "% off");
        jl6 = new JLabel("Total: " + String.format("%.2f",room.getRate()) + " * " +reservation.getNights() + " * " +reservation.getDiscount() + " = $" + String.format("%.2f",price));

        jb1 = new JButton("Back to main menu");
        jb2 = new JButton("Pay and reserve");
        jb3 = new JButton("Pay later and reserve");

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp7 = new JPanel();
        
        
        //setting the fonts of the components
        jl1.setFont(jl1.getFont ().deriveFont (16.0f));
        jl2.setFont(jl2.getFont ().deriveFont (16.0f));
        jl3.setFont(jl3.getFont ().deriveFont (16.0f));
        jl4.setFont(jl4.getFont ().deriveFont (16.0f));
        jl5.setFont(jl5.getFont ().deriveFont (16.0f));
        jl6.setFont(jl6.getFont ().deriveFont (16.0f));
        jl8.setFont(jl8.getFont ().deriveFont (16.0f));
        jb1.setFont(jb1.getFont ().deriveFont (14.0f));
        jb2.setFont(jb2.getFont ().deriveFont (14.0f));
        jb3.setFont(jb3.getFont ().deriveFont (14.0f));

        //setting layout for the window
        jp4.add(jb1);
        jp5.add(jb2);
        jp7.add(jb3);

        jp1.setLayout(new GridLayout(1,3));
        jp1.add(jp4);
        jp1.add(jp5);
        jp1.add(jp7);

        jp3.setLayout(new GridLayout(8,1));
        this.setLayout(new GridBagLayout());
        jp3.add(jl1);
        jp3.add(jl2);
        jp3.add(jl3);
        jp3.add(jl4);
        jp3.add(jl8);
        jp3.add(jl5);
        jp3.add(jl6);
        jp3.add(jp1);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 5.0;
        c.weighty = 10.0;
        this.setTitle("Invoice");
        this.add(jp3,c);
        this.setSize(800,600);
        this.setLocation(300,300);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        //back to main menu
        jb1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    int temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to back to main menu?", "Warning", JOptionPane.YES_NO_OPTION);
                    if(temp == 0){
                        Menu.main(h);
                        dispose();
                    }
                }
            });

        //reserve and pay
        jb2.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    h.addReservation(reservation);    
                    room.setAvailable(false);
                    reservation.setPaymentDue();
                    reservation.payBill();
                    JOptionPane.showMessageDialog(null, "Reservation Successful!", "Message", JOptionPane.PLAIN_MESSAGE);
                    Menu.main(h);
                    dispose();
                }
            }
        );

        //reserve and do not pay
        jb3.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    h.addReservation(reservation);    
                    room.setAvailable(false);
                    reservation.setPaymentDue();
                    JOptionPane.showMessageDialog(null, "Reservation Successful!", "Message", JOptionPane.PLAIN_MESSAGE);
                    Menu.main(h);
                    dispose();
                }
            }
        );
        
        //back to main menu on close
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

    /**
     * returns string for the discount the guest have
     * 
     * @param guest the guest object
     * @return the discount catagory the guest belongs
     */
    private static String discountString(Guest guest){
        String str = "No";
        if ( guest.isGovernment() ) {
            str = "Government";
        }
        else if (guest.isMilitary() ) {
            str = "Military";
        }
        else if (guest.isMember() ) {
            str = "Member";
        }
        return str;
    }

}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Shows the information of the guest
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 1/28/2019
 */
public class UI_Guest extends JFrame
{
    private JPanel jp3, jp4;
    private JButton jb1, jb2, jb3;
    private JLabel jl0, jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8;

    public static void main(Hotel h, Guest guest)
    {
        UI_Guest window = new UI_Guest(h, guest);
        JFrame frame = new JFrame("Guest");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public UI_Guest(Hotel h, Guest guest)
    {
        //instantiate components
        jl0 = new JLabel("Guest Information");
        jl1 = new JLabel("Name: " + guest.getFullName());
        jl2 = new JLabel("Phone Number: " + guest.getPhoneNum());
        jl4 = new JLabel("Room reserved: " + guest.getRoomReserved(h)); 
        jl8 = new JLabel("Party Size: " + h.findReservation(guest).getPartySize());
        jl5 = new JLabel("Checked In: " + yesOrNo(guest.isCheckedIn(h)));
        jl6 = new JLabel("Is military: " + yesOrNo(guest.isMilitary()));
        jl7 = new JLabel("Is government: " + yesOrNo(guest.isGovernment()));
        jl3 = new JLabel("IS member: " + yesOrNo(guest.isMember()));

        jb1 = new JButton("OK");
        jb2 = new JButton("History reservation");
        jp3 = new JPanel();
        jp3.add(jb1);
        jp3.add(jb2);

        //setting the fonts for the components
        jl0.setFont(jl0.getFont ().deriveFont (16.0f));
        jl1.setFont(jl1.getFont ().deriveFont (16.0f));
        jl2.setFont(jl2.getFont ().deriveFont (16.0f));
        jl3.setFont(jl3.getFont ().deriveFont (16.0f));        
        jl4.setFont(jl4.getFont ().deriveFont (16.0f));
        jl5.setFont(jl5.getFont ().deriveFont (16.0f));
        jl6.setFont(jl6.getFont ().deriveFont (16.0f));
        jl7.setFont(jl7.getFont ().deriveFont (16.0f));
        jl8.setFont(jl8.getFont ().deriveFont (16.0f));
        jb1.setFont(jb1.getFont ().deriveFont (14.0f));
        jb2.setFont(jb2.getFont ().deriveFont (14.0f));

        //setting the layout for the window
        jp4 = new JPanel();
        jp4.setLayout(new GridLayout(10,1));
        jp4.add(jl0);
        jp4.add(jl1);
        jp4.add(jl2);
        jp4.add(jl4);
        jp4.add(jl8);
        jp4.add(jl5);
        jp4.add(jl6);
        jp4.add(jl7);
        jp4.add(jl3);
        jp4.add(jp3);
        
        this.setTitle("Guest: " + guest.getFullName());
        this.setLayout(new GridLayout(1,3));
        this.add(new JPanel());
        this.add(jp4);
        this.add(new JPanel());
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

                }
            });
        
        //open the history reservation for the guest    
        jb2.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    UI_ShowGuestReservations.main(h, guest);

                }
            });
    }

    /**
     * return yes for true, no for false
     * 
     * @param b the boolean which to be converted
     * @return yes for true, no for false
     */
    private static String yesOrNo(boolean b){
        if(b)
            return "Yes";
        else
            return "No";
    }

}

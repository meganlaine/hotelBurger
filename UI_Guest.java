import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Write a description of class UI_Guest here.
 *
 * @author Xingyu Liu
 * @version (a version number or a date)
 */
public class UI_Guest extends JFrame
{
    private JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7;
    private JButton jb1, jb2, jb3;
    private JLabel jl0, jl1, jl2, jl3, jl4, jl5, jl6, jl7;

    public static void main(Hotel h, Guest guest)
    {
        UI_Guest window = new UI_Guest(h, guest);
        JFrame frame = new JFrame("Guest");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public UI_Guest(Hotel h, Guest guest)
    {
        jl0 = new JLabel("Guest Information");
        jl1 = new JLabel("Name: " + guest.getFullName());
        jl2 = new JLabel("Phone Number: " + guest.getPhoneNum());
        jl3 = new JLabel("Email: " + guest.getEmail());
        jl4 = new JLabel("Room reserved: " + guest.getRoomReserved()); 
        jl5 = new JLabel("Checked In: " + yesOrNo(guest.isCheckedIn()));
        jl6 = new JLabel("Is military: " + yesOrNo(guest.isMilitary()));
        jl7 = new JLabel("Is government: " + yesOrNo(guest.isGovernment()));
        
        jb1 = new JButton("OK");
        jp3 = new JPanel();
        jp3.add(jb1);
        
        jp4 = new JPanel();
        jp4.setLayout(new GridLayout(9,1));

        
        jp4.add(jl0);
        jp4.add(jl1);
        jp4.add(jl2);
        jp4.add(jl3);
        jp4.add(jl4);
        jp4.add(jl5);
        jp4.add(jl6);
        jp4.add(jl7);
        jp4.add(jp3);
        
        this.add(jp4);
        this.setSize(800,600);
        this.setLocation(300,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    
    private static String yesOrNo(boolean b){
        if(b)
            return "Yes";
        else
            return "No";
    }
}

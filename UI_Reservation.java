import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Write a description of class UI_Reservation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UI_Reservation extends JFrame
{
    private JPanel jp1, jp2, jp3, jp4, jp5;
    private JButton jb1, jb2, jb3;
    
    public static void main(Hotel h)
    {
        UI_Reservation window = new UI_Reservation(h);
        JFrame frame = new JFrame("Reservation");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    /**
     * Constructor for objects of class UI_Reservation
     */
    public UI_Reservation(Hotel h)
    {
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        
        jb1 = new JButton("Make a new reservation");
        jb2 = new JButton("See current reservations");
        jb3 = new JButton("Back to main menu");
        
        jp2.add(jb1);
        jp2.add(jb2);
        jp3.add(jb3);
        
        jp4.setLayout(new GridLayout(2,1));
        jp4.add(jp2);
        jp4.add(jp3);
        this.setLayout(new GridLayout(3,1));
        this.add(jp1);
        this.add(jp4);
        this.add(jp5);
        
        this.setTitle("Exit Reservation");
        this.setSize(600,500);
        this.setLocation(200,200);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
        jb1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    UI_PreReservation2.main(h);
                    dispose();
                }
            });
        
        jb2.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    UI_ReservationList.main(h);
                    dispose();
                }
            });
        
        jb3.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    Menu.main(h);
                    dispose();
                }
            });
    }
    

}

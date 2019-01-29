import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The menu for the reservations
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 1/28/2019
 */
public class UI_Reservation extends JFrame
{
    private JPanel jp1, jp2, jp3, jp4, jp5;
    private JButton jb1, jb2, jb3;

    public static void main(Hotel h)
    {
        UI_Reservation window = new UI_Reservation(h);
        JFrame frame = new JFrame("Reservation");
    }

    /**
     * Constructor for objects of class UI_Reservation
     */
    public UI_Reservation(Hotel h)
    {
        //instantiate components
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();

        jb1 = new JButton("Make a new reservation");
        jb2 = new JButton("See current reservations");
        jb3 = new JButton("Back to main menu");
        
        
        //setting up the fonts of the components
        jb1.setFont(jb1.getFont ().deriveFont (14.0f));
        jb2.setFont(jb2.getFont ().deriveFont (14.0f));
        jb3.setFont(jb3.getFont ().deriveFont (14.0f));
        
        //setting up the layout of the window
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

        this.setTitle("Reservation Menu");
        this.setSize(600,500);
        this.setLocation(200,200);
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        //make a new reservation
        jb1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    UI_PreReservation2.main(h);
                    dispose();
                }
            });

        //see current reservations
        jb2.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    UI_ReservationList.main(h);
                    dispose();
                }
            });

        //back to main menu
        jb3.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    Menu.main(h);
                    dispose();
                }
            });
            
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
}

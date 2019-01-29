import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu extends JFrame{
    private JPanel panel1,panel2,panel3,panel4, panel5, panel6;
    private JButton b1, b2, b3, b4, b5, b6;
    private JLabel l1;

    public static void main(Hotel h) {
        Menu window = new Menu( h );
        JFrame frame = new JFrame("Menu");
    }

    public Menu(Hotel h) {
        //instantiate components
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();

        b1 = new JButton("Reservations");
        b2 = new JButton("Rooms");
        b3 = new JButton("Guests");
        b4 = new JButton("Report");
        b5 = new JButton("About");
        b6 = new JButton("Exit");
        l1 = new JLabel("Main Menu");

        //set fonts for components
        b1.setFont(b1.getFont ().deriveFont (14.0f));
        b2.setFont(b2.getFont ().deriveFont (14.0f));
        b3.setFont(b3.getFont ().deriveFont (14.0f));
        b4.setFont(b4.getFont ().deriveFont (14.0f));
        b5.setFont(b5.getFont ().deriveFont (14.0f));
        b6.setFont(b6.getFont ().deriveFont (14.0f));
        l1.setFont(l1.getFont ().deriveFont (18.0f));

        //set layouts
        this.setLayout(new GridLayout(3,1));
        panel5.setLayout(new GridLayout(3,1));
        panel1.add(l1);
        panel2.add(b1);
        panel2.add(b2);
        panel3.add(b3);
        panel3.add(b4);
        panel4.add(b5);
        panel4.add(b6);
        panel5.add(panel2);
        panel5.add(panel3);
        panel5.add(panel4);
        this.add(panel1);
        this.add(panel5);
        this.add(panel6);

        this.setTitle("Main Menu");
        this.setSize(800,600);
        this.setLocation(200,200);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);

        //open reservation menu
        b1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    dispose();
                    UI_Reservation.main(h);
                }
            });
            
        //open room list
        b2.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    dispose();
                    UI_RoomList.main(h);
                }
            });
        
        //open guest list    
        b3.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    dispose();
                    UI_GuestList.main(h);
                }
            });
            
        //open report window
        b4.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    UI_Report.main(h);
                }
            });
            
        //show about dialog
        b5.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    JOptionPane.showMessageDialog(null, "Hotel Manager \n © 2019 Team Burger. All rights reserved.", "About", JOptionPane.PLAIN_MESSAGE);
                }
            });

        //exit the program
        b6.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    UI_Exit.main(h);
                }
            });

        //close action exit the program
        this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent we)
                { 
                    UI_Exit.main(h);
                }

            });

    }
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Write a description of class UI_MakeReservation here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UI_MakeReservation extends JFrame
{
    private JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp9, jp10, jp11, jp12, jp13, jp14;
    private JButton jb1, jb2, jb3, jb4;
    private JTextField tf1, tf2 ,tf3, tf4, tf5, tf6, tf7, tf8;
    private JCheckBox cb1, cb2, cb3;
    private JLabel jl1, jl2, jl3, jl4 ,jl5;
    private JCheckBox jc1, jc2, jc3, jc4;
    private boolean isMili, isGov, isMem, isChk;
    private String first, last, middle, dob, phone, email;

    public static void main(int roomNum, int guestNum, Hotel h)
    {
        UI_MakeReservation window = new UI_MakeReservation(roomNum, guestNum,h);
        JFrame frame = new JFrame("Make Reservation");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Constructor for objects of class UI_MakeReservation
     */
    public UI_MakeReservation(int roomNum, int guestNum, Hotel h)
    {
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

        jl1 = new JLabel("Guest Info");

        jb1 = new JButton("Continue");
        jb2 = new JButton("Back");
        jb3 = new JButton("Main Menu");
        jb4 = new JButton("Auto Fill");

        tf1 = new JTextField("First Name     ");
        tf2 = new JTextField("Middle Initial ");
        tf3 = new JTextField("Last Name      ");
        tf4 = new JTextField("Phone Number   ");
        tf5 = new JTextField("Membership Number   ");
        tf6 = new JTextField("DOB mm/dd/yyyy   ");
        tf7 = new JTextField("E-mail address               ");
        tf8 = new JTextField();

        jc1 = new JCheckBox("Member");
        jc2 = new JCheckBox("Military");
        jc3 = new JCheckBox("Government");
        jc4 = new JCheckBox("Check in at the same time");

        jp2.add(tf1);
        jp2.add(tf2);
        jp2.add(tf3);

        jp1.add(jl1);

        jp3.add(tf4);

        jp6.setLayout(new GridLayout(1,4));
        jp11.add(tf5);
        jp12.add(jc1);
        jp14.add(jb4);

        jp6.add(jp13);
        jp6.add(jp12);
        jp6.add(jp11);
        jp6.add(jp14);

        jp4.add(tf6);

        jp5.add(tf7);

        jp7.add(jc2);
        jp7.add(jc3);

        jp8.add(jc4);

        jp9.add(jb1);
        jp9.add(jb3);
        //jp9.add(jb2);

        jp10.setLayout(new GridLayout(9,1));
        jp10.add(jp1);
        jp10.add(jp2);
        jp10.add(jp3);
        jp10.add(jp4);
        jp10.add(jp5);
        jp10.add(jp6);
        jp10.add(jp7);
        jp10.add(jp8);
        jp10.add(jp9);

        this.add(jp10);

        this.setTitle("Make Reservation");
        this.setSize(800,600);
        this.setLocation(200,200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);

        tf5.setVisible(false);
        jb4.setVisible(false);

        jc1.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int state = e.getStateChange();
                    switch (state) {
                        case ItemEvent.SELECTED:
                        isMem = true;
                        tf5.setVisible(true);
                        jb4.setVisible(true);
                        break;
                        case ItemEvent.DESELECTED:
                        isMem = false;
                        tf5.setVisible(false);
                        jb4.setVisible(false);
                        break;
                    }
                }
            });

        jc2.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int state = e.getStateChange();
                    switch (state) {
                        case ItemEvent.SELECTED:
                        isMili = true;
                        break;
                        case ItemEvent.DESELECTED:
                        isMili = false;
                        break;
                    }
                }
            });

        jc3.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int state = e.getStateChange();
                    switch (state) {
                        case ItemEvent.SELECTED:
                        isGov = true;
                        break;
                        case ItemEvent.DESELECTED:
                        isGov = false;
                        break;
                    }
                }
            });

        jc4.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int state = e.getStateChange();
                    switch (state) {
                        case ItemEvent.SELECTED:
                        isChk = true;
                        break;
                        case ItemEvent.DESELECTED:
                        isChk = false;
                        break;
                    }
                }
            });
        jb1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    first = tf1.getText();
                    last = tf3.getText();
                    middle = tf2.getText(); 
                    dob = tf6.getText();
                    phone = tf4.getText();
                    email = tf7.getText();
                    Guest guest = new Guest(first, middle, last, dob, phone, email, isMili, isGov, isChk, String.valueOf(roomNum));
                }
            });

        jb3.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    int temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to back to main menu?", "Warning", JOptionPane.YES_NO_OPTION);
                    if(temp == 0){
                        Menu.main(h);
                        dispose();
                    }

                }
            });
    }


}

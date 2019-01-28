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
    private boolean isMili, isGov, isMem, isChk, tf1FirstSelect, tf3FirstSelect, tf4FirstSelect, firstFocus;
    private String first, last, middle, dob, phone, email;
    private Guest guest;

    public static void main(String roomNum, int guestNum, int nights, Hotel h)
    {
        UI_MakeReservation window = new UI_MakeReservation(roomNum, guestNum, nights, h);
        JFrame frame = new JFrame("Make Reservation");
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

    /**
     * Constructor for objects of class UI_MakeReservation
     */
    public UI_MakeReservation(String roomNum, int guestNum, int nights, Hotel h)
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
        jl2 = new JLabel("Guest Name");
        jl3 = new JLabel("Phone Number");
        jb1 = new JButton("Continue");
        jb2 = new JButton("Back");
        jb3 = new JButton("Main Menu");
        //jb4 = new JButton("Auto Fill");

        tf1 = new JTextField("First Name     ");
        tf2 = new JTextField("Middle Initial ");
        tf3 = new JTextField("Last Name      ");
        tf4 = new JTextField("1234567890     ");
        //tf5 = new JTextField("Membership Number   ");
        //tf6 = new JTextField("DOB mm/dd/yyyy   ");
        //tf7 = new JTextField("E-mail address               ");
        //tf8 = new JTextField();

        jc1 = new JCheckBox("Member");
        jc2 = new JCheckBox("Military");
        jc3 = new JCheckBox("Government");
        jc4 = new JCheckBox("Check in at the same time");

        jl1.setFont(jl1.getFont ().deriveFont (16.0f));
        jl2.setFont(jl2.getFont ().deriveFont (16.0f));
        jl3.setFont(jl3.getFont ().deriveFont (16.0f));
        tf1.setFont(tf1.getFont ().deriveFont (16.0f));
        tf3.setFont(tf3.getFont ().deriveFont (16.0f));
        tf4.setFont(tf4.getFont ().deriveFont (16.0f));
        jc1.setFont(jc1.getFont ().deriveFont (16.0f));
        jc2.setFont(jc2.getFont ().deriveFont (16.0f));
        jc3.setFont(jc3.getFont ().deriveFont (16.0f));
        jc4.setFont(jc4.getFont ().deriveFont (16.0f));
        jb1.setFont(jb1.getFont ().deriveFont (14.0f));
        jb2.setFont(jb2.getFont ().deriveFont (14.0f));
        jb3.setFont(jb3.getFont ().deriveFont (14.0f));

        jp2.add(jl2);
        jp2.add(tf1);
        //jp2.add(tf2);
        jp2.add(tf3);

        jp1.add(jl1);

        jp3.add(jl3);
        jp3.add(tf4);

        //jp6.setLayout(new GridLayout(1,4));
        //jp11.add(tf5);
        jp6.add(tf2);
        jp6.setVisible(false);
        //jp14.add(jb4);

        //jp6.add(jp13);
        //jp6.add(jp12);
        //jp6.add(jp11);
        //jp6.add(jp14);

        //jp4.add(tf6);

        //jp5.add(tf7);

        tf1FirstSelect = true;
        tf3FirstSelect = true;
        tf4FirstSelect = true;
        firstFocus = true;

        jp7.add(jc1);
        jp7.add(jc2);
        jp7.add(jc3);

        jp8.add(jc4);

        jp9.add(jb1);
        jp9.add(jb3);
        //jp9.add(jb2);

        jp10.setLayout(new GridLayout(6,1));
        jp10.add(jp1);
        jp10.add(jp2);
        jp10.add(jp3);
        //jp10.add(jp4);
        //jp10.add(jp5);
        //jp10.add(jp6);
        jp10.add(jp7);
        jp10.add(jp8);
        jp10.add(jp9);

        tf2.requestFocus();

        this.add(jp10);
        this.setTitle("Make Reservation");
        this.setSize(800,600);
        this.setLocation(200,200);
        this.setResizable(true);
        this.setVisible(true);

        // tf5.setVisible(false);
        // jb4.setVisible(false);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        this.addWindowFocusListener(new WindowAdapter() {
                public void windowGainedFocus(WindowEvent e) {
                    jb1.requestFocusInWindow();
                }
            });

        jc1.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int state = e.getStateChange();
                    switch (state) {
                        case ItemEvent.SELECTED:
                        isMem = true;
                        // tf5.setVisible(true);
                        // jb4.setVisible(true);
                        break;
                        case ItemEvent.DESELECTED:
                        isMem = false;
                        // tf5.setVisible(false);
                        // jb4.setVisible(false);
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
                    boolean validPhoneNum = false;
                    boolean validName = false;
                    first = tf1.getText().trim();
                    last = tf3.getText().trim();

                    // middle = tf2.getText(); 
                    // dob = tf6.getText();
                    phone = tf4.getText().trim();
                    if(isInt(phone)){
                        if(phone.length() == 10){    
                            validPhoneNum = true;
                        }
                    }
                    if(!(first.equals("")||last.equals(""))){
                        validName = true;
                    }
                    if(validName){
                        if(validPhoneNum){
                            guest = new Guest(first, last, phone, isMili, isGov, isMem);
                            dispose();
                            UI_ReservationConfirmation.main(h, guestNum, guest, h.getRoom(roomNum), isChk, nights);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Not a valid phone number", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Not a valid Name", "Error", JOptionPane.ERROR_MESSAGE);
                    }

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

        tf1.addFocusListener(new FocusListener(){
                @Override
                public void focusGained(FocusEvent e){
                    if(firstFocus){
                        firstFocus = false;
                    }
                    else if(tf1FirstSelect){
                        tf1.setText("");
                        tf1FirstSelect = false;
                    }
                }

                @Override
                public void focusLost(FocusEvent e){
                    //Do nothing
                }
            });

        tf3.addFocusListener(new FocusListener(){
                @Override
                public void focusGained(FocusEvent e){
                    if(tf3FirstSelect){
                        tf3.setText("");
                        tf3FirstSelect = false;
                    }
                }

                @Override
                public void focusLost(FocusEvent e){
                    //Do nothing
                }
            });

        tf4.addFocusListener(new FocusListener(){
                @Override
                public void focusGained(FocusEvent e){
                    if(tf4FirstSelect){
                        tf4.setText("");
                        tf4FirstSelect = false;
                    }
                }

                @Override
                public void focusLost(FocusEvent e){
                    //Do nothing
                }
            });

    }

    private static boolean isInt(String s) 
    {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }        
    }
}

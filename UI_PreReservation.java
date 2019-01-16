import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UI_PreReservation extends JFrame
{

    private JComboBox jc;

    private JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9, jl10, jl11, jl12, jl13;
    private JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp9, jp10, jp11, jp0, jp12;
    private Reservation res;
    private JButton jb1, jb2;
//    private JRadioButton jr1, jr2, jr3, jr4, jr5, jr6;
    private Room room;
    private int guestNum;
    private JTextField jt1;
    
    
    /**
     * Constructor for objects of class UI_PreReservation
     */
    public static void main(Hotel h) {
        UI_PreReservation window = new UI_PreReservation(h);
        JFrame frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public UI_PreReservation(Hotel h){
        jl1 = new JLabel("Number of Guests");
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
        jp0 = new JPanel();
        jp12 = new JPanel();
        jt1 = new JTextField("Enter the room number here");
        
        jl2 = new JLabel("$num available");
        jl3 = new JLabel("$num available");
        jl4 = new JLabel("$num available");
        jl5 = new JLabel("$num available");
        jl6 = new JLabel("$num available");
        jl7 = new JLabel("$num available");
        
        jb1 = new JButton("Continue");
        jl8 = new JLabel("Monday");
        jl9 = new JLabel("Tuesday");
        jl10 = new JLabel("Wednesday");
        jl11 = new JLabel("Thursday");
        jl12 = new JLabel("Friday");
        jl13 = new JLabel("Saturday");
        jb2 = new JButton("Back");
        
        jp6.add(jl8); jp6.add(jl2);
        jp7.add(jl9); jp7.add(jl3);
        jp8.add(jl10); jp8.add(jl4);
        jp9.add(jl11); jp9.add(jl5);
        jp10.add(jl12); jp10.add(jl6);
        jp11.add(jl13); jp11.add(jl7);
        jp12.add(jt1);
        
        String selections[] = {"","1","2","3","4","5","6","7","8"};
        jc = new JComboBox(selections);

        this.setLayout(new GridLayout(1,2));
        
        jp1.add(jl1);
        jp1.add(jc);
        jp4.add(jb2);
        
        jp3.setLayout(new GridLayout(3,1));
        
        jp3.add(jp0);
        jp3.add(jp1);
        jp3.add(jp4);
        
        jp5.add(jb1);
        jp2.setLayout(new GridLayout(8,1));
        
        jp2.add(jp6);
        jp2.add(jp7);
        jp2.add(jp8);
        jp2.add(jp9);
        jp2.add(jp10);
        jp2.add(jp11);
        jp2.add(jp12);
        jp2.add(jp5);
        
        
        this.add(jp3);
        this.add(jp2);
        this.setTitle("Room availability");
        this.setSize(800,600);
        this.setLocation(200,200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(true);
        this.setVisible(true);

        jp5.setVisible(false);
        jp6.setVisible(false);      
        jp7.setVisible(false);
        jp8.setVisible(false);
        jp9.setVisible(false);
        jp10.setVisible(false);
        jp11.setVisible(false);
        jt1.setVisible(false);
        jc.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    jp5.setVisible(false);
                    jp6.setVisible(false);
                    jp7.setVisible(false);
                    jp8.setVisible(false);
                    jp9.setVisible(false);
                    jp10.setVisible(false);
                    jp11.setVisible(false);
                    
                    String item = e.getItem().toString();
                    switch (item) {
                        case "1":
                        jp6.setVisible(true);
                        guestNum = 1;
                        break;
                        case "2":
                        jp7.setVisible(true);
                        guestNum = 2;
                        break;
                        case "3":
                        jp8.setVisible(true);
                        guestNum = 3;
                        break;
                        case "4":
                        jp9.setVisible(true);
                        guestNum = 4;
                        break;
                        case "5":
                        jp10.setVisible(true);
                        guestNum = 5;
                        break;
                        case "6":
                        jp11.setVisible(true);
                        guestNum = 6;
                        break;
                    }
                    jt1.setVisible(true);
                    jp5.setVisible(true);
                }
            });

        jb1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    
                    if (!isInt(jt1.getText())){
                        PopUp.main("Please Select a room!");
                    }
                    else{                    
                        UI_MakeReservation.main(Integer.parseInt(jt1.getText()),guestNum,h);
                        dispose();
                    }
                }
            });
        jb2.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    
                    dispose();
                    Menu.main(h);
                }
            });
    }
    
    
    
    private static boolean isInt(String str) {
    try { 
        Integer.parseInt(str); 
    } catch(NumberFormatException e) { 
        return false; 
    } catch(NullPointerException e) {
        return false;
    }
    return true;
}
}

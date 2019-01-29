import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * the window collects the informations for reservation, including a room filter.
 * 
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 1/28/2019
 */
public class UI_PreReservation2 extends JFrame
{

    private JComboBox jc, jc2;
    private JLabel jl1, jl2;
    private JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp9, jp10, jp11, jp0, jp12, jp13, jp14, jp15;
    private JButton jb1, jb2;
    private JRadioButton jr1, jr2, jr3, jr4, jr5, jr6, jr7, jr8;
    private JList list;
    private JScrollPane js1;
    private int guestNum, roomNum, night;
    private ArrayList<String> rms;
    private Object[] strings;
    private ButtonGroup bg1, bg2;
    private String rt, bt;

    /**
     * Constructor for objects of class UI_PreReservation
     */
    public static void main(Hotel h) {
        UI_PreReservation2 window = new UI_PreReservation2(h);
        JFrame frame = new JFrame("Menu");
    }

    public UI_PreReservation2(Hotel h){
        //instantiate components
        jl1 = new JLabel("Number of Guests");
        jl2 = new JLabel("Nights");
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
        jp13 = new JPanel();
        jp14 = new JPanel();
        jp15 = new JPanel();

        js1 = new JScrollPane();
        js1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        js1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        list = new JList();
        js1.setViewportView(list);
        jp12.setLayout(new BorderLayout());
        jp12.add(js1);

        //setting up two button groups
        bg1 = new ButtonGroup();
        bg2 = new ButtonGroup();

        jb1 = new JButton("Continue");
        jr1 = new JRadioButton("Regular");
        jr2 = new JRadioButton("Large");
        jr3 = new JRadioButton("Suite");
        jr4 = new JRadioButton("Double");
        jr5 = new JRadioButton("Queen");
        jr6 = new JRadioButton("King");
        jb2 = new JButton("Back");

        bg1.add(jr1);
        bg1.add(jr2);
        bg1.add(jr3);

        bg2.add(jr4);
        bg2.add(jr5);
        bg2.add(jr6);

        jp6.add(jr1); //jp6.add(jl2);
        jp7.add(jr2); //jp7.add(jl3);
        jp8.add(jr3); //jp8.add(jl4);
        jp9.add(jr4); //jp9.add(jl5);
        jp10.add(jr5); //jp10.add(jl6);
        jp11.add(jr6); //jp11.add(jl7);
        
        //setting up the combo box
        String selections[] = {"","1","2","3","4","5","6"};
        jc = new JComboBox(selections);

        String nights[] = {"","1","2","3","4","5","6","7","8"};
        jc2 = new JComboBox(nights);

        //setting the fonts for components 
        jl1.setFont(jl1.getFont ().deriveFont (16.0f));
        jl2.setFont(jl2.getFont ().deriveFont (16.0f));
        list.setFont(list.getFont ().deriveFont (16.0f));
        jc.setFont(jc.getFont ().deriveFont (16.0f));
        jc2.setFont(jc2.getFont ().deriveFont (16.0f));
        jr1.setFont(jr1.getFont ().deriveFont (16.0f));
        jr2.setFont(jr2.getFont ().deriveFont (16.0f));
        jr3.setFont(jr3.getFont ().deriveFont (16.0f));
        jr4.setFont(jr4.getFont ().deriveFont (16.0f));
        jr5.setFont(jr5.getFont ().deriveFont (16.0f));
        jr6.setFont(jr6.getFont ().deriveFont (16.0f));
        jb1.setFont(jb1.getFont ().deriveFont (14.0f));
        jb2.setFont(jb2.getFont ().deriveFont (14.0f));

        //setting up the layout of the window
        this.setLayout(new GridLayout(1,4));
        jp1.add(jl1);
        jp1.add(jc);
        jp14.add(jl2);
        jp14.add(jc2);

        jp15.setLayout(new GridLayout(2,1));
        jp15.add(jp14);
        jp15.add(jp1);
        jp4.add(jb2);

        jp3.setLayout(new GridLayout(3,1));

        jp3.add(jp0);
        jp3.add(jp15);
        jp3.add(jp4);

        jp13.setLayout(new GridLayout(4,1));

        jp13.add(jp9);
        jp13.add(jp10);
        jp13.add(jp11);

        jp5.add(jb1);
        jp2.setLayout(new GridLayout(3,1));

        jp2.add(jp6);
        jp2.add(jp7);
        jp2.add(jp8);
        jp13.add(jp5);
        

        this.add(jp3);
        this.add(jp2);
        this.add(jp13);
        this.add(jp12);
        this.setTitle("Pre-Reservation");
        this.setSize(800,600);
        this.setLocation(200,200);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        //hide the room filter before the selection of the guest num and nights
        jp5.setVisible(false);
        jp6.setVisible(false);      
        jp7.setVisible(false);
        jp8.setVisible(false);
        jp1.setVisible(false);
        jp13.setVisible(false);

        js1.setVisible(false);

        //get the nights stay, and show the choices for the guest num
        jc2.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    String item = e.getItem().toString();
                    switch (item) {
                        case "1":
                        night = 1;
                        break;
                        case "2":
                        night = 2;
                        break;
                        case "3":
                        night = 3;
                        break;
                        case "4":
                        night = 4;
                        break;
                        case "5":
                        night = 5;
                        break;
                        case "6":
                        night = 6;
                        break;
                        case "7":
                        night = 7;
                        break;
                        case "8":
                        night = 8;
                        break;
                    }
                    jp1.setVisible(true);
                }
            });

        //get the guest num, and show the corresponding room type that matches the capacity
        jc.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    jp5.setVisible(false);
                    jp6.setVisible(false);
                    jp7.setVisible(false);
                    jp8.setVisible(false);
                    jp13.setVisible(false);
                    js1.setVisible(false);
                    list.clearSelection();
                    String item = e.getItem().toString();
                    switch (item) {
                        case "1":
                        jp6.setVisible(true);
                        jp7.setVisible(true);
                        jp8.setVisible(true);
                        guestNum = 1;
                        break;
                        case "2":
                        jp6.setVisible(true);
                        jp7.setVisible(true);
                        jp8.setVisible(true);
                        guestNum = 2;
                        break;
                        case "3":
                        jp6.setVisible(true);
                        jp7.setVisible(true);
                        jp8.setVisible(true);
                        guestNum = 3;
                        break;
                        case "4":
                        jp6.setVisible(true);
                        jp7.setVisible(true);
                        jp8.setVisible(true);
                        guestNum = 4;
                        break;
                        case "5":
                        jp8.setVisible(true);
                        guestNum = 5;
                        break;
                        case "6":
                        jp8.setVisible(true);
                        guestNum = 6;
                        break;
                    }
                    bg1.clearSelection();
                    jp5.setVisible(true);
                }
            });
            
        //shows the available bedtype for the room type
        jr1.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int state = e.getStateChange();
                    switch (state) {
                        case ItemEvent.SELECTED:
                        rt = "Regular";
                        bg2.clearSelection();
                        jp13.setVisible(true);
                        jr6.setVisible(false);
                        break;
                        case ItemEvent.DESELECTED:
                        jp13.setVisible(false);
                        jr6.setVisible(true);
                        break;
                    }
                }
            });    

        //shows the available bedtype for the room type
        jr2.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int state = e.getStateChange();
                    switch (state) {
                        case ItemEvent.SELECTED:
                        rt = "Large";
                        bg2.clearSelection();
                        jp13.setVisible(true);
                        jr4.setVisible(false);
                        break;
                        case ItemEvent.DESELECTED:
                        jp13.setVisible(false);
                        jr4.setVisible(true);
                        break;
                    }
                }
            });    

        //shows the available bedtype for the room type    
        jr3.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int state = e.getStateChange();
                    switch (state) {
                        case ItemEvent.SELECTED:
                        rt = "Suite";
                        bg2.clearSelection();
                        jp13.setVisible(true);
                        jr4.setVisible(false);
                        jr5.setVisible(false);
                        break;
                        case ItemEvent.DESELECTED:
                        jp13.setVisible(false);
                        jr4.setVisible(true);
                        jr5.setVisible(true);
                        break;
                    }
                }
            });    

        //shows the available rooms for the given room type, bed type, and capacity    
        jr4.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int state = e.getStateChange();
                    switch (state) {
                        case ItemEvent.SELECTED:
                        bt = "DOUBLE";
                        strings = searchRoom(h.getAllRooms(), rt,bt, true, guestNum);
                        list.setListData(strings);
                        js1.setVisible(true);
                        break;
                        case ItemEvent.DESELECTED:
                        js1.setVisible(false);
                        break;
                    }
                }
            });    

        //shows the available rooms for the given room type, bed type, and capacity  
        jr5.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int state = e.getStateChange();
                    switch (state) {
                        case ItemEvent.SELECTED:
                        bt = "QUEEN";
                        strings = searchRoom(h.getAllRooms(), rt,bt, true, guestNum);
                        list.setListData(strings);
                        js1.setVisible(true);
                        break;
                        case ItemEvent.DESELECTED:
                        js1.setVisible(false);
                        break;
                    }
                }
            });    

        //shows the available rooms for the given room type, bed type, and capacity      
        jr6.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int state = e.getStateChange();
                    switch (state) {
                        case ItemEvent.SELECTED:
                        bt = "KING";
                        strings = searchRoom(h.getAllRooms(), rt,bt, true, guestNum);
                        list.setListData(strings);
                        js1.setVisible(true);
                        break;
                        case ItemEvent.DESELECTED:
                        js1.setVisible(false);
                        break;
                    }
                }
            });
            
        //continue to the make reservation page and show a message if no room is selected
        jb1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    if (list.getSelectedIndex() == -1){
                        JOptionPane.showMessageDialog(null, "Please select a room!", "Message", JOptionPane.PLAIN_MESSAGE);
                    }
                    else{

                        UI_MakeReservation.main((String)strings[list.getSelectedIndex()],guestNum, night, h);
                        dispose();
                    }
                }
            });

        //back to main menu    
        jb2.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    int temp = JOptionPane.showConfirmDialog(null, "Are you sure you want to back to main menu?", "Warning", JOptionPane.YES_NO_OPTION);
                    if(temp == 0){
                        Menu.main(h);
                        dispose();
                    }

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

    /**
     * search for rooms based on the condition
     * 
     * @param rms the arraylist of rooms to search
     * @param rt the room type of the room
     * @param bt the bed type of the room
     * @param isAval the avalability of the room
     * @param guestNum the number of guests
     * @return the room which satisfy all conditions
     */
    private static Object[] searchRoom(ArrayList<Room> rms, String rt, String bt, boolean isAval, int guestNum){
        ArrayList<String> strings = new ArrayList<>();
        for(Room rm: rms){
            String bedtype = rm.getBedType().replaceAll("x.*$","").replaceAll("-.*$","");
            if(rm.getRoomType().equals(rt) && bedtype.equals(bt) && rm.isAvailable() == isAval && rm.getCapacity() >= guestNum)
                strings.add(rm.getRoomNumber());
        }
        return strings.toArray();
    }

}

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

public class UI_PreReservation2 extends JFrame
{

    private JComboBox jc;

    private JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7;
    private JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp9, jp10, jp11, jp0, jp12, jp13;
    private Reservation res;
    private JButton jb1, jb2;
    private JRadioButton jr1, jr2, jr3, jr4, jr5, jr6;
    private JList list;
    private JScrollPane js1;
    private int guestNum, roomNum;
    private ArrayList<Integer> rms;
    private Object[] ints;
    private ButtonGroup bg1, bg2;
    private RoomType rt;
    private BedType bt;

    /**
     * Constructor for objects of class UI_PreReservation
     */
    public static void main(Hotel h) {
        UI_PreReservation2 window = new UI_PreReservation2(h);
        JFrame frame = new JFrame("Menu");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public UI_PreReservation2(Hotel h){
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
        jp13 = new JPanel();

        js1 = new JScrollPane();
        js1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        js1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        list = new JList();
        js1.setViewportView(list);
        jp12.setLayout(new BorderLayout());
        jp12.add(js1);

        bg1 = new ButtonGroup();
        bg2 = new ButtonGroup();
        // jl2 = new JLabel("$num available");
        // jl3 = new JLabel("$num available");
        // jl4 = new JLabel("$num available");
        // jl5 = new JLabel("$num available");
        // jl6 = new JLabel("$num available");
        // jl7 = new JLabel("$num available");

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

        String selections[] = {"","1","2","3","4","5","6","7","8"};
        jc = new JComboBox(selections);

        this.setLayout(new GridLayout(1,4));
        jp1.add(jl1);
        jp1.add(jc);
        jp4.add(jb2);

        jp3.setLayout(new GridLayout(3,1));

        jp3.add(jp0);
        jp3.add(jp1);
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
        // jp2.add(jp9);
        // jp2.add(jp10);
        // jp2.add(jp11);
        jp13.add(jp5);

        this.add(jp3);
        this.add(jp2);
        this.add(jp13);
        this.add(jp12);
        this.setTitle("Main Menu");
        this.setSize(800,600);
        this.setLocation(200,200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);

        jp5.setVisible(false);
        jp6.setVisible(false);      
        jp7.setVisible(false);
        jp8.setVisible(false);
        //jp9.setVisible(false);
        //jp10.setVisible(false);
        jp13.setVisible(false);
        //jp11.setVisible(false);
        js1.setVisible(false);
        jc.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    jp5.setVisible(false);
                    jp6.setVisible(false);
                    jp7.setVisible(false);
                    jp8.setVisible(false);
                    // jp9.setVisible(false);
                    // jp10.setVisible(false);
                    // jp11.setVisible(false);
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
                        jp7.setVisible(true);
                        jp8.setVisible(true);
                        guestNum = 3;
                        break;
                        case "4":
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
                    jp5.setVisible(true);
                }
            });

        jr1.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int state = e.getStateChange();
                    switch (state) {
                        case ItemEvent.SELECTED:
                        rt = RoomType.REGULAR;
                        bg2.clearSelection();
                        jp13.setVisible(true);
                        break;
                        case ItemEvent.DESELECTED:
                        jp13.setVisible(false);
                        break;
                    }
                }
            });    

        jr2.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int state = e.getStateChange();
                    switch (state) {
                        case ItemEvent.SELECTED:
                        rt = RoomType.LARGE;
                        bg2.clearSelection();
                        jp13.setVisible(true);
                        break;
                        case ItemEvent.DESELECTED:
                        jp13.setVisible(false);
                        break;
                    }
                }
            });    

        jr3.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int state = e.getStateChange();
                    switch (state) {
                        case ItemEvent.SELECTED:
                        rt = RoomType.SUITE;
                        bg2.clearSelection();
                        jp13.setVisible(true);
                        break;
                        case ItemEvent.DESELECTED:
                        jp13.setVisible(false);
                        break;
                    }
                }
            });    

        jr4.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int state = e.getStateChange();
                    switch (state) {
                        case ItemEvent.SELECTED:
                        bt = BedType.DOUBLE;
                        ints = searchRoom(h.getRoomList(), rt,bt);
                        list.setListData(ints);
                        js1.setVisible(true);
                        break;
                        case ItemEvent.DESELECTED:
                        js1.setVisible(false);
                        break;
                    }
                }
            });    

        jr5.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int state = e.getStateChange();
                    switch (state) {
                        case ItemEvent.SELECTED:
                        bt = BedType.QUEEN;
                        ints = searchRoom(h.getRoomList(), rt,bt);
                        list.setListData(ints);
                        js1.setVisible(true);
                        break;
                        case ItemEvent.DESELECTED:
                        js1.setVisible(false);
                        break;
                    }
                }
            });    

        jr6.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int state = e.getStateChange();
                    switch (state) {
                        case ItemEvent.SELECTED:
                        bt = BedType.KING;
                        ints = searchRoom(h.getRoomList(), rt,bt);
                        list.setListData(ints);
                        js1.setVisible(true);
                        break;
                        case ItemEvent.DESELECTED:
                        js1.setVisible(false);
                        break;
                    }
                }
            });    

        jb1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    if (list.getSelectedIndex() == -1){
                        PopUp.main("Please Select a room!");
                    }
                    else{

                        UI_MakeReservation.main((int)ints[list.getSelectedIndex()],guestNum, h);
                        dispose();
                    }
                }
            });
    }

    private static Object[] searchRoom(ArrayList<Room> rms, RoomType rt, BedType bt){
        ArrayList<Integer> ints = new ArrayList<Integer>();
        for(Room rm: rms){
            if(rm.getRoomType() == rt && rm.getBedType() == bt)
                ints.add(Integer.parseInt(rm.getRoomNumber()));
        }
        return ints.toArray();
    }
}


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * Write a description of class UI_Exit here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UI_RoomList extends JFrame
{
    private JPanel jp1, jp2, jp3, jp4, jp5, jp6;
    private JButton jb1, jb2, jb3;
    private JLabel jl1, jl2;
    private JScrollPane js1, js2;
    private JList list1, list2;
    private ArrayList<String> empty, occupied;
    /**
     * Constructor for objects of class PopUp
     */
    public static void main(Hotel h)
    {
        UI_RoomList window = new UI_RoomList(h);
        JFrame frame = new JFrame("Rooms");
    }

    public UI_RoomList(Hotel h)
    {
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();

        empty = h.getEmptyRoomNum();
        occupied = h.getOccupiedRoomNum();

        list1 = new JList(empty.toArray());
        list2 = new JList(occupied.toArray());

        jl1 = new JLabel("Empty");
        jl2 = new JLabel("Occupied");

        js1 = new JScrollPane();
        js1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        js1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        js2 = new JScrollPane();
        js2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        js2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        jb1 = new JButton("Back to main menu");
        jb2 = new JButton("Detail");

        this.setLayout(new BorderLayout());

        js2.setViewportView(list1);
        js1.setViewportView(list2);

        jp1.setLayout(new GridLayout(1,2));
        jp1.add(js1);
        jp1.add(js2);

        jp6.setLayout(new GridLayout(1,2));
        jp6.add(jl1);
        jp6.add(jl2);

        jp3.add(jb1);
        jp4.add(jb2);

        jp2.setLayout(new GridLayout(1,2));
        jp2.add(jp3);
        jp2.add(jp4);

        this.add(jp1,BorderLayout.CENTER);
        this.add(jp6,BorderLayout.NORTH);
        this.add(jp2,BorderLayout.SOUTH);
        //this.add(jp2);

        this.setSize(800,600);
        this.setLocation(300,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
        jb1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    //Some exit program here
                    Menu.main(h);
                    dispose();
                }
            });
        jb2.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    if(list1.getSelectedIndex() != -1) {
                        UI_Room.main(h, empty.get(list1.getSelectedIndex()));
                    }
                    else{
                        UI_Room.main(h, occupied.get(list2.getSelectedIndex()));
                    }
                }
            });

        list1.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    list2.clearSelection();
                }
            });

        list2.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    list1.clearSelection();
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
    }
}
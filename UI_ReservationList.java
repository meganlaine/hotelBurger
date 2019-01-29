import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * the window which shows the list of all reservations
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 1/28/2019
 */
public class UI_ReservationList extends JFrame
{
    private JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7, jp8, jp9;
    private JButton jb1, jb2, jb3;
    private JLabel jl1, jl2, jl3, jl4;
    private JScrollPane js1, js2, js3, js4;
    private JList list1, list2, list3, list4;
    private ArrayList<Reservation> in, out, waiting, cancel;
    /**
     * Constructor for objects of class PopUp
     */
    public static void main(Hotel h)
    {
        UI_ReservationList window = new UI_ReservationList(h);
        JFrame frame = new JFrame("Reservation List");
    }

    public UI_ReservationList(Hotel h)
    {
        //instantiate components
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jp5 = new JPanel();
        jp6 = new JPanel();
        jp7 = new JPanel();
        jp8 = new JPanel();
        jp9 = new JPanel();

        in = h.getReservations(Status.IN);
        out = h.getReservations(Status.OUT);
        waiting = h.getReservations(Status.WAITING);
        cancel = h.getReservations(Status.CANCELED);

        list1 = new JList(toLabel(waiting).toArray());
        list2 = new JList(toLabel(in).toArray());
        list3 = new JList(toLabel(out).toArray());
        list4 = new JList(toLabel(cancel).toArray());

        jl1 = new JLabel("Waiting");
        jl2 = new JLabel("Checked in");
        jl3 = new JLabel("Checked out");
        jl4 = new JLabel("Cancelled");

        js1 = new JScrollPane();
        js1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        js1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        js2 = new JScrollPane();
        js2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        js2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        js3 = new JScrollPane();
        js3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        js3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        js4 = new JScrollPane();
        js4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        js4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        jb1 = new JButton("Back to reservation page");
        jb2 = new JButton("Detail");
        jb3 = new JButton("Refresh");
        
        
        //setting fonts for components
        jl1.setFont(jl1.getFont ().deriveFont (16.0f));
        jl2.setFont(jl2.getFont ().deriveFont (16.0f));
        jl3.setFont(jl3.getFont ().deriveFont (16.0f));
        jl4.setFont(jl4.getFont ().deriveFont (16.0f));
        list1.setFont(list1.getFont ().deriveFont (16.0f));
        list2.setFont(list2.getFont ().deriveFont (16.0f));
        list3.setFont(list3.getFont ().deriveFont (16.0f));
        list4.setFont(list4.getFont ().deriveFont (16.0f));
        jb1.setFont(jb1.getFont ().deriveFont (14.0f));
        jb2.setFont(jb2.getFont ().deriveFont (14.0f));
        jb3.setFont(jb3.getFont ().deriveFont (14.0f));

        //setting layout
        this.setLayout(new BorderLayout());
        js1.setViewportView(list1);
        js2.setViewportView(list2);
        js3.setViewportView(list3);
        js4.setViewportView(list4);

        jp1.setLayout(new GridLayout(1,4));
        jp1.add(js1);
        jp1.add(js2);
        jp1.add(js3);
        jp1.add(js4);

        jp6.setLayout(new GridLayout(1,2));
        jp6.add(jl1);
        jp6.add(jl2);
        jp6.add(jl3);
        jp6.add(jl4);

        jp3.add(jb1);
        jp4.add(jb2);
        jp9.add(jb3);

        jp2.setLayout(new GridLayout(1,3));
        jp2.add(jp3);
        jp2.add(jp4);
        jp2.add(jp9);

        this.add(jp1,BorderLayout.CENTER);
        this.add(jp6,BorderLayout.NORTH);
        this.add(jp2,BorderLayout.SOUTH);
        this.setTitle("Reservation List");
        this.setSize(1000,750);
        this.setLocation(200,200);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        
        //back to reservation menu
        jb1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    //Some exit program here
                    UI_Reservation.main(h);
                    dispose();
                }
            });
            
        //get the selected element and open the reservation detail wiondow    
        jb2.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    if(list1.getSelectedIndex() != -1) {
                        UI_ShowReservation.main(h, waiting.get(list1.getSelectedIndex()));
                    }
                    else if(list2.getSelectedIndex() != -1){
                        UI_ShowReservation.main(h, in.get(list2.getSelectedIndex()));
                    }
                    else if(list3.getSelectedIndex() != -1){
                        UI_ShowReservation.main(h, out.get(list3.getSelectedIndex()));
                    }
                    else if(list4.getSelectedIndex() != -1){
                        UI_ShowReservation.main(h, cancel.get(list4.getSelectedIndex()));
                    }
                }
            });

        //back to main menu
        jb3.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    dispose();
                    main(h);
                }
            });
            
        //clear selections of other lists when the current list is selected    
        list1.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    list2.clearSelection();
                    list3.clearSelection();
                    list4.clearSelection();
                }
            });

        list2.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    list1.clearSelection();
                    list3.clearSelection();
                    list4.clearSelection();
                }
            });

        list3.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    list1.clearSelection();
                    list2.clearSelection();
                    list4.clearSelection();
                }
            });

        list4.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent e) {
                    list1.clearSelection();
                    list3.clearSelection();
                    list2.clearSelection();
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
     * convert the list of reservations 
     * 
     * @param reservations the list of reservations is being converted
     * @return list of string of the reservations
     */
    private static ArrayList<String> toLabel(ArrayList<Reservation> reservations){
        ArrayList<String> list = new ArrayList<>();

        for(Reservation reservation: reservations){
            list.add(reservation.getRoom().getRoomNumber()+ "  " + reservation.getGuest().getFullName());
        }
        Collections.sort(list);
        return list;
    }

}
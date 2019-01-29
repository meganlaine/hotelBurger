import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The window which shows the information for the room
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 1/28/2019
 */
public class UI_Room extends JFrame
{
    // instance variables - replace the example below with your own
    private JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7;
    private JPanel jp1, jp2, jp3, jp4, jp5;
    private JButton jb1, jb2, jb3, jb4;

    public static void main(Hotel h, String roomNum){
        UI_Room window = new UI_Room(h, roomNum);
        JFrame frame = new JFrame("Room " + roomNum);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    /**
     * Constructor for objects of class UI_Room
     */
    public UI_Room(Hotel h, String roomNum)
    {
        //instantiate the components
        Room room = h.getRoom(roomNum);
        jl1 = new JLabel("Room: "+roomNum);
        jl2 = new JLabel("Room type: " + room.getRoomType());
        jl3 = new JLabel("Bed type: " + room.getBedType()); 
        jl4 = new JLabel("Capacity: " + room.getCapacity());
        jl5 = new JLabel("Availability: " + yesOrNo(room.isAvailable()));
        jp1 = new JPanel();
        jp2 = new JPanel();

        jb1 = new JButton("OK");
        jb2 = new JButton("Guest Info");
        jp1.add(jb1);
        jp1.add(jb2);

        //setting up the fonts of the components
        jl1.setFont(jl1.getFont ().deriveFont (16.0f));
        jl2.setFont(jl2.getFont ().deriveFont (16.0f));
        jl3.setFont(jl3.getFont ().deriveFont (16.0f));
        jl4.setFont(jl4.getFont ().deriveFont (16.0f));
        jl5.setFont(jl5.getFont ().deriveFont (16.0f));

        jb1.setFont(jl1.getFont ().deriveFont (14.0f));
        jb2.setFont(jb2.getFont ().deriveFont (14.0f));

        //setting up the layout
        jp2.setLayout(new GridLayout(6,1));
        this.setLayout(new GridLayout(1,3));
        jp2.add(jl1);
        jp2.add(jl2);
        jp2.add(jl3);
        jp2.add(jl4);
        jp2.add(jl5);
        jp2.add(jp1);

        this.setTitle("Room: " + room.getRoomNumber());
        this.add(new JPanel());
        this.add(jp2);
        this.add(new JPanel());
        this.setSize(800,600);
        this.setLocation(300,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        //close
        jb1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    dispose();

                }
            });

        //open the guest info window of the guest in the room
        jb2.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    if(!room.isAvailable()){
                        UI_Guest.main(h, h.findReservation(roomNum).getGuest());
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "There is currently no guest in the room", "Message", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            });
    }

    /**
     * return available for true, occupied for false
     * 
     * @param b the boolean which to be converted
     * @return available for true, occupied for false
     */
    private static String yesOrNo(boolean b){
        if(b)
            return "Available";
        else
            return "Occupied";
    }
}

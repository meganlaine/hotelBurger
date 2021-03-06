import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Write a description of class UI_Room here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UI_Room extends JFrame
{
    // instance variables - replace the example below with your own
    private JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7;
    private JPanel jp1, jp2, jp3, jp4, jp5;
    private JButton jb1, jb2, jb3, jb4;
    
    public static void main(Hotel h, int roomNum){
        UI_Room window = new UI_Room(h, roomNum);
        JFrame frame = new JFrame("Room " + roomNum);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    /**
     * Constructor for objects of class UI_Room
     */
    public UI_Room(Hotel h, int roomNum)
    {
        jl1 = new JLabel("Room: "+roomNum);
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jl2 = new JLabel("Room type: " + h.getRoom(String.valueOf(roomNum)).getRoomType());
        jl3 = new JLabel("Bed type: " + h.getRoom(String.valueOf(roomNum)).getBedType()); 
        

        
        
        this.setLayout(new GridLayout(2,1));
        this.add(jl1);
        this.add(jl2);
        this.setSize(800,600);
        this.setLocation(300,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
    }


}

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
    private JLabel jl1;
    private JPanel jp1;

    
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
        jl1 = new JLabel("Room "+roomNum+" to be implement");
        jp1 = new JPanel();
        jp1.add(jl1);
        
        this.setLayout(new GridLayout(1,1));
        this.add(jp1);
        this.setSize(800,600);
        this.setLocation(300,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
    }


}

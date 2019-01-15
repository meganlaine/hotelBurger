import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Write a description of class UI_Guest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UI_Guest extends JFrame
{
    private JPanel jp1, jp2, jp3, jp4, jp5, jp6, jp7;
    private JButton jb1, jb2, jb3;
    private JLabel jl1, jl2, jl3, jl4, jl5;

    public static void main(Hotel h)
    {
        UI_Guest window = new UI_Guest(h);
        JFrame frame = new JFrame("Guest");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public UI_Guest(Hotel h)
    {
        // put your code here
 
    }
}

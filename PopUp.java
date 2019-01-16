import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Write a description of class PopUp here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PopUp extends JFrame
{
    
    private JPanel jp1, jp2;
    private JButton jb1;
    private JLabel jl1;

    /**
     * Constructor for objects of class PopUp
     */
    public static void main(String args)
    {
        PopUp window = new PopUp(args);
        JFrame frame = new JFrame("Hotel App");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    
    public PopUp(String str)
    {
        jp1 = new JPanel();
        jp2 = new JPanel();

        jl1 = new JLabel(str);
        
        jb1 = new JButton("OK");
        
        
        this.setLayout(new GridLayout(4,1));
        jp1.add(jl1);
        //jp2.add(jl2);
        jp2.add(jb1);
        
        this.add(jp1);
        this.add(jp2);
        //this.add(jp2);

        this.setSize(300,150);
        this.setLocation(300,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("Warning");
        jb1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    dispose();
                }
            });
    }
}
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Write a description of class Welcome here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Welcome extends JFrame
{
    // instance variables - replace the example below with your own
    private JPanel jp1,jp2,jp3,jp4;
    private JLabel jl1,jl2;
    private JButton jb;
    
    /**
     * Constructor for objects of class Welcome
     */
    public static void main(String args[])
    {
        Welcome window = new Welcome();
        JFrame frame = new JFrame("Hotel App");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    Welcome(){
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();
        jl1 = new JLabel("Welcome to the Hotel Manager™ beta version! \n Team Burger");
        //jl2 = new JLabel("Made by Team Burger");
        
        jb = new JButton("Continue");
                
        this.setLayout(new GridLayout(4,1));
        jp1.add(jl1);
        //jp2.add(jl2);
        jp3.add(jb);
        
        this.add(jp2);
        this.add(jp1);
        //this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        this.setSize(800,600);
        this.setLocation(200,200);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
        jb.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    dispose();
                    UI.main(new String[0]);
                }
            }
        );
    }
}

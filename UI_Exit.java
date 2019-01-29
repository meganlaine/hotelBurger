import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * The Exit page for GUI
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 1/28/2019
 */
public class UI_Exit extends JFrame
{
    private JPanel jp1, jp2, jp3, jp4;
    private JButton jb1, jb2;
    private JLabel jl1;

    /**
     * Constructor for objects of class PopUp
     */
    public static void main(Hotel h)
    {
        UI_Exit window = new UI_Exit(h);
        JFrame frame = new JFrame("Hotel App");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public UI_Exit(Hotel h)
    {
        //instantiate components
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();

        jl1 = new JLabel("Are you sure you want to exit?");

        jb1 = new JButton("OK");
        jb2 = new JButton("Cancel");

        //setting up the layout of the window
        this.setLayout(new GridLayout(4,1));

        jp2.add(jl1);
        jp3.add(jb1);
        jp3.add(jb2);
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        //this.add(jp2);

        this.setSize(300,180);
        this.setLocation(300,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
        
        //exit the whole program
        jb1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    //Some exit program here
                    try{
                        h.save();
                        System.exit(0);
                    }
                    catch(FileNotFoundException ex){

                        JOptionPane.showMessageDialog(null, "File save failed", "Message", JOptionPane.PLAIN_MESSAGE);

                    }
                }
            });
            
        //do nothing
        jb2.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){

                    dispose();
                }
            });
    }
}

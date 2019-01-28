import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Write a description of class UI_Guest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UI_Report extends JFrame
{
    private JPanel jp3, jp4;
    private JButton jb1, jb2, jb3;
    private JLabel jl0, jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8;

    public static void main(Hotel h)
    {
        UI_Report window = new UI_Report(h);
        JFrame frame = new JFrame("Report");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public UI_Report(Hotel h)
    {
        jl0 = new JLabel(h.getName());
        jl1 = new JLabel("PhoneNumber: " + h.getPhoneNumber());
        jl2 = new JLabel("Address: " + h.getAddress());

        jl4 = new JLabel("Occupancy: " + h.getTotalGuestsInHotel() + " guests"); 
        jl8 = new JLabel("Revenue: $" + String.format("%.2f",h.getTotalSales()));


        jb1 = new JButton("OK");
        jp3 = new JPanel();
        jp3.add(jb1);
        
        jl0.setFont(jl0.getFont ().deriveFont (16.0f));
        jl1.setFont(jl1.getFont ().deriveFont (16.0f));
        jl2.setFont(jl2.getFont ().deriveFont (16.0f));       
        jl4.setFont(jl4.getFont ().deriveFont (16.0f));
        jl8.setFont(jl8.getFont ().deriveFont (16.0f));

        jb1.setFont(jb1.getFont ().deriveFont (14.0f));

        jp4 = new JPanel();
        jp4.setLayout(new GridLayout(7,1));
        
        jp4.add(new JPanel());
        jp4.add(jl0);
        jp4.add(jl1);
        jp4.add(jl2);
        jp4.add(jl4);
        jp4.add(jl8);

        jp4.add(jp3);
        this.setLayout(new GridBagLayout());
        this.add(jp4);
        this.setTitle("Report");
        this.setSize(800,600);
        this.setLocation(300,300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        jb1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    dispose();

                }
            });
    }

    private static String yesOrNo(boolean b){
        if(b)
            return "Yes";
        else
            return "No";
    }

}

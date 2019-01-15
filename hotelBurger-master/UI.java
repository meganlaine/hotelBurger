import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * Write a description of class UI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class UI extends JFrame
{
    private JPanel panel1, panel2, panel3,panel4, panel5, panel6;
    private JButton button1,button2, button3;
    private JLabel label1, label2, label3;
    private JTextField textField, textField2;

    /**
     * Constructor for objects of class UI
     */
    public static void main(String args[])
    {
        UI window = new UI();
        JFrame frame = new JFrame("Hotel App");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    UI(){
        panel1=new JPanel();
        panel2=new JPanel();
        panel3=new JPanel();        
        panel4=new JPanel();
        panel5=new JPanel();
        panel6=new JPanel();

        label1=new JLabel("user");
        label2=new JLabel("password");
        label3=new JLabel("Log In");
        button1=new JButton("log in");
        button2=new JButton("cancel");
        button3=new JButton("cheat");

        textField = new JTextField(10);
        textField2 = new JTextField(10);
        this.setLayout(new GridLayout(3,1));
        panel1.add(label1);   panel1.add(textField);
        panel2.add(label2);    panel2.add(textField2);
        panel3.add(button1);   panel3.add(button2);
        panel4.add(button3);
        panel6.add(label3);

        panel5.setLayout(new GridLayout(4,1));

        this.add(new JPanel());

        panel5.add(panel6);
        panel5.add(panel1);
        panel5.add(panel2);
        panel5.add(panel3);

        this.add(panel5);
        this.add(panel4);
        this.setTitle("Log in");
        this.setSize(600,500);
        this.setLocation(200,200);
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        button1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    String user = "admin";
                    String pwd = "password";
                    try{
                        if(textField.getText().equals(user) && textField2.getText().equals(pwd)){
                            dispose();
                            Hotel h = Simulator.main(new String[0]);
                            Menu.main(h);
                        }
                        else{
                            PopUp.main("Wrong username or password");
                        }
                    }
                    catch(FileNotFoundException ex){
                        int temp = JOptionPane.showConfirmDialog(null, "File corrupted or dosen't exist", "Warning", JOptionPane.YES_OPTION);

                    }
                }
            }
        );

        button2.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){

                    textField2.setText("");
                }
            }
        );
        button3.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    try{
                        Hotel h = Simulator.main(new String[0]);
                        Menu.main(h);
                        dispose();}
                    catch(FileNotFoundException ex){
                        int temp = JOptionPane.showConfirmDialog(null, "File corrupted or dosen't exist", "Warning", JOptionPane.YES_OPTION);

                    }
                }
            }
        );
    }
}

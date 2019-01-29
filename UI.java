import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The log in page for the GUI
 *
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 1/28/2019
 */
public class UI extends JFrame
{
    private JPanel panel1, panel2, panel3,panel4, panel5, panel6;
    private JButton button1,button2, button3, button4;
    private JLabel label1, label2, label3;
    private JTextField textField, textField2;

    /**
     * Constructor for objects of class UI
     */
    public static void main(String args[])
    {
        UI window = new UI();
        JFrame frame = new JFrame("Hotel App");
    }

    UI(){
        //instantiate components
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
        
        //setting up fonts for components
        label1.setFont(label1.getFont ().deriveFont (16.0f));
        label2.setFont(label2.getFont ().deriveFont (16.0f));
        label3.setFont(label3.getFont ().deriveFont (16.0f));
        button1.setFont(button1.getFont ().deriveFont (14.0f));
        button2.setFont(button2.getFont ().deriveFont (14.0f));
        button3.setFont(button3.getFont ().deriveFont (14.0f));
        
        //setting layout
        textField = new JTextField(10);
        textField2 = new JTextField(10);
        this.setLayout(new GridLayout(3,1));
        panel1.add(label1);    panel1.add(textField);
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
        
        //log in when the user name and password matches.
        button1.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    String user = "admin";
                    String pwd = "password";
                    try{
                        if(textField.getText().equals(user) && textField2.getText().equals(pwd)){
                            dispose();
                            Hotel h = Main.GUI_Starter(new String[0]);
                            Menu.main(h);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Wrong user name or password", "Message", JOptionPane.PLAIN_MESSAGE);;
                        }
                    }
                    catch(FileNotFoundException ex){
                        JOptionPane.showMessageDialog(null, "File corrupted or dosen't exist", "Warning", JOptionPane.PLAIN_MESSAGE);

                    }
                }
            }
        );
        
        //clear the content in textfiled
        button2.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    textField.setText("");
                    textField2.setText("");
                }
            }
        );
        
        //bypass the log in page
        button3.addActionListener(new ActionListener(){
                @Override    
                public void actionPerformed(ActionEvent e){
                    try{
                        Hotel h = Main.GUI_Starter(new String[0]);
                        Menu.main(h);
                        dispose();
                    }
                    catch(FileNotFoundException ex){
                        JOptionPane.showConfirmDialog(null, "File corrupted or dosen't exist", "Warning", JOptionPane.YES_OPTION);

                    }
                }
            }
        );

    }
}

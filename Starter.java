import java.io.FileNotFoundException;
import javax.swing.*;

/**
 * The starter of the program
 * 
 * @author Dale Berg, Nick Coyle, Megan Laine, Steven Liu
 * @version 1/28/2019
 */
class Starter{
    public static void main() {
        try{
            //popup a selection window
            int i = JOptionPane.showOptionDialog(null, "Please select the way you want to start", "Hello", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[] {"CommandLine", "GUI"},"GUI");
            if(i == 0){
                //run console version
                Main.main(new String[0]);
            }
            else{
                //run GUI version
                Welcome.main(new String[0]);
            }
        }

        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "File corrupted or dosen't exist", "Warning", JOptionPane.PLAIN_MESSAGE);

        }
    }
}
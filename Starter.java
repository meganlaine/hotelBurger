import java.io.FileNotFoundException;
import javax.swing.*;

class Starter{
    public static void main() {
        try{
            int i = JOptionPane.showOptionDialog(null, "Please select the way you want to start", "Hello", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[] {"CommandLine", "GUI"},"GUI");
            if(i == 0){
                Main.main(new String[0]);
            }
            else{
                Welcome.main(new String[0]);
            }
        }

        catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null, "File corrupted or dosen't exist", "Warning", JOptionPane.PLAIN_MESSAGE);

        }
    }
}
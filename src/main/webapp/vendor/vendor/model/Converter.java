package Animation;
import javax.swing.*;


public class Converter  {

    //constants declaration
    private static final double MILES_PER_KILOMETER = 0.621;

    //main method
    public static void main(String[] args) {
        //... Local variables
        String kmstr;    // String km before conversion to double.
        double km;       // Number of kilometers.
        double mi = 0;       // Number of miles.

        //... Input
        kmstr = JOptionPane.showInputDialog(null, "Enter miles.");
        km = Double.parseDouble(kmstr);

        //... Computation
        km = mi/ MILES_PER_KILOMETER;

        //... Output
        JOptionPane.showMessageDialog(null, mi + " miles" + km + " kilometers.");
    }

}


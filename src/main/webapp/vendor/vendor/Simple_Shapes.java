package Animation;
/*
 * A java code to draw a simple shape
 */
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;


@SuppressWarnings("serial")
public class Simple_Shapes extends Applet{
    //method for drawing graphics
    public void paint(Graphics g) {


        //draw the rectangle using lines

        g.drawLine(60,30,300,30);
        g.drawLine(300,30,300,150);
        g.drawLine(60,30,60,150);
        g.drawLine(60,150,300,150);

        //draw the first circle and set color to black
        g.drawOval(30, 105, 90, 90);
        g.setColor(Color.BLACK);


        //draw the second circle and set color to black
        g.drawOval(240,105,90,90);
        g.setColor(Color.BLACK);

    }

}


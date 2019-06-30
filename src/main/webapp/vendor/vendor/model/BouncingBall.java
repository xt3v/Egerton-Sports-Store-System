package Animation;

import java.applet.Applet;
import java.awt.*;

public class BouncingBall extends Applet implements Runnable{

    int x;
    int y;
    int increase_x;
    int increase_y;

    public void init(){
        x=20;
        y=20;
        increase_x=1;
        increase_y=1;

    }

    public void start(){
        Thread thread=new Thread(this);
        thread.start();
    }

    public void run(){
        while (true){
            repaint();
            try {
                Thread.sleep(10);
            }catch (Exception e){

            }

            if (x+40>400)
                increase_x=-1;
            if (x<10)
                increase_x=1;
            if (y+40>400)
                increase_y=-1;
            if (y<10)
                increase_y=1;

            x=x+increase_x*1;
            y=y+increase_y*1;
        }

    }

    public void paint(Graphics graphics){
        graphics.drawOval(x,y,40,40);

    }

}

/*
 * A java applet that simulates a pendulum
 */
package Animation;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class Pendulum extends JFrame {
    int pivotx,pivoty;
    double thetamax,theta;;
    double len=260;
    int x,y,ymax,xmax;
    int bobradius=30;
    int xsign=-1,ysign=1;
    double omega;

    public Pendulum()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,400);
        thetamax=60*Math.PI/180;
        pivotx=getWidth()/2;
        pivoty=40;
        ymax=(int) (pivoty+len*Math.cos(thetamax));
        xmax=(int) (pivotx+len*Math.sin(thetamax));
        x=xmax;
        y=ymax;
        theta=thetamax;
        System.out.println("pivotx:"+pivotx+" pivoty:"+pivoty);
        System.out.println("starting x:"+x+ "y:"+y);
        System.out.println("starting theta="+theta);
        t.start();
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("called");
        g.setColor(Color.WHITE);
        g.fillRect(0, 0,getWidth(),getHeight());
        g.setColor(Color.red);
        g.fillOval(pivotx-4,pivoty-4,8,8);
        g.drawLine(pivotx,pivoty, x,y);
        g.fillOval(x-bobradius/2,y-bobradius/2,bobradius,bobradius);

    }

    Thread t=new Thread()
    {
        public void run()
        {
            try {
                while(true)
                {
                    if(x>=pivotx+Math.abs(len*Math.sin(thetamax)))
                    {
                        System.out.println("right extreme");
                        xsign=-1;
                        ysign*=-1;
                        x=xmax-1;
                        Thread.sleep(40);
                    }
                    else if(x<=pivotx-Math.abs(len*Math.sin(thetamax)))
                    {
                        System.out.println("left extreme");
                        ysign*=-1;
                        xsign=1;
                        x=(int) (pivotx-Math.abs(len*Math.sin(thetamax))+2);
                        Thread.sleep(40);
                    }
                    else if(y>=pivoty+len)
                    {
                        ysign*=-1;
                        System.out.println("mean position");
                    }

                    omega=y/60*Math.PI/180;
                    double decr=xsign*omega;
                    System.out.println("decrement:"+decr);
                    theta=theta+decr;
                    x=(int) (pivotx+len*Math.sin(theta));
                    y=(int) (pivoty+len*Math.cos(theta));
                    System.out.println("new theta:"+theta);
                    repaint();
                    Thread.sleep(40);

                }
            } catch (InterruptedException ex) {}
        }
    };


    public static void main(String[] args) {
        new Pendulum().setVisible(true);
    }

}


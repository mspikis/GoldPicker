import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
/**
 * THe Game class contains the main features of the game: rectangle, score, respond and menu buttons
 * 
 * @author (Michail Pikis) 
 * @version (30/3/2017)
 */
public class Game extends JPanel
{
    //declare and initialize score
    public static int score=0; 
    
    //declare respond buttons
    public static JButton btSimple, btGolden; 
    
    //declare canvas
    public static ButtonCanvas2 canvas2;
    
    //declare variables
    public static double h, w; //Height, Width 
    public static boolean goldRec; //Golden rectangle indicator
    /**
     * init method that sets the Game JPanel 
     */
    public void init2()
    {
        //set layout
        setLayout(new BorderLayout() );
        
        //Create respond buttons
        btSimple = new JButton("Simple");
        add(btSimple,BorderLayout.LINE_START);
        btGolden = new JButton("Golden");
        add(btGolden,BorderLayout.LINE_END);
        
        //create canvas
        canvas2 = new ButtonCanvas2();    
        canvas2.setSize(400, 400);
        canvas2.setBackground(Color.white);
        add(canvas2,BorderLayout.CENTER);
        
        //create action handler
        JButtonHandler2 handler2 = new JButtonHandler2();
        btSimple.addActionListener( handler2 );
        btGolden.addActionListener( handler2 );
    }
    
}
/** 
 * ActionListener class 
 */
class JButtonHandler2 implements ActionListener
{
    /**
     * The actionPerformed method connects each button with its responding method.
     */
    public void actionPerformed( ActionEvent e) 
    {
        if (e.getSource() == Game.btSimple)
        {
            Game.canvas2.respondSimple();
        }
        if (e.getSource() == Game.btGolden) 
        {
            Game.canvas2.respondGolden();
        }
    }
}
/**
 * The ButtonCanvas2 class contains the rectangle, score and the buttons responding methods 
 */
class ButtonCanvas2 extends Canvas
{
    //declare timer
    public Timer timer1 = new Timer();;
    /**
     * Paint method.
     */
    public void paint( Graphics g )
    {
        //generate random height
        Game.h= Math.floor(Math.random() * 50) + 50;
        
        //one out of three times create a golden rectangle
         if (Math.random() <= 0.33)
        {
            Game.w = Game.h*(Math.sqrt(5)+1)/2;
            Game.goldRec = true;
        }
        
        //the rest of the times create random rectangle width depending on the level of difficulty
        else 
        {
            if (Math.random() < 0.5)
            {
                Game.w = Game.h*(Math.sqrt(5)+1)/2 + Math.floor(Math.random()*Menu.dflvl)+Menu.dflvl/2;
            }
            else
            {
                Game.w = Game.h*(Math.sqrt(5)+1)/2 - Math.floor(Math.random()*Menu.dflvl)-Menu.dflvl/2;
            }
            Game.goldRec = false;
        }
        
        //display the rectangle
        g.setColor(Color.orange);
        g.fillRect(100,200, (int) Game.w,(int) Game.h);
        
        //display score
        g.setColor(Color.blue);
        g.drawString("Golden Tokens:" + Game.score, 5,15);
        
        //call time limit
        qCountDwn();
    }
    /**
     * The qCountCDwn method sets a 7 seconds question time limit and then generates a new rectangle and reduces the score
     */
    public void qCountDwn()
    {
        timer1 = new Timer();
        timer1.schedule(new TimerTask()
        {
          @Override
          public void run() {
              repaint();
              Game.score-=5;
              
              //terminate timer
              timer1.cancel();
              timer1.purge();
               }
             }, 5000);

    }
    /**
     * When btSimple is pressed:
     * If the rectangle is not golden generate pop-up window displaying "Correct".
     * If the rectangle is golden generate pop-up window displaying "False".
     * Terminate timer.
     * Then repaint rectangle.
     */
    void respondSimple()
    {
        if (Game.goldRec == false)
        {
            JOptionPane.showMessageDialog(null,"Correct!");
            Game.score= Game.score+10;
        }
        else
        {
            JOptionPane.showMessageDialog(null,"False");
            Game.score-=5;
        }
        timer1.cancel();
        timer1.purge();
        repaint();
    }
     /**
     * When btGolden is pressed:
     * If the rectangle is golden generate pop-up window displaying "Correct".
     * If the rectangle is not golden generate pop-up window displaying "False"
     * Terminate timer..
     * Then repaint rectangle.
     */
    void respondGolden()
    {
        if (Game.goldRec == true)
        {
            JOptionPane.showMessageDialog(null,"Correct!");
            Game.score= Game.score+10;
        }
        else
        {
            JOptionPane.showMessageDialog(null,"False");
            Game.score= Game.score-5;
        }
        timer1.cancel();
        timer1.purge();
        repaint();
    }
    /**
     * Return height.
     */
    public double geth()
    {
        return Game.h;
    }
    /**
     * Return width.
     */
    public double getw()
    {
        return Game.w;
    }
    /**
     * Return score.
     */
    public int getscore()
    {
        return Game.score;
    }
    /**
     * Return true if the rectangle is gold.
     */
    public boolean getgoldRec()
    {
        return Game.goldRec;
    }
}

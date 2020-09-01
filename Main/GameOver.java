import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * The class GameOver contains: game over text, final score.
 * 
 * @author (Michail Pikis) 
 * @version (30/3/2017)
 */
public class GameOver extends JPanel

{
    //declare JLabel and canvas
    public static JLabel gover;
    public static LabelCanvas canvas3;
    /**
     * init methos that set the GameOver JPanel
     */
   public void init3()
    {
        //set layout
        setLayout(new GridLayout(3,1) );
        //create a text font
        Font font = new Font("", Font.BOLD,18);
        
        //create JLabel
        gover = new JLabel("  Your time is up!");
        gover.setFont(font);
        add(gover);
        
        //create canvas
        canvas3 = new LabelCanvas();    
        canvas3.setSize(400, 400);
        canvas3.setBackground(Color.white);
        add(canvas3);
    }
   public int getscore()
    {
        return Game.score;
    }
}
/**
 * The LabelCanvas class contains a text with the final score.
 */
class LabelCanvas extends Canvas
{
    /**
     * Paint method.
     */
    public void paint ( Graphics g)
    {
        g.drawString("You have gathered " + Game.score + " Golden Tokens.", 10,15);
    }

}

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * The Menu class contains basic instructions for the game and the ability to set the level of difficulty. 
 * 
 * @author (Michail Pikis) 
 * @version (30/3/2017)
 */
public class Menu extends JPanel
{
    //declare difficulty buttons
    public static JRadioButton btEasy, btInte, btExpe; 
    
    //declare Jlabel
    public static JLabel welc;
    
    //declare canvas
    public static ButtonCanvas1 canvas1;
    
    //declare and initialize the level of difficulty (divergence to golden ratio) = Easy 
    public static int dflvl=32;
    /**
     * init method that sets the Menu JPanel 
     */
    public void init1()
    {
        //GridLayout
        setLayout(new GridLayout(6,1) );
        
        //create a text font
        Font font = new Font("", Font.BOLD,18);
        
        //create welcome message 
        welc= new JLabel("  Welcome to GoldPicker");
        welc.setFont(font);
        add(welc);
        
        //create a canvas for text display
        canvas1 = new ButtonCanvas1();    
        canvas1.setSize(400, 400);
        canvas1.setBackground(Color.white);
        add(canvas1);
        
        //create difficulty buttons
        btEasy = new JRadioButton("Easy");
        add(btEasy);
        btInte = new JRadioButton("Intermidiate");
        add(btInte);
        btExpe = new JRadioButton("Expert");
        add(btExpe);
        
        //group dificulty buttons
        ButtonGroup group = new ButtonGroup();
        group.add(btEasy);
        group.add(btInte);
        group.add(btExpe);
        
        //create action handler for difficulty buttons
        JButtonHandler1 handler1 = new JButtonHandler1();
        btEasy.addActionListener( handler1 );
        btInte.addActionListener( handler1 );
        btExpe.addActionListener( handler1 );
    }
        public int getdflvl()
    {
        return dflvl;
    }
}
/** 
 * ActionListener class 
 */
class JButtonHandler1 implements ActionListener
{
    /**
     * The actionPerformed method connects each button with its responding method.
     */
    public void actionPerformed( ActionEvent e) 
    {
        if (e.getSource() == Menu.btEasy)
        {
           Menu.canvas1.respondEasy();
        }
        if (e.getSource() == Menu.btInte) 
        {
            Menu.canvas1.respondInte();
        }
        if (e.getSource() == Menu.btExpe) 
        {
            Menu.canvas1.respondExpe();
        }
    }
}
/**
 * The ButtonCanvas1 class contains the instruction text and the responding methods of the difficulty buttons
 */
class ButtonCanvas1 extends Canvas
{
    // declare variables to wrap the text
    public int x, y, curx, cury; //x =initial text x coordinate, y = initial text y coordinates, curx = current text x coordinate, cury= current text y coordinate
    public int lineHeight, wordWidth; //text proportions
    /**
     * Paint method.
     */
    public void paint( Graphics g )
    {
        // set colour
        g.setColor(Color.black);
        
        // get text proportions
        FontMetrics f = g.getFontMetrics();
        lineHeight = f.getHeight();
        
        //initialize text starting coordinates
        x= 10;
        y= 10;
        curx = x;
        cury = y;
        
        //create string text and array
        String s = new String("In this game you will be provided with a rectangle and you will have 5 seconds to guess wherther it's ratio is golden or not. Your long term objective is to give as many correct answers as you can in 2 minutes time. Have Fun!!!");
        String[] words = s.split(" ");
        for (String word : words)
        {
            // find the width of a word
            wordWidth = f.stringWidth(word + " ");
            
            // if text exceeds the total width then move to the next line
            if (curx + wordWidth >= x + 450)
            {
                cury += lineHeight;
                curx = x;
            }

            g.drawString(word, curx, cury);
    
            // move over to the right for next word
            curx += wordWidth;
        }

        g.drawString("Correct = 10 Golden Tokens / Wrong or Null = -5 Golden Tokens",10,75); 
    }
    /**
     * When btEasy is pressed:
     * Set the difficulty to easy.
     */
    void respondEasy()
    {
       Menu.dflvl = 37;
    }
     /**
     * When btInte is pressed:
     * Set the difficulty to Intermidiate.
     */
    void respondInte()
    {
        Menu.dflvl = 15;
    }
    /**
     * When btExpe is pressed:
     * Set the difficulty to Expert.
     */
    void respondExpe()
    {
        Menu.dflvl = 6;
    }
}

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The class Layout creates a JFrame and has the abbility to switch between JPanels.
 * 
 * @author (Michail Pikis) 
 * @version (30/3/2017)
 */
public class Layout extends JFrame
{
   // declare and create JPanel objects 
   public static JPanel panelCont = new JPanel();
   public static Menu panel1 = new Menu();
   public static Game panel2 = new Game();
   public static GameOver panel3 = new GameOver();
   
   //declare JButtons for switching JPanels
   public static JButton btStart, btMenu, btPlay;
   
   //declare layout
   public static CardLayout cl = new CardLayout();
   /*
    * Layout class constructor
    */ 
   public Layout()
    {
        //window title
        super("GoldPicker"); 
        
        //run each init method
        panel1.init1();
        panel2.init2();
        panel3.init3();
        
        //create JButtons for switching JPanels
        btStart = new JButton ("Start"); //Menu -> Game
        btMenu = new JButton ("Menu"); //Game -> Menu
        btPlay = new JButton("Play Again!?"); //GameOver -> Menu
        
        //initialize background colour
        panel1.setBackground(Color.WHITE);
        panel2.setBackground(Color.WHITE);
        panel3.setBackground(Color.WHITE);
        
        //add buttons to JPannels
        panel1.add(btStart);
        panel2.add(btMenu, BorderLayout.PAGE_END);
        panel3.add(btPlay);
        
        //create action handler
        JButtonHandler handler = new JButtonHandler();
        btStart.addActionListener( handler );
        btMenu.addActionListener( handler );
        btPlay.addActionListener( handler );
        
        //set layout
        panelCont.setLayout(cl);
        panelCont.add(panel1,"panel1");
        panelCont.add(panel2,"panel2");
        panelCont.add(panel3,"panel3");
        this.setContentPane(panelCont);
        
        //show Menu first
        cl.show(panelCont,"panel1");
    }
}
/**
 * Action listener class.
 */
class JButtonHandler implements ActionListener
     
{
    //declare timer
    Timer timer;
    public void actionPerformed( ActionEvent e) 
    
    {
        //when btStart start gets clicked start the game and set time limit
        if (e.getSource() == Layout.btStart)
        {
           //switch from Menu to Game
           Layout.cl.show(Layout.panelCont,"panel2");
           
           //set game's time limit to two minutes and if reached, switch from Game to GameOver
           timer = new Timer();
           timer.schedule(new TimerTask() 
           {

              @Override
              public void run() {
                  Layout.cl.show(Layout.panelCont,"panel3");
                  
                  //terminate timer
                  timer.cancel();
                  timer.purge();
                        }
           }, 120000);
        }
        
        //when btMenu menu gets clicked switch from Game to Menu and reset the score
        if (e.getSource() == Layout.btMenu) 
        {
            Layout.cl.show(Layout.panelCont,"panel1");
            Layout.panel2.score=0;
        }
        
        //when btPlay gets clicked switch from Game to Menu and reset the score
        if (e.getSource() == Layout.btPlay) 
        {
            Layout.cl.show(Layout.panelCont,"panel1");
            Layout.panel2.score=0;
        }
    }
}

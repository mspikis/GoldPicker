import javax.swing.*;
/**
 * The main class implements an application that runs the GoldPicker game.
 * Initializes the size and visibility of the JFrame.
 * 
 * @author (Michail Pikis) 
 * @version (30/3/2017)
 */
public class Main
{
     public static void main(String[] args)
    {
        //create new Layout JFrame class and exit when closed
        Layout g = new Layout();
        g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //set size and visibility
        g.setSize(500, 500); 
        g.setVisible(true); 
   }
}

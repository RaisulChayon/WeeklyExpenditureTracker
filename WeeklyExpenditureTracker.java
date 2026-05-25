/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package weeklyexpendituretracker;
import javax.swing.SwingUtilities;
/**
 *
 * @author ddh
 */
public class WeeklyExpenditureTracker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ExpenseTrackerGUI gui = new ExpenseTrackerGUI();
                gui.setVisible(true);
            }
        });
    }
    
}

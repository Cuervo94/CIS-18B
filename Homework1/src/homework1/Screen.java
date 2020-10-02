package homework1;

/**
 *
 * @author Pedro Longo
 */
public class Screen {
    public void displayMessage(String message){
        System.out.print(message);
    }
    
    public void displayDollarAmount(double amount){
        System.out.printf("$%,.2f", amount);
    }
}

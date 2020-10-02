package homework1;

/**
 *
 * @author Pedro Longo
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class Keypad {

    private Scanner input;

    public Keypad() {
        input = new Scanner(System.in);
    }

    public int getInput() {
        while (true) {
            try {
                return input.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Please select a number from the keypad. Try again:\n");
                input.next();
            }
        }
    }
}

package homework1;

/**
 *
 * @author Pedro Longo
 */
public class Withdrawal extends Transaction {

    private int amount;
    private Keypad keypad;
    private CashDispenser cashDispenser;

    private final static int CANCELLED = 6;

    public Withdrawal(int userAccountNumber, Screen atmScreen,
            BankDatabase atmBankDatabase, Keypad atmKeypad, CashDispenser atmCashDispenser) {

        super(userAccountNumber, atmScreen, atmBankDatabase);

        keypad = atmKeypad;
        cashDispenser = atmCashDispenser;
    }

    @Override
    public void execute() {
        boolean cashDispensed = false;
        double availableBalance;

        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        do {
            amount = displayMenuOfAmounts();

            if (amount != CANCELLED) {
                availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
                if (amount <= availableBalance) {
                    if (cashDispenser.isSufficientCashAvailable(amount)) {
                        bankDatabase.debit(getAccountNumber(), amount);

                        cashDispenser.dispenseCash(amount);
                        cashDispensed = true;

                        screen.displayMessage("\nYour cash has been dispensed.\n");
                        screen.displayMessage("Please take your cash now.\n");
                    } else {
                        screen.displayMessage("\nInsufficient cash available in the ATM.\n");
                        screen.displayMessage("Please choose a smaller amount.\n");
                    }
                } else {
                    screen.displayMessage("\nInsufficient funds in your account\n");
                    screen.displayMessage("Please choose a smaller amount.\n");
                }
            } else {
                screen.displayMessage("\nCanceling transaction...\n");
                return;
            }
        } while (!cashDispensed);        
    }
    
    private int displayMenuOfAmounts(){
        int userChoice = 0;
        
        Screen screen = getScreen();
        
        int[] amounts = {0, 20, 40, 60, 100, 200};
        
        while (userChoice == 0){
            screen.displayMessage("\nWithdrawal Menu:\n");
            screen.displayMessage("1 - $20\n");
            screen.displayMessage("2 - $40\n");
            screen.displayMessage("3 - $60\n");
            screen.displayMessage("4 - $100\n");
            screen.displayMessage("5 - $200\n");
            screen.displayMessage("6 - Cancel transaction\n");
            screen.displayMessage("Choose withdrawal amount: ");
            
            int input = keypad.getInput();
            
            switch (input) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    userChoice = amounts[input];
                    break;
                case CANCELLED:
                    userChoice = CANCELLED;
                    break;
                default:
                    screen.displayMessage("\nInvalid selection. Try again.\n");
            }
        }
        return userChoice;
    }
}

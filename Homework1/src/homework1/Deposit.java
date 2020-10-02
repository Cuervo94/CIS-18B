/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package homework1;

/**
 *
 * @author Pedro Longo
 */
public class Deposit extends Transaction {

    private double amount;
    private Keypad keypad;
    private DepositSlot depositSlot;
    private final static int CANCELLED = 0;

    public Deposit(int userAccountNumber, Screen atmScreen,
            BankDatabase atmBankDatabase, Keypad atmKeypad, DepositSlot atmDepositSlot) {

        super(userAccountNumber, atmScreen, atmBankDatabase);

        keypad = atmKeypad;
        depositSlot = atmDepositSlot;
    }

    @Override
    public void execute() {
        BankDatabase bankDatabase = getBankDatabase();
        Screen screen = getScreen();

        amount = promptForDepositAmount();

        if (amount != CANCELLED) {
            screen.displayMessage("\nPlease insert a deposit envelope containing ");
            screen.displayDollarAmount(amount);

            boolean envelopeReceived = depositSlot.isEnvelopeReceived();

            if (envelopeReceived) {
                screen.displayMessage("\nYour envelope has been received.\n");
                screen.displayMessage("NOTE: The money just deposited will not be available"
                        + "until we verify the amount of any enclosed cash and your checks clear.");

                bankDatabase.credit(getAccountNumber(), amount);
            } else {
                screen.displayMessage("\nYou did not insert an envelope, so the ATM has cancelled your transtaction.\n");
            }
        } else {
            screen.displayMessage("\nCancelling transaction...");

        }
    }

    private double promptForDepositAmount() {
        Screen screen = getScreen();

        screen.displayMessage("\nPlease enter a deposit amount in CENTS (or 0 to cancel): ");
        int input = keypad.getInput();

        if (input == CANCELLED) {
            return CANCELLED;
        } else {
            return (double) input / 100;
        }
    }
}

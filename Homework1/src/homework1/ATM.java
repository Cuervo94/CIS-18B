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
public class ATM {
    private boolean userAuthenticated;
    private int currentAccNumber;
    private Screen screen;
    private Keypad keypad;
    private CashDispenser cashDispenser;
    private DepositSlot depositSlot;
    private BankDatabase bankDatabase;
    
    //constants for main menu
    private static final int BALANCE_INQUIRY = 1;
    private static final int WITHDRAWAL = 2;
    private static final int DEPOSIT = 3;
    private static final int EXIT = 4;
    
    public ATM(){
        userAuthenticated = false;
        currentAccNumber = 0;
        screen = new Screen();
        keypad = new Keypad();
        cashDispenser = new CashDispenser();
        depositSlot = new DepositSlot();
        bankDatabase = new BankDatabase();        
    }
    
    public void start(){
        while(true){
            while(!userAuthenticated){
                screen.displayMessage("\nWelcome\n");
                authenticateUser();
            }
            performTransactions();
            userAuthenticated = false;
            currentAccNumber = 0;
            screen.displayMessage("\nThank you! Goodbye!\n");
        }
    }
    
    private void authenticateUser(){
        screen.displayMessage("\nPlease enter your account number: ");
        int accNumber = keypad.getInput();
        screen.displayMessage("\nEnter your PIN: ");
        int pin = keypad.getInput();
        
        userAuthenticated = bankDatabase.autheticateUser(accNumber, pin);
        
        if (userAuthenticated) {
            currentAccNumber = accNumber;
        } else {
            screen.displayMessage("\nInvalid account number or PIN. Please Try again\n");
        }
    }
    
    private void performTransactions(){
        Transaction currentTransaction = null;
        
        boolean userExited = false;
        
        while (!userExited){
            int mainMenuSelection = displayMainMenu();
            
            switch (mainMenuSelection){
                
                case BALANCE_INQUIRY:
                case WITHDRAWAL:
                case DEPOSIT:
                    
                    currentTransaction = createTransaction(mainMenuSelection);
                    
                    currentTransaction.execute();
                    break;
                case EXIT:
                    screen.displayMessage("\nExiting the system...\n");
                    userExited = true;
                    break;
                default:
                    screen.displayMessage("\nYou did not enter a valid selection. Try again.\n");
                    break;
            }
        }
    }
    
    private int displayMainMenu(){
        screen.displayMessage("\nMain menu:\n");
        screen.displayMessage("1 - View my balance\n");
        screen.displayMessage("2 - Withdrawal cash\n");
        screen.displayMessage("3 - Deposit funds\n");
        screen.displayMessage("4 - Exit\n");
        screen.displayMessage("Enter a choice: ");
        return keypad.getInput();
    }
    
    private Transaction createTransaction(int type){
        Transaction temp = null;
        
        switch (type){
            case BALANCE_INQUIRY:
                temp = new BalanceInquiry(currentAccNumber, screen, bankDatabase);
                break;
            case WITHDRAWAL:
                temp = new Withdrawal(currentAccNumber, screen, bankDatabase, keypad, cashDispenser);
                break;
            case DEPOSIT:
                temp = new Deposit(currentAccNumber, screen, bankDatabase, keypad, depositSlot);
                break;
        } 
        return temp;
    } 
}

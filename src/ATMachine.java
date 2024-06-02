import java.util.Scanner;
public class ATMachine {
    public static void main(String[] args) {
        // Initialize ATM with PIN and balance
        ATM atm = new ATM(5299, 1000.0);

        System.out.println("Welcome to the ATM");

        // Prompt for PIN
        Scanner scanner = new Scanner(System.in);
        int pinTries = 0;
        boolean pinValidated = false;

        while (pinTries < atm.getMaxPinTries()) {
            System.out.print("Enter your PIN: ");
            int enteredPIN = scanner.nextInt();

            // Validate PIN
            if (atm.validatePIN(enteredPIN)) {
                // Show menu if PIN is valid
                atm.showMenu();
                pinValidated = true;
                break;
            } else {
                // Display error if PIN is invalid
                System.out.println("Invalid PIN. Please try again.");
                pinTries++;
            }
        }

        if (!pinValidated) {
            System.out.println("Maximum PIN tries exceeded. Exiting...");
        } else {
            // Display final balance
            System.out.println("Final Balance: Rs." + atm.getCurrentBalance());
        }
    }
}

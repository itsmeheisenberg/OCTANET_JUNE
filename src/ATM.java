import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

class ATM {
    private final int PIN;
    private double currentBalance;
    private final ArrayList<Transaction> transactionHistory;

    public ATM(int pin, double balance) {
        this.PIN = pin;
        this.currentBalance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nTransaction History:");
                    displayTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    System.out.print("Enter account number to transfer: ");
                    String accountNumber = scanner.next();
                    transfer(transferAmount, accountNumber);
                    break;
                case 5:
                    System.out.println("Quitting... Thank You for using!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);
    }

    private void withdraw(double amount) {
        if (amount > 0 && amount <= currentBalance) {
            currentBalance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
            System.out.println("Withdrawal successful.");
            displayCurrentBalance();

        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    private void deposit(double amount) {
        if (amount > 0) {
            currentBalance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            System.out.println("Deposit successful.");
            displayCurrentBalance();

        } else {
            System.out.println("Invalid amount.");
        }
    }

    private void transfer(double amount, String accountNumber) {
        // Assume transfer is successful for demonstration purposes
        if (amount > 0 && amount <= currentBalance) {
            currentBalance -= amount;
            transactionHistory.add(new Transaction("Transfer to " + accountNumber, amount));
            System.out.println("Transfer successful.");
            displayCurrentBalance();

        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }

    private void displayTransactionHistory() {
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction.getType() + ": Rs." + transaction.getAmount());
        }
    }
    private void displayCurrentBalance() {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("Current Balance: Rs." + df.format(currentBalance));
    }


    public boolean validatePIN(int pin) {
        return this.PIN == pin;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public int getMaxPinTries() {
        return 3;
    }
}

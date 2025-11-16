import java.util.UUID;

import javax.swing.text.View;

class Menu {
    private final String text = "1. Add a user\n" +
            "2. View user balances\n" +
            "3. Perform a transfer\n" +
            "4. View all transactions for a specific user";
    private final String textDev = "5. DEV - remove a transfer by ID\r\n" +
            "6. DEV - check transfer validity";

    private boolean devMode;
    private TransactionsService service;

    public Menu(boolean dev) {
        this.devMode = dev;
        this.service = new TransactionsService();
    }

    private void printPrompt() {
        System.out.println(text);
        if (this.devMode) {
            System.out.println(textDev);
        }
        System.out.println((this.devMode ? 7 : 5) + ". Finish execution");
    }

    private void addUser() {
        System.out.println("Enter a user name and a balance");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String input = scanner.nextLine();
        String[] paths = input.split(" ");
        if (paths.length > 2 || paths.length < 2) {
            System.out.println("[x] Error, input should be : name balance");
            return;
        }
        String name = paths[0];
        double balance = (double) Integer.parseInt(paths[1]);
        User user = new User(name, balance);
        service.setUser(user);
        System.out.println("User with id = " + user.getIdentifier() + " is added");
    }

    private void viewUserBalances() {
        System.out.println("Enter a user ID");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String input = scanner.nextLine();
        String[] paths = input.split(" ");
        if (paths.length != 1) {
            System.out.println("[x] Error, input should be : userID");
            return;
        }
        int userId = Integer.parseInt(paths[0]);
        double userBalance;
        try {
            userBalance = this.service.getUserBalance(userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println(this.service.getUsers().getUserById(userId).getName() + " - " + userBalance);
    }

    public void performTransfer() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String input = scanner.nextLine();
        String[] paths = input.split(" ");
        if (paths.length < 3 || paths.length > 3) {
            System.out.println("[x] Error, input should be : sender ID, recipient ID, transfer amount");
            return;
        }
        int userId1 = Integer.parseInt(paths[0]);
        int userId2 = Integer.parseInt(paths[1]);
        int amount = Integer.parseInt(paths[2]);
        try {
            User sender = this.service.getUsers().getUserById(userId1);
            User recipient = this.service.getUsers().getUserById(userId2);
            this.service.transferTransaction(amount, userId1, userId2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("The transfer is completed");
    }

    private void viewAllTransactionsForSpecificUser() {
        System.out.println("Enter a user ID");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String input = scanner.nextLine();
        String[] paths = input.split(" ");
        if (paths.length != 1) {
            System.out.println("[x] Error, input should be : userID");
            return;
        }
        int userId = Integer.parseInt(paths[0]);
        User user;
        try {
            user = this.service.getUsers().getUserById(userId);
            Transaction[] arr = user.getTransactionsList().toArray();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].getTransactionCategory() == "OUTGOING") {
                    System.out.println(
                            "TO " + arr[i].getRecipient().getName() + "(id = " + arr[i].getRecipient().getIdentifier()
                                    + ") " + arr[i].getAmount() + " with id = " + arr[i].getIdentifier());
                }
                if (arr[i].getTransactionCategory() == "INCOMING") {
                    System.out.println(
                            "FROM " + arr[i].getSender().getName() + "(id = " + arr[i].getSender().getIdentifier()
                                    + ") " + arr[i].getAmount() + " with id = " + arr[i].getIdentifier());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private void removeTransactionById() {
        try {
            System.out.println("Enter a user ID and a transfer ID");
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String input = scanner.nextLine();
            String[] paths = input.split(" ");
            if (paths.length > 2 || paths.length < 2) {
                System.out.println("[x] Error, input should be : userID, transfer ID");
                return;
            }
            int userId = Integer.parseInt(paths[0]);
            String uuid = paths[1];
            User user = this.service.getUsers().getUserById(userId);
            Transaction temp = user.transactionsList.getTransactionById(uuid);
            String name = "";
            int id = 0;
            double amount = 0;
            String tr = "";
            if (temp.getSender().getIdentifier() == userId) {
                tr = "To";
                name = temp.getRecipient().getName();
                id = temp.getRecipient().getIdentifier();
                amount = -1 * temp.getAmount();
            } else {
                tr = "From";
                name = temp.getSender().getName();
                id = temp.getSender().getIdentifier();
                amount = temp.getAmount();
            }
            user.transactionsList.removeTransactionById(uuid);
            System.out.println("Transfer " + tr + " " + name + "(id = " + id + ") " + amount + " removed");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private void checkTransferValidity() {
        Transaction[] arr = this.service.checkValidity();
        System.out.println("Check results:");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getTransactionCategory() == "OUTGOING") {
                System.out.println(
                        arr[i].getSender().getName() + "(id = " +
                                arr[i].getSender().getIdentifier() +
                                ") has an unacknowledged transfer id = " +
                                arr[i].getIdentifier() +
                                " to " + arr[i].getRecipient().getName() +
                                "(id = " + arr[i].getRecipient().getIdentifier() + ") for "
                                + (-1 * arr[i].getAmount()));
            } else {
                System.out.println(
                        arr[i].getRecipient().getName() + "(id = " +
                                arr[i].getRecipient().getIdentifier() +
                                ") has an unacknowledged transfer id = " +
                                arr[i].getIdentifier() +
                                " from " + arr[i].getSender().getName() +
                                "(id = " + arr[i].getSender().getIdentifier() + ") for " + (arr[i].getAmount()));
            }
        }
    }

    private void executeCommand(int numberOfCommand) {
        if (numberOfCommand == 7 || (!this.devMode && numberOfCommand == 5)) {
            System.exit(0);
        }

        switch (numberOfCommand) {
            case 1:
                addUser();
                break;
            case 2:
                viewUserBalances();
                break;
            case 3:
                performTransfer();
                break;
            case 4:
                viewAllTransactionsForSpecificUser();
                break;
            case 5:
                removeTransactionById();
                break;
            case 6:
                checkTransferValidity();
                break;
            default:
                break;
        }
    }

    public void parsingCommand(String input) {
        String[] paths = input.split(" ");
        if (paths.length > 1 || paths[0].length() > 1) {
            System.out.println("[x] Error: Please enter number of command above!");
            return;
        }
        int number = Integer.parseInt(paths[0]);
        if (number <= 0 || ((this.devMode == false) && number > 5) || number > 7) {
            System.out.println("[x] Error: Please enter number of command above!");
            return;
        }
        executeCommand(number);
    }

    public void run() {
        while (true) {
            printPrompt();
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String input = scanner.nextLine();
            parsingCommand(input);
            System.out.println("---------------------------------------------------------");
        }
    }
}
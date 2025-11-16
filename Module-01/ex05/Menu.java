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
        } catch (RuntimeException e) {
            System.out.println("id not found");
            return;
        }
        System.out.println(this.service.getUsers().getUserById(userId).getName() + " - " + userBalance);
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
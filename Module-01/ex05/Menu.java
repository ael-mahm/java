class Menu {
    private final String text = "1. Add a user\n" +
            "2. View user balances\n" +
            "3. Perform a transfer\n" +
            "4. View all transactions for a specific user";
    private final String textDev = "5. DEV - remove a transfer by ID\r\n" +
            "6. DEV - check transfer validity";

    private boolean devMode;

    public Menu(boolean dev) {
        this.devMode = dev;
    }

    private void printPrompt() {
        System.out.println(text);
        if (this.devMode) {
            System.out.println(textDev);
        }
        System.out.println((this.devMode ? 7 : 5) + ". Finish execution");
    }

    private void executeCommand(int numberOfCommand) {
        if (numberOfCommand == 7 || (!this.devMode && numberOfCommand == 5)) {
            System.exit(0);
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
        }
    }
}
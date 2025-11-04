class Program {
    public static void main(String[] args) {
        System.out.println("[->] Enter number:");
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int number = scanner.nextInt();
        if (number < 2) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }

        int limit = (int) Math.sqrt(number);
        int steps = 0;

        for (int i = 2; i <= limit; i++) {
            steps++;
            if (number % i == 0) {
                System.out.println("false " + steps);
                return;
            }
        }

        System.out.println("true " + limit);
    }
}
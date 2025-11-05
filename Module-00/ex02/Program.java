class Program {
    public static int sum(int number) {
        int sum = 0;
        while (number != 0) {
            int tmp = number % 10;
            sum += tmp;
            number /= 10;
        }
        return sum;
    }

    public static boolean isPrime(int number) {
        int limit = (int) Math.sqrt(number);
        if (number <= 1)
            return false;
        for (int i = 2; i <= limit; i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int count = 0;
        while (true) {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            int number = scanner.nextInt();
            if (number == 42)
                break;
            if (number < 2) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            int sum = sum(number);
            if (isPrime(sum))
                count++;
        }
        System.out.println("Count of coffee-request : " + count);
    }
}
class Program {
    public static void main(String[] args) {
        int number = 479598;
        int sum = 0;

        if (number < 100000 || number > 999999) {
            System.err.println("[x] Error: Number must be six-digits");
            System.exit(-1);
        }

        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }

        System.out.println(sum);
    }
}

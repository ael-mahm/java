class Program {
    public static void main(String[] args) {
        int weekNumber = 1;
        int result = 0;
        while (true) {
            int min = 9;
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String week = scanner.next();
            if (week.equals("42")) {
                System.out.println(result);
                break;
            }
            int weekNum = scanner.nextInt();
            boolean isCorrect = checkWeek(week, weekNumber, weekNum);
            if (!isCorrect) {
                System.out.println("IllegalArgument");
                System.exit(-1);
            }
            scanner = new java.util.Scanner(System.in);
            for (int i = 0; i < 5; ++i) {
                int note = scanner.nextInt();
                if (note < 1 || note > 9) {
                    System.out.println("IllegalArgument");
                    System.exit(-1);
                }
                if (note < min) {
                    min = note;
                }
            }
            result = result * 10 + min;
            ++weekNumber;
        }
        printGraph(result, weekNumber);
    }

    public static boolean checkWeek(String line, int number, int weekNum) {
        boolean result = line.equals("Week") && number == weekNum;
        return result;
    }

    public static void printGraph(int result, int week) {
        int min = result % 10;
        result = result / 10;
        week--;
        if (result != 0) {
            printGraph(result, week);
        }
        System.out.print("Week " + week + " ");
        for (int i = 0; i < min; ++i) {
            System.out.print("=");
        }
        System.out.println(">");
    }
}
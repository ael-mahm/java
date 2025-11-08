class Program {
    public static void main(String[] args) {
        String students[] = new String[10];
        String days[] = new String[10];
        int hours[] = new int[10];
        int countStudents = 0;
        int countLessons = 0;

        while (true) {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String studentName = scanner.next();
            if (studentName.equals(".")) {
                break;
            }
            if (studentName.length() > 10) {
                System.out.println("IllegalArgument.");
                System.exit(-1);
            }
            students[countStudents] = studentName;
            countStudents++;
            if (countStudents >= 10) {
                System.out.println("Maximum number of students reached.");
                break;
            }
        }

        while (true) {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String input = scanner.next();
            if (input.equals(".")) {
                break;
            }

            int hour = stringToInt(input);

            if (hour < 1 || hour > 6) {
                System.out.println("IllegalArgument.");
                System.exit(-1);
            }
            hours[countLessons] = hour;
            String day = scanner.next();
            if (!day.equals("MO") && !day.equals("TU") && !day.equals("WE") && !day.equals("TH") && !day.equals("FR")
                    && !day.equals("SA") && !day.equals("SU")) {
                System.out.println("IllegalArgument.");
                System.exit(-1);
            }
            days[countLessons] = day;
            countLessons++;
            if (countLessons >= 10) {
                System.out.println("Maximum number of Lessons reached.");
                break;
            }
        }

        while (true) {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            String name = scanner.next();
            if (name.equals(".")) {
                break;
            }
            if (!checkNameStudent(name, students, countStudents)) {
                System.out.println("StudentNotFound.");
                System.exit(-1);
            }
            String input = scanner.next();
            int hour = stringToInt(input);
            if (hour < 1 || hour > 6) {
                System.out.println("IllegalArgument.");
                System.exit(-1);
            }
            input = scanner.next();
            int day = stringToInt(input);
            if (day < 1 || day > 31) {
                System.out.println("IllegalArgument.");
                System.exit(-1);
            }
            input = scanner.next();
            boolean present;
            if (input.equals("HERE")) {
                present = true;
            } else if (input.equals("NOT_HERE")) {
                present = false;
            } else {
                System.out.println("IllegalArgument.");
                System.exit(-1);
            }
        }

        // for (int i = 0; i < countStudents; i++) {
        // System.out.println(students[i]);
        // }
        // for (int i = 0; i < countLessons; i++) {
        // System.out.println(hours[i] + " " + days[i]);
        // }
    }

    public static boolean checkNameStudent(String name, String[] students, int countStudents) {
        for (int i = 0; i < countStudents; i++) {
            if (students[i].equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static int stringToInt(String str) {
        int r = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                System.out.println("IllegalArgument.");
                System.exit(-1);
            }
            r = r * 10 + (c - '0');
        }
        return r;
    }
}
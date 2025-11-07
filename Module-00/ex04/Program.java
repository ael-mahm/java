class Program {
    public static void main(String[] args) {
        int numBmp = 65535;
        int[] counts = new int[numBmp];
        int[] numberOfChar = new int[numBmp];
        char[] chars = new char[numBmp];

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String line = scanner.nextLine();

        char[] text = line.toCharArray();
        for (int i = 0; i < text.length; i++) {
            if (text[i] == '4' && i + 1 < text.length && text[i + 1] == '2') {
                break;
            }
            counts[text[i]]++;
        }
        int index = 0;
        for (int i = 0; i < numBmp; i++) {
            if (counts[i] > 0) {
                chars[index] = (char) i;
                numberOfChar[index] = counts[i];
                index++;
            }
        }
        for (int i = 0; i < index; i++) {
            for (int j = 0; j < index - 1; j++) {
                if (numberOfChar[j] < numberOfChar[j + 1]) {
                    int temp = numberOfChar[j];
                    numberOfChar[j] = numberOfChar[j + 1];
                    numberOfChar[j + 1] = temp;
                    char tempChar = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = tempChar;
                } else if (numberOfChar[j] == numberOfChar[j + 1] && chars[j] > chars[j + 1]) {
                    char tempChar = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = tempChar;
                }
            }
        }
        printGraph(numberOfChar, chars, index);
    }

    public static void printGraph(int numberOfChar[], char chars[], int index) {
        int maxFreq = numberOfChar[0];
        int number = 10;
        while (number != 0) {
            for (int i = 0; i < index; i++) {
                int height = (numberOfChar[i] * 10) / maxFreq;
                if (height >= number) {
                    System.out.print("# ");
                } else {
                    System.out.print("    ");
                }
            }
            System.out.println();
            if (number == 1) {
                for (int i = 0; i < index; i++) {
                    System.out.print(chars[i] + " ");
                }
                System.out.println();
            }
            number--;
        }
    }
}
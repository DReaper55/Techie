package org.example.programming_and_algorithm;

import java.util.Scanner;

public class TimeToWordConverter {

    private static final String[] NUMBERS = {
            "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen",
            "twenty", "twenty-one", "twenty-two", "twenty-three", "twenty-four", "twenty-five", "twenty-six",
            "twenty-seven", "twenty-eight", "twenty-nine"
    };

    public static void main(String[] args) {

    }

    public static void run() {
        System.out.println("This is a Time-to-Word converter program. \nInput your time as Hour and Minute\n");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input your Hour as a number: ");
        int hours = scanner.nextInt();

        System.out.print("Input your Minutes as a number: ");
        int minutes = scanner.nextInt();

        String convertedTime = convertTimeToWords(hours, minutes);

        System.out.println("The time is " + convertedTime);

        scanner.close();
    }

    private static String convertTimeToWords(int hours, int minutes) {
        if (minutes == 0) {
            return NUMBERS[hours] + " o’clock";
        } else if (minutes == 15) {
            return "quarter past " + NUMBERS[hours];
        } else if (minutes == 30) {
            return "half past " + NUMBERS[hours];
        } else if (minutes == 45) {
            return "quarter to " + NUMBERS[(hours % 12) + 1];
        } else if (minutes == 1) {
            return "one minute past " + NUMBERS[hours];
        } else if (minutes < 30) {
            return NUMBERS[minutes] + " minutes past " + NUMBERS[hours];
        } else {
            return NUMBERS[60 - minutes] + " minutes to " + NUMBERS[(hours % 12) + 1];
        }
    }
}

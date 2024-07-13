package org.example.programming_and_algorithm;

import java.util.Scanner;

public class DigitsSummation {
    public static void main(String[] args) {
    }

    public static void runSummationFromArgs(String number){
        int[] digits = convertToDigitArray(number);
        int sum = sumOfDigits(digits, digits.length - 1);
        System.out.println("Sum of digits: " + sum);
    }

    public static void runSummationFromConsoleInput(){
        System.out.println("This is a Digit Summation program. \nInput a number and I'll add all the digits in it\n");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Input your number: ");
        String number = scanner.next();

        if(number.length() > 16){
            System.out.println("This number is too long, please enter a number not more than 17digits long\n\n");
            runSummationFromConsoleInput();
        }

        int[] digits = convertToDigitArray(number);
        int sum = sumOfDigits(digits, digits.length - 1);
        System.out.println("Sum of digits: " + sum);
    }

    // Convert the number string to an array of digits
    private static int[] convertToDigitArray(String number) {
        int[] digits = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            digits[i] = number.charAt(i) - '0';
        }
        return digits;
    }

    // Recursive method to sum the digits of the array
    private static int sumOfDigits(int[] digits, int index) {
        // Return 0 if the index is negative
        if (index < 0) {
            return 0;
        }
        // Return the current digit plus the sum of the remaining digits
        return digits[index] + sumOfDigits(digits, index - 1);
    }
}

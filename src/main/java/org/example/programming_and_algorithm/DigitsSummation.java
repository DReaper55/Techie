package org.example.programming_and_algorithm;

public class DigitsSummation {
    public static void main(String[] args) {
        String number = "1234445";
        int[] digits = convertToDigitArray(number);
        int sum = sumOfDigits(digits, digits.length - 1);
        System.out.println("Sum of digits: " + sum);
    }

    // Convert the number string to an array of digits
    public static int[] convertToDigitArray(String number) {
        int[] digits = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            digits[i] = number.charAt(i) - '0';
        }
        return digits;
    }

    // Recursive method to sum the digits of the array
    public static int sumOfDigits(int[] digits, int index) {
        // Return 0 if the index is negative
        if (index < 0) {
            return 0;
        }
        // Return the current digit plus the sum of the remaining digits
        return digits[index] + sumOfDigits(digits, index - 1);
    }
}

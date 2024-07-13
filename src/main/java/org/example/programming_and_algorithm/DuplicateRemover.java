package org.example.programming_and_algorithm;

import org.example.programming_and_algorithm.utils.CustomHashSet;

import java.util.Scanner;


public class DuplicateRemover {
    public static void main(String[] args) {
        /*int[][] array = {
                {1, 3, 1, 2, 3, 4, 4, 3, 5},
                {1, 1, 1, 1, 1, 1, 1}
        };*/
    }

    public static void runWithInputArgs(int[][] array) {
        removeDuplicates(array);
        printArray(array);
    }

    public static void runWithConsoleInput() {
        int[][] array = getConsoleInput();

        if(array.length < 1){
            return;
        }

        System.out.println("\nFor this array:");
        printArray(array);

        removeDuplicates(array);

        System.out.println("\nThe new array with replaced duplicates is:");
        printArray(array);
    }

    private static int[][] getConsoleInput() {
        System.out.println("This is a Duplicate Remover program. \nInput an array of numbers and I'll replace all the duplicates with 0\n");

        Scanner scanner = new Scanner(System.in);

        // Read the number of rows
        System.out.print("Enter the number of rows: ");
        int n = scanner.nextInt();

        if(n > 200000){
            System.out.println("This size is too large. \nPlease restart the program enter a row size not more than 200,000\n\n");
            return new int[0][];
        }

        int[][] array = new int[n][];

        // Read each row
        for (int i = 0; i < n; i++) {
            System.out.print("\nEnter the number of elements in row " + (i + 1) + ": ");
            int m = scanner.nextInt();
            array[i] = new int[m];

            System.out.println("Enter the elements of row " + (i + 1) + ": ");
            for (int j = 0; j < m; j++) {
                array[i][j] = scanner.nextInt();
            }
        }

        return array;
    }

//    Print the elements of an array
    private static void printArray(int[][] array) {
        for (int[] row : array) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

//    Replace duplicated elements with 0
    private static void removeDuplicates(int[][] array) {
        for (int[] row : array) {
            CustomHashSet seen = new CustomHashSet();

            for (int j = 0; j < row.length; j++) {
                if (seen.contains(row[j])) {
                    row[j] = 0;
                } else {
                    seen.add(row[j]);
                }
            }
        }
    }
}

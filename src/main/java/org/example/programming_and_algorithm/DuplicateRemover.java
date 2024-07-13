package org.example.programming_and_algorithm;

import org.example.programming_and_algorithm.utils.CustomHashSet;


public class DuplicateRemover {
    public static void main(String[] args) {
        int[][] array = {
                {1, 3, 1, 2, 3, 4, 4, 3, 5},
                {1, 1, 1, 1, 1, 1, 1}
        };

        removeDuplicates(array);

        for (int[] row : array) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static void removeDuplicates(int[][] array) {
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

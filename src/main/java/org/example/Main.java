package org.example;

import org.example.programming_and_algorithm.DigitsSummation;
import org.example.programming_and_algorithm.DuplicateRemover;
import org.example.programming_and_algorithm.TimeToWordConverter;

public class Main {
    public static void main(String[] args) {
//        ........................................
//        Programming and Algorithm
//        ........................................
        TimeToWordConverter.run();

        /*int[][] array = {
                {1, 3, 1, 2, 3, 4, 4, 3, 5},
                {1, 1, 1, 1, 1, 1, 1}
        };
        DuplicateRemover.runWithInputArgs(array);*/

        DuplicateRemover.runWithConsoleInput();

//        DigitsSummation.runSummationFromArgs("1234445");

        DigitsSummation.runSummationFromConsoleInput();
    }
}
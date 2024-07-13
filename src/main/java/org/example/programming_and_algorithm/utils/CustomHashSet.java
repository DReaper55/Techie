package org.example.programming_and_algorithm.utils;

import java.util.HashSet;

public class CustomHashSet {
    private final HashSet<Integer> set;

    public CustomHashSet() {
        this.set = new HashSet<>();
    }

    public boolean contains(int key) {
        for (Integer integer : set) {
            if (integer == key) {
                return true;
            }
        }
        return false;
    }

    public void add(int key) {
        set.add(key);
    }
}

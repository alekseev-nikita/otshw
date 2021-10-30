package ru.otus;

import java.util.Objects;

public class TestsHandler {
    public static  <T, V> void assertion(T expected, V actual) {
        if (!Objects.equals(expected, actual)) {
            System.err.println(String.format("Expected: {0}. Actual: {1}", expected.toString(), actual.toString()));
            throw new AssertionError();
        }
    }
}

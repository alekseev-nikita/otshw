package ru.otus;

public class TestResults {
    private int passed;
    private int errors;

    public TestResults() {};

    public void addErrors() {
        this.errors++;
    }

    public void addPassed() {
        this.passed++;
    }

    public int getErrors() {
        return errors;
    }

    public int getPassed() {
        return passed;
    }

    public void printResult() {
        if(errors == 0){
            System.out.println(String.format("Passed: %d; Errors: 0", passed));
        } else {
            System.err.println(String.format("Passed: %d; Errors: %d",passed, errors));
        }
    }
}

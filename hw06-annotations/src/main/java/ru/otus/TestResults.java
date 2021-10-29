package ru.otus;

public class TestResults {
    private int passed;
    private int errors;

    public TestResults() {};

    private void addErrors() {
        this.errors++;
    }

    private void addPassed() {
        this.passed++;
    }

    public int getErrors() {
        return errors;
    }

    public int getPassed() {
        return passed;
    }

    public String results() {
        if(errors == 0){
            return String.format("Passed: %d; Errors: 0", passed);
        } else {
            return String.format("Passed: %d; Errors: %d",passed, errors);
        }
    }
    public void addResult(boolean result) {
        if (result) {
            addPassed();
        }
        else {
            addErrors();
        }
    }
}

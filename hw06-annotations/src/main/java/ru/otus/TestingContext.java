package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestingContext {
    private List<Method> cases = new ArrayList<>();
    private List<Method> before = new ArrayList<>();
    private List<Method> after = new ArrayList<>();

    public TestingContext(Class<?> TestClass) {
        for (Method m: TestClass.getDeclaredMethods()){
            if (m.isAnnotationPresent(Test.class)){
                cases.add(m);
            }
            else if (m.isAnnotationPresent(Before.class)) {
                before.add(m);
            }
            else if (m.isAnnotationPresent(After.class)) {
                after.add(m);
            }
        }
    }

    public List<Method> getAfter() {
        return after;
    }
    public List<Method> getBefore() {
        return before;
    }
    public List<Method> getCases() {
        return cases;
    }
}

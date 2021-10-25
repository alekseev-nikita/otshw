package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    static int passed;
    static int errors;
    private static List<Method> cases = new ArrayList<>();
    private static List<Method> before = new ArrayList<>();
    private static List<Method> after = new ArrayList<>();

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName("ru.otus.Tests");

        setCases(clazz);

        runTests(clazz);

        printResult();
    }
    private static void printResult() {
        if(errors == 0){
            System.out.println(String.format("All tests passed %d/%d", passed, cases.size()));
        } else {
            System.err.println(String.format("%d errors in %d total", errors, cases.size()));
        }
    }

    private static void runTests(Class clz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> constructor = clz.getConstructor();
        var instance = (Tests)constructor.newInstance();

        for (Method method : cases) {
            try{
                setupRun(before, instance);
                method.invoke(instance);
                passed++;
            }
            catch (Exception e) {
                errors++;
            }
            finally {
                setupRun(after, instance);
            }
        }
    }

    private static void setupRun(List<Method> setup, Tests inst) throws InvocationTargetException, IllegalAccessException {
        for (Method m : setup) {
            m.invoke(inst);
        }
    }

    private static void setCases(Class<?> TestClass){
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
}

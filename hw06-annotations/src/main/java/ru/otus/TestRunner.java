package ru.otus;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class TestRunner {

    public static void run(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        TestingContext context = getContext(clazz);
        TestResults testResults = new TestResults();

        for (Method mTest : context.getCases() ) {
            TestRunner testRunner = new TestRunner();

            testResults.addResult( testRunner.runTests(clazz, mTest, context) );
        }

        printResult(testResults.results());
    }
    private static void printResult(String result) {
        System.out.println(result);
    }

    private boolean runTests(Class<?> clz, Method method, TestingContext testingContext) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var instance = clz.getDeclaredConstructor().newInstance();

        try{
            invokeAll(testingContext.getBefore(), instance);
            method.invoke(instance);
        }
        catch (Exception e) {
            return false;
        }
        finally {
            try {
                invokeAll(testingContext.getAfter(), instance);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }

    private static void invokeAll(List<Method> methods, Object inst) throws InvocationTargetException, IllegalAccessException {
        for (Method m : methods) {
            m.invoke(inst);
        }
    }

    private static TestingContext getContext(Class<?> TestClass){
        return new TestingContext(TestClass);
    }
}

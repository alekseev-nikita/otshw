package ru.otus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class TestRunner {

    public static void run(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        TestingContext context = getContext(clazz);

        TestResults testResults = runTests(clazz, context);

        printResult(testResults.results());
    }
    private static void printResult(String result) {
        System.out.println(result);
    }

    private static TestResults runTests(Class<?> clz, TestingContext testingContext) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        TestResults testResults = new TestResults();
        for (Method method : testingContext.getCases()) {
            var instance = clz.getDeclaredConstructor().newInstance();
            try{
                invokeAll(testingContext.getBefore(), instance);
                method.invoke(instance);
                testResults.addPassed();
            }
            catch (Exception e) {
                testResults.addErrors();
            }
            finally {
                try {
                    invokeAll(testingContext.getAfter(), instance);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return testResults;
    }

    private static void invokeAll(List<Method> methods, Object inst) throws InvocationTargetException, IllegalAccessException {
        for (Method m : methods) {
            m.invoke(inst);
        }
    }

    private static TestingContext getContext(Class<?> testClass){
        return new TestingContext(testClass);
    }
}

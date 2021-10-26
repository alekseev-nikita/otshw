package ru.otus;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class TestRunner {

    public static void run(Class<?> clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        TestingContext context = getContext(clazz);

        TestResults testResults = runTests(clazz, context);

        testResults.printResult();
    }

    private static TestResults runTests(Class clz, TestingContext testingContext) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        TestResults testResults = new TestResults();
        Constructor<?> constructor = clz.getConstructor();
        var instance = clz.getDeclaredConstructor().newInstance();

        for (Method method : testingContext.getCases()) {
            try{
                setupRun(testingContext.getBefore(), instance);
                method.invoke(instance);
                testResults.addPassed();
            }
            catch (Exception e) {
                testResults.addErrors();
            }
            finally {
                setupRun(testingContext.getAfter(), instance);
            }
        }
        return testResults;
    }

    private static void setupRun(List<Method> setup, Object inst) throws InvocationTargetException, IllegalAccessException {
        for (Method m : setup) {
            m.invoke(inst);
        }
    }

    private static TestingContext getContext(Class<?> TestClass){
        return new TestingContext(TestClass);
    }
}

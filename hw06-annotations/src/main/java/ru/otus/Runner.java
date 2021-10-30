package ru.otus;

import java.lang.reflect.InvocationTargetException;

public class Runner {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TestRunner.run(Class.forName("ru.otus.Tests"));
    }
}

package ru.otus.Utils;

import ru.otus.Main.Logging;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProxyUtil {
    public static Set<Method> annotatedMethods() throws NoSuchMethodException {
        Set<Method> methods = new HashSet<>();

        List<Method> declatedMethods = Arrays.stream(Logginable.class.getDeclaredMethods()).collect(Collectors.toList());
        for (Method m : declatedMethods) {
            if (Logging.class.getDeclaredMethod(m.getName(), m.getParameterTypes()).isAnnotationPresent(Log.class)) {
                methods.add(m);
            }
        }
        return methods;
    }
    public static String methodDetails(Set<Method> methodSet, Method m, Object[] args) {
        String log = "";
        if (methodSet.contains(m)){
            log += String.format("Method Name: %s \n", m.getName());
            for (int i = 0; i < args.length; i++) {
                log += String.format("Parameter %d: %s \n",i+1, args[i]);
            }
        }
        return log;
    }
}

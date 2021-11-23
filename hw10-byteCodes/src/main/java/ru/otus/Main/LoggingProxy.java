package ru.otus.Main;

import ru.otus.Utils.Logginable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;
import java.util.logging.Logger;

import static ru.otus.Utils.ProxyUtil.annotatedMethods;
import static ru.otus.Utils.ProxyUtil.methodDetails;

public class LoggingProxy {
    public LoggingProxy() {

    }

    public static Logginable createInstance() throws NoSuchMethodException {
        InvocationHandler handler = new LoggingInvocationHandler(new Logging());
        return (Logginable) Proxy.newProxyInstance(LoggingProxy.class.getClassLoader(),
                new Class<?>[]{Logginable.class}, handler);
    }

    static class LoggingInvocationHandler implements InvocationHandler {
        private final Logginable logging;
        private final Set<Method> methods;
        private static Logger logger = Logger.getLogger(LoggingInvocationHandler.class.getName());

        LoggingInvocationHandler(Logginable logging) throws NoSuchMethodException {
            this.logging = logging;
            this.methods = annotatedMethods();
        }

        @Override
        public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
            String log = methodDetails(methods, m, args);
            logger.info(log);
            return m.invoke(logging, args);
        }
    }
}

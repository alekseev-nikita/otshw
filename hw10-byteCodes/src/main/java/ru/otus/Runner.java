package ru.otus;

import ru.otus.Main.LoggingProxy;
import ru.otus.Utils.Logginable;

public class Runner {
    public static void main(String[] args) throws NoSuchMethodException {
        Logginable logging = LoggingProxy.createInstance();

        logging.calculation(2);
        logging.calculation(33, 2);
        logging.calculation(4, 2, "text");
    }
}

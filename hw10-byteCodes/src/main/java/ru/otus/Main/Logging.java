package ru.otus.Main;

import ru.otus.Utils.Log;
import ru.otus.Utils.Logginable;

public class Logging implements Logginable {
    public Logging(){

    }

    @Log
    @Override
    public void calculation(int param1) {
        System.out.println(String.format("Called with params %d", param1));
    }
    @Log
    @Override
    public void calculation(int param1, int param2) {
        System.out.println(String.format("Called with params %d, %d", param1, param2));
    }
    @Log
    @Override
    public void calculation(int param1, int param2, String param3) {
        System.out.println(String.format("Called with params %d, %d, %s", param1, param2, param3));
    }
}

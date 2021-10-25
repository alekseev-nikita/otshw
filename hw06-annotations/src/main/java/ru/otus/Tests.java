package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;
import static ru.otus.TestsHandler.*;

import java.util.HashMap;

public class Tests {
    private HashMap<String, String> band;

    public Tests() {
        band = new HashMap<>();
    }

    @Before
    public void setBand() {
        band.put("John", "rhythm guitar");
        band.put("Paul", "bass guitar");
        band.put("George", "lead guitar");
        band.put("Ringo", "drums");
    }

    @After
    public void clearBand() {
        band.clear();
    }

    @Test
    public void test1() {
        System.out.println("test1");
        assertion(0, band.size());
    }

    @Test
    public void test2() {
        System.out.println("test2");
        assertion(4, band.size());
    }

    @Test
    public void test3() {
        System.out.println("test3");
        assertion(false, band.containsKey("Sam"));
    }

}

package ru.severyuchin.service;

import java.util.Collection;

public interface CounterService {

    boolean createCounter(String name);

    boolean setCounterValue(String name, Integer value);

    Integer getCounterValue(String name);

    boolean deleteCounter(String name);

    Integer getCounterSum();

    Collection<String> getCounterNames();

}

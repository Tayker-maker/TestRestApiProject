package ru.severyuchin.service.impl;

import org.springframework.stereotype.Service;
import ru.severyuchin.service.CounterService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class LocalCounterService implements CounterService {

    Map<String, Integer> counterMap;

    public LocalCounterService() {
        counterMap = new HashMap<>();
    }


    @Override
    public boolean createCounter(String name) {
        // Корректно в случаи обращени 1 пользователя в 1 момент времени.
        if (counterMap.containsKey(name)) return false;
        counterMap.put(name, 0);
        return true;
    }

    @Override
    public boolean setCounterValue(String name, Integer value) {
        if (counterMap.containsKey(name)) {
            counterMap.put(name, value);
            return true;
        }
        return false;
    }

    @Override
    public Integer getCounterValue(String name) {
        if (counterMap.containsKey(name)){
            return counterMap.get(name);
        }
        return null;
    }

    @Override
    public boolean deleteCounter(String name) {
        if(counterMap.containsKey(name)) {
            counterMap.remove(name);
            return true;
        }
        return false;
    }

    @Override
    public Integer getCounterSum() {
        return counterMap.values().stream()
                .reduce(Integer::sum)
                .orElse(0);
    }

    @Override
    public Collection<String> getCounterNames() {
        return counterMap.keySet();
    }
}

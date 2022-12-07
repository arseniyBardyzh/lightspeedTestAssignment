package com.lightspeed.incrementor.repository.impl;

import com.lightspeed.incrementor.repository.api.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Repository
public class CounterRepositoryImpl implements CounterRepository {

    private static final long INITIATE_COUNTER_VALUE = 0L;
    private final Map<String, Long> counterStorage;

    @Autowired
    public CounterRepositoryImpl(final Map<String, Long> counterStorage) {
        this.counterStorage = counterStorage;
    }

    @Override
    public void createCounter(String name) {
        counterStorage.put(name, INITIATE_COUNTER_VALUE);
    }

    @Override
    public Long getCounterValue(String name) {
        return counterStorage.get(name);
    }

    @Override
    public void updateCounterValue(String name) {
        Long counterValue = counterStorage.get(name);
        counterValue += 1;
        counterStorage.put(name, counterValue);
    }

    @Override
    public void deleteCounter(String name) {
        counterStorage.remove(name);
    }

    @Override
    public Set<String> getCountersName() {
        return counterStorage.keySet();
    }

    @Override
    public Collection<Long> getCountersValue() {
        return counterStorage.values();
    }
}

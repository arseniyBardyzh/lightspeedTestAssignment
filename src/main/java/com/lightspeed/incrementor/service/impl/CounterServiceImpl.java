package com.lightspeed.incrementor.service.impl;

import com.lightspeed.incrementor.exception.CounterException;
import com.lightspeed.incrementor.repository.api.CounterRepository;
import com.lightspeed.incrementor.service.api.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class CounterServiceImpl implements CounterService {
    private final CounterRepository counterRepository;

    @Autowired
    public CounterServiceImpl(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Override
    public void createCounter(String counterName) {
        if(checkCounterName(counterName)){
            String errorMessage = String.format(CounterException.COUNTER_ALREADY_EXIST, counterName);
            throw new CounterException(errorMessage);
        }
        counterRepository.createCounter(counterName);
    }

    @Override
    public void incrementCounterByName(String counterName) {
        if(!checkCounterName(counterName)){
            String errorMessage = String.format(CounterException.COUNTER_NOT_AVAILABLE, counterName);
            throw new CounterException(errorMessage);
        }
        checkMaximumValue(counterName);
        counterRepository.updateCounterValue(counterName);
    }

    @Override
    public long getCounterValueByCounterName(String counterName) {
        if(!checkCounterName(counterName)){
            String errorMessage = String.format(CounterException.COUNTER_NOT_AVAILABLE, counterName);
            throw new CounterException(errorMessage);
        }
        return counterRepository.getCounterValue(counterName);
    }

    @Override
    public void deleteCounterByName(String counterName) {
        if(!checkCounterName(counterName)){
            String errorMessage = String.format(CounterException.COUNTER_NOT_AVAILABLE, counterName);
            throw new CounterException(errorMessage);
        }
        counterRepository.deleteCounter(counterName);
    }

    @Override
    public long getSumForAllCounters() {
        Long retValue = 0L;
        Collection<Long> allValues = counterRepository.getCountersValue();
        for(Long value : allValues){
            if(Long.MAX_VALUE - retValue < value){
                throw new CounterException(CounterException.SUM_OF_COUNTER_BIGGER_LONG);
            }
            retValue += value;
        }
        return retValue;
    }

    @Override
    public Set<String> getAllCounterName() {
        return counterRepository.getCountersName();
    }

    /**
     * Check counter in counter storage
     * @return if counter exist - return true, else - false
     */
    private boolean checkCounterName(String counterName){
        Optional<Long> counterValueOpt = Optional.ofNullable(counterRepository.getCounterValue(counterName));
        return counterValueOpt.isPresent();
    }

    /**
     * Check max value of counter
     */
    private void checkMaximumValue(String counterName){
        if (counterRepository.getCounterValue(counterName) == Long.MAX_VALUE){
            String errorMessage = String.format(CounterException.COUNTER_IS_MAXIMUM, counterName);
            throw new CounterException(errorMessage);
        }
    }
}

package com.lightspeed.incrementor.service;

import com.lightspeed.incrementor.exception.CounterException;
import com.lightspeed.incrementor.repository.api.CounterRepository;
import com.lightspeed.incrementor.service.impl.CounterServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CounterServiceTest {

    @Mock
    private CounterRepository counterRepository;

    @InjectMocks
    CounterServiceImpl counterService;

    @Test
    void createCounter_ShouldCreateCounter_WhenMapIsEmpty(){
        when(counterRepository.getCounterValue("name")).thenReturn(null);
        counterService.createCounter("name");
    }

    @Test
    void createCounter_ShouldThrowAnException_WhenCounterAlreadyExist(){
        when(counterRepository.getCounterValue("name")).thenReturn(0L);
        assertThrows(CounterException.class, () -> counterService.createCounter("name"));
    }

    @Test
    void incrementCounter_ShouldIncrementCounter_WhenCounterAlreadyExist(){
        when(counterRepository.getCounterValue("name")).thenReturn(0L);
        counterService.incrementCounterByName("name");
        verify(counterRepository, times(2)).getCounterValue("name");
        verify(counterRepository).updateCounterValue("name");
    }

    @Test
    void incrementCounter_ShouldThrowAnException_WhenCounterIsNotExist(){
        when(counterRepository.getCounterValue("name")).thenReturn(0L);
        assertThrows(CounterException.class, () -> counterService.createCounter("name"));
    }

    @Test
    void incrementCounter_ShouldThrowAnException_WhenCounterValueIsMax(){
        when(counterRepository.getCounterValue("name")).thenReturn(Long.MAX_VALUE);
        assertThrows(CounterException.class, () -> counterService.createCounter("name"));
    }

    @Test
    void getSumForAllCounters_ShouldReturnSum_WhenEverythingIsOK(){
        List<Long> returnedList = new ArrayList<>();
        returnedList.add(0L);
        returnedList.add(3L);
        returnedList.add(2L);
        when(counterRepository.getCountersValue()).thenReturn(returnedList);
        Long serviceResult = counterService.getSumForAllCounters();
        assertEquals(5L, serviceResult);
    }

    @Test
    void getSumForAllCounters_ShouldThrowAnException_WhenSumHigherThenMax(){
        List<Long> returnedList = new ArrayList<>();
        returnedList.add(0L);
        returnedList.add(Long.MAX_VALUE-1);
        returnedList.add(2L);
        when(counterRepository.getCountersValue()).thenReturn(returnedList);
        assertThrows(CounterException.class, () -> counterService.getSumForAllCounters());
    }
}

package com.lightspeed.incrementor.web.controller;

import com.lightspeed.incrementor.service.api.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Rest Controller to handle with counters
 */
@RestController
@RequestMapping("/counter")
public class CounterController {
    private final CounterService counterService;

    @Autowired
    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @PostMapping("/{name}")
    public void createCounter(@PathVariable ("name") String counterName){
        counterService.createCounter(counterName);
    }

    @PutMapping("/{name}/increment")
    public void incrementCounterByName(@PathVariable ("name") String counterName){
        counterService.incrementCounterByName(counterName);
    }

    @GetMapping("/{name}")
    public long getCounterValueByName(@PathVariable ("name") String counterName){
        return counterService.getCounterValueByCounterName(counterName);
    }

    @DeleteMapping("/{name}")
    public void deleteCounterByName(@PathVariable ("name") String counterName){
        counterService.deleteCounterByName(counterName);
    }

    @GetMapping("/all-values")
    public long getAllCountersValue(){
        return counterService.getSumForAllCounters();
    }
    @GetMapping("/all-names")
    public Collection<String> getAllCountersName(){
        return counterService.getAllCounterName();
    }
}

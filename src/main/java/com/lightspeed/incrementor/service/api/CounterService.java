package com.lightspeed.incrementor.service.api;

import java.util.Set;

/**
 * Service to work with counter
 */
public interface CounterService {
    /**
     * Create new Counter
     * @param counterName counterName
     */
    void createCounter(String counterName);

    /**
     * Increment counter
     * @param counterName counter Name
     */
    void incrementCounterByName(String counterName);

    /**
     * Get Counter Value
     * @param counterName counter name
     * @return current counter number
     */
    long getCounterValueByCounterName(String counterName);

    /**
     * Delete Counter
     * @param counterName counter name
     */
    void deleteCounterByName(String counterName);

    /**
     * Sum values for all counter
     * @return Sum
     */
    long getSumForAllCounters();

    /**
     * Get List of counter name
     * @return Counter name List
     */
    Set<String> getAllCounterName();
}

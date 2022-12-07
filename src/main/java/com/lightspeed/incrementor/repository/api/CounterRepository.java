package com.lightspeed.incrementor.repository.api;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

/**
 * Repository to interact with CounterDataStorage
 */
public interface CounterRepository {
    /**
     * create new unique Counter
     * @param name counter Name
     */
    void createCounter(String name);

    /**
     * Get Counter Value By Name
     * @param name counter name
     * @return Optional counter Value
     */
    Long getCounterValue(String name);

    /**
     * update value by counter name
     * @param name counter name
     */
    void updateCounterValue(String name);

    /**
     * Delete counter by Counter name
     * @param name counter name
     * @return counter value
     */
    void deleteCounter(String name);

    /**
     * Get All Counter name
     * @return counters name
     */
    Set<String> getCountersName();

    /**
     * Get All Counter values
     * @return counters values
     */
    Collection<Long> getCountersValue();
}

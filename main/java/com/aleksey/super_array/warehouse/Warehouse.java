package com.aleksey.super_array.warehouse;

import com.aleksey.super_array.entity.SuperArray;
import com.aleksey.super_array.entity.SuperArrayStatistic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Warehouse {

    private static final Logger logger = LogManager.getLogger();
    private static Warehouse instance;
    private final Map<Long, SuperArrayStatistic> statistics = new HashMap<>();

    private Warehouse() {
    }

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void put(SuperArray superArray) {
        update(superArray);
    }

    public void update(SuperArray superArray) {
        if (superArray == null) {
            return;
        }
        logger.info("Update statistic in warehouse.");
        statistics.put(superArray.getId(), SuperArrayStatistic.from(superArray));
    }

    public Optional<SuperArrayStatistic> get(long id) {
        return Optional.ofNullable(statistics.get(id));
    }

    public void remove(long id) {
        statistics.remove(id);
    }
}


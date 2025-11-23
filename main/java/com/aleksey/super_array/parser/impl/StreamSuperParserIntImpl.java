package com.aleksey.super_array.parser.impl;

import com.aleksey.super_array.parser.SuperParserInt;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamSuperParserIntImpl implements SuperParserInt {
    private static final String NUMBER_SEPARATOR_PATTERN = "\\s*[; ,.]\\s*";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<int[]> parse(List<String> array) {
        List<int[]> result = array.stream()
                .filter(line -> !line.isBlank())
                .map(this::parseLine)
                .collect(Collectors.toList());
        logger.log(Level.INFO, "Parser worked.");
        return result;
    }

    private int[] parseLine(String line) {
        return Stream.of(line.split(NUMBER_SEPARATOR_PATTERN))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}


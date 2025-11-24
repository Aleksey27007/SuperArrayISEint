package com.aleksey.super_array.parser.impl;

import com.aleksey.super_array.parser.SuperParserInt;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SuperParserIntImpl implements SuperParserInt {
    private static final String NUMBER_SEPARATOR_PATTERN = "\\s*[; ,.]\\s*";
    private static final Logger logger = LogManager.getLogger();

    @Override
    public List<int[]> parse(List<String> array) {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            if (!array.get(i).isBlank()) {
                int[] parsedLine = parseLine(array.get(i));
                result.add(parsedLine);
            }
            logger.warn("The string is empty.");
        }
        return result;
    }

    private int[] parseLine(String line) {
        String[] substring = line.split(NUMBER_SEPARATOR_PATTERN);
        int[] result = new int[substring.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(substring[i]);
        }
        return result;
    }
}

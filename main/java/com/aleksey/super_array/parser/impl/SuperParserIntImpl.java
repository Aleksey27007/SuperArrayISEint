package com.aleksey.super_array.parser.impl;

import com.aleksey.super_array.parser.SuperParserInt;

import java.util.ArrayList;

public class SuperParserIntImpl implements SuperParserInt {
    private static final String REGEX = "\\s*[;,.]\\s*";
    private int elementCounter = 0;

    @Override
    public int[] parse(ArrayList<String> array) {
        int[] temp;
        int[] result = new int[getElementCounter(array)];
        int position = 0;
        for (int i = 0; i < array.size(); i++) {
            temp = parseLine(array.get(i));
            System.arraycopy(temp, 0, result, position, temp.length);
            position += temp.length;
        }
        return result;
    }

    private int[] parseLine(String line) {
        String [] substring = line.split(REGEX);
        int[] result = new int[substring.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(substring[i]);
        }
        return result;
    }

    private int getElementCounter(ArrayList<String> array) {
        for (int i = 0; i < array.size(); i++) {
            elementCounter += array.get(i).split(REGEX).length;
        }
        return elementCounter;
    }
}

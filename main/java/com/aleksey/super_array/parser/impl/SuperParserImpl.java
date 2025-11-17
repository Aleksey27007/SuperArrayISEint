package com.aleksey.super_array.parser.impl;

import com.aleksey.super_array.parser.SuperParser;

public class SuperParserImpl implements SuperParser {
    private static final String REGEX = "\\s*[;,.]\\s*";

    @Override
    public int[] parse(String text) {
        String [] substring = text.split(REGEX);
        int[] result = new int[substring.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(substring[i]);
        }

        return result;
    }
}

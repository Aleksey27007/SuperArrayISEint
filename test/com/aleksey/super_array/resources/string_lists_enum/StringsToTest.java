package com.aleksey.super_array.resources.string_lists_enum;

import java.util.List;

public enum StringsToTest {
    STRING_TO_TEST(List.of(
            "1; 2; 3",
            "1; 2; x3; 6..5; 77 ",
            "",
            "13; 12, 11a",
            "10; 5; 4",
            "11; 2; 3 ;4,",
            "1, 2, 3 , 12, 5, 7; 10",
            "1;3;3;4;1;6",
            "1q; 2; 3a",
            "1; 2; x3; 6..5; 77 ",
            "qass",
            "13;5; 6; 1; 14. 8",
            "",
            "11; 2; 1 ; 6; 1; 2; 9",
            "1; 2; 3",
            "1; 2; x3; 6..5; 77 ",
            "",
            "11; 2",
            "1; 2; 3",
            "1; 2; x3; 6..5; 77 ",
            "",
            "11; 2"));

    private List<String> stringList;

    StringsToTest(List<String> stringList) {
        this.stringList = stringList;
    }

    public List<String> getStringList() {
        return stringList;
    }

    @Override
    public String toString() {
        return "StringsToTest{" +
                "stringList=" + stringList +
                '}';
    }
}

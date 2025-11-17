package com.aleksey.super_array.reader.impl;

import com.aleksey.super_array.reader.SuperReader;
import com.aleksey.super_array.validator.StringValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class SuperReaderImpl implements SuperReader {

    private static final String PATH = "D:\\IntJava\\innowise\\super_array\\main\\resources\\file";
    private static final StringValidator stringValidator = new StringValidator();

    @Override
    public ArrayList<String> superRead(String fileName) {
        String absolutePath = PATH + "\\" + fileName;
        ArrayList<String> list = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(absolutePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(stringValidator.isTheLineSuitable(line)) {
                    list.add(line);
                }
            }
            return list;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}

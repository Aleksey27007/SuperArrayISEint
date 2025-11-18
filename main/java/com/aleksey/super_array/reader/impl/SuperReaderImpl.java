package com.aleksey.super_array.reader.impl;

import com.aleksey.super_array.excepsion.CustomArrayException;
import com.aleksey.super_array.reader.SuperReader;
import com.aleksey.super_array.validator.StringValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SuperReaderImpl implements SuperReader {
    private final Path baseDirectory;
    private final StringValidator stringValidator;

    public SuperReaderImpl(Path baseDirectory, StringValidator stringValidator) throws CustomArrayException {
        if (baseDirectory == null) {
            throw new CustomArrayException("baseDirectory must not be null");
        }
        if (stringValidator == null) {
            throw new IllegalArgumentException("stringValidator must not be null");
        }
        this.baseDirectory = baseDirectory;
        this.stringValidator = stringValidator;
    }

    @Override
    public ArrayList<String> superRead(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("fileName must not be null or blank");
        }

        ArrayList<String> list = new ArrayList<>();
        Path absolutePath = baseDirectory.resolve(fileName);

        try (BufferedReader reader = Files.newBufferedReader(absolutePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(stringValidator.isTheLineSuitable(line)) {
                    list.add(line);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read file: " + absolutePath, e);
        }

        return list;
    }
}

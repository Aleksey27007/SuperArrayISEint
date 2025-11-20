package com.aleksey.super_array.reader.impl;

import com.aleksey.super_array.excepsion.CustomArrayException;
import com.aleksey.super_array.reader.SuperReader;
import com.aleksey.super_array.validator.impl.StringValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SuperReaderImpl implements SuperReader {
    private final Path baseDirectory;
    private final StringValidatorImpl stringValidator;
    private static final Logger logger = LogManager.getLogger();

    public SuperReaderImpl(Path baseDirectory, StringValidatorImpl stringValidator) throws CustomArrayException {
        if (baseDirectory == null) {
            throw new CustomArrayException("baseDirectory must not be null");
        }
        if (stringValidator == null) {
            throw new CustomArrayException("stringValidator must not be null");
        }
        this.baseDirectory = baseDirectory;
        this.stringValidator = stringValidator;
    }

    @Override
    public List<String> superRead(String fileName) throws CustomArrayException {
        if (fileName == null || fileName.isBlank()) {
            logger.log(Level.ERROR, "fileName must not be null or blank " + SuperReaderImpl.class.getName());
            throw new CustomArrayException("fileName must not be null or blank");
        }

        List<String> list = new ArrayList<>();
        Path absolutePath = baseDirectory.resolve(fileName);

        try (BufferedReader reader = Files.newBufferedReader(absolutePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(stringValidator.isTheLineSuitable(line)) {
                    list.add(line);
                }
            }
        } catch (IOException e) {
            logger.log(Level.ERROR, "Unable to read file: " + absolutePath + e + SuperReaderImpl.class.getName());
            throw new CustomArrayException("Unable to read file: " + absolutePath, e);
        }

        return list;
    }
}

package com.aleksey.super_array.reader.impl;

import com.aleksey.super_array.excepsion.CustomArrayException;
import com.aleksey.super_array.reader.SuperReader;
import com.aleksey.super_array.validator.impl.StringValidatorImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamSuperReaderImpl implements SuperReader {
    private static final Logger logger = LogManager.getLogger();
    private final Path baseDirectory;
    private final StringValidatorImpl stringValidator;


    public StreamSuperReaderImpl(Path baseDirectory, StringValidatorImpl stringValidator) throws CustomArrayException {
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
            logger.log(Level.ERROR, "fileName must not be null or blank " + StreamSuperReaderImpl.class.getName());
            throw new CustomArrayException("fileName must not be null or blank");
        }

        Path absolutePath = baseDirectory.resolve(fileName);

        try (Stream<String> lines = Files.lines(absolutePath)) {
            return lines
                    .filter(stringValidator::isTheLineSuitable)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.log(Level.ERROR, "Unable to read file: " + absolutePath + e + StreamSuperReaderImpl.class.getName());
            throw new CustomArrayException("Unable to read file: " + absolutePath, e);
        }
    }
}


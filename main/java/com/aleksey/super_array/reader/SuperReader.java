package com.aleksey.super_array.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.io.FileInputStream;

public class SuperReader {

//    private static final String FILE_NAME = "fileNumbers";
//    private static final String EXTENSION = ".txt";

    private static final String PATH = "D:\\IntJava\\innowise\\super_array\\main\\resources\\file";
    private final String fileName;
//    private final String extension;
    private final String absolutePath;

    public SuperReader(String fileName) {
        this.fileName = fileName;
        this.absolutePath = PATH + "\\" + fileName;
    }

    private Optional<String> superRead() {
        Optional<String> optionalString = Optional.empty();

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(absolutePath)))) {
            String line;

            while ((line = reader.readLine()) != null) {
                optionalString = Optional.of(line);
            }
            return optionalString;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void checkReader() {
        System.out.println(superRead());
    }
}

package com.aleksey.super_array.reader;

import com.aleksey.super_array.excepsion.CustomArrayException;

import java.util.List;

public interface SuperReader {
    List<String> superRead(String fileName) throws CustomArrayException;
}

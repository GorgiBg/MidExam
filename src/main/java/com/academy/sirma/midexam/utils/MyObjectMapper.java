package com.academy.sirma.midexam.utils;

import com.academy.sirma.midexam.constants.StringConstants;
import com.academy.sirma.midexam.entities.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MyObjectMapper extends ObjectMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        configureMapper();
    }

    public static void configureMapper() {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    // read list of items from json
    public static List<Employee> loadItemsFromJson(String path) {

        try {
            return objectMapper.readValue(new File(path), new TypeReference<List<Employee>>() {
            });
        } catch (IOException e) {
            System.out.println(StringConstants.ERROR_LOADING_JSON);
        }
        return null;
    }
}
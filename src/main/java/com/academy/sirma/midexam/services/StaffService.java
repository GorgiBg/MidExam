package com.academy.sirma.midexam.services;

import com.academy.sirma.midexam.constants.StringConstants;
import com.academy.sirma.midexam.entities.Employee;
import com.academy.sirma.midexam.utils.MyObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class StaffService<T> implements Service<T> {
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private MyObjectMapper mapper;

    public StaffService(BufferedReader reader, BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;
        this.mapper = new MyObjectMapper();
    }

    @Override
    public List<T> readData(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            // If the file doesn't exist, return an empty list
            return new ArrayList<>();
        }

        // read list of items from json
        List<Employee> employees = this.mapper.readValue(file, new TypeReference<List<Employee>>() {
        });
        return (List<T>) employees;
    }

    public void saveData(List<T> dataList) throws IOException {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(
            Paths.get(StringConstants.JSON_FILE_PATH),
            StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)) {

            String jsonArray = mapper.writeValueAsString(dataList);
            bufferedWriter.write(jsonArray);
        }
    }
}
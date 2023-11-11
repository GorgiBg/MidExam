package com.academy.sirma.midexam.services;

import com.academy.sirma.midexam.constants.StringConstants;
import com.academy.sirma.midexam.entities.Employee;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StaffService implements Service {
    private final BufferedReader reader;
    private final BufferedWriter writer;
    private ObjectMapper mapper;

    public StaffService(BufferedReader reader, BufferedWriter writer) {
        this.reader = reader;
        this.writer = writer;
        mapper = new ObjectMapper();
    }

    @Override
    public Map<Integer, Employee> readData(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            return Collections.emptyMap();
        }

        // read list of items from json
        List<Employee> employees = this.mapper.readValue(file, new TypeReference<>() {});

        // Convert the list to a Map using employee ID as the key
        return employees.stream()
            .collect(Collectors.toMap(Employee::getId, Function.identity()));
    }


    public void saveData(List<Employee> dataList) {
        try {
            // Use try-with-resources to automatically close the BufferedWriter
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(
                Paths.get(StringConstants.JSON_FILE_PATH),
                StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)) {

                String jsonArray = mapper.writeValueAsString(dataList);
                bufferedWriter.write(jsonArray);
            }
        } catch (IOException e) {
            System.err.println("Error saving data to JSON file: " + e.getMessage());
        }
    }
}
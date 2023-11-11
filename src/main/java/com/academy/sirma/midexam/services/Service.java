package com.academy.sirma.midexam.services;

import java.io.IOException;
import java.util.List;

public interface Service<T> {
    List<T> readData(String path) throws IOException;

    void saveData(List<T> employees) throws IOException;
}

package com.nesterov.tasksexecutor.worker.utils;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class StreamUtilsTest {
    @Test
    void getStringFromStreamTest() throws IOException {
        String string = "Привет";
        InputStream inputStream = new ByteArrayInputStream(string.getBytes("cp866"));

        assertEquals(string, StreamUtils.getStringFromStream(inputStream));
    }
}
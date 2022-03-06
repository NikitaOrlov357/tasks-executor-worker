package com.nesterov.tasksexecutor.worker.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamUtils {
    public static String getStringFromStream(InputStream stream) throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))){
            String line;
            while ((line = bufferedReader.readLine()) != null){
                output.append(line).append("\n");
            }
        }
        return output.toString();
    }
}
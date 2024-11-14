package com.myq.test.cleaningrobot.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.myq.test.cleaningrobot.model.input.Input;
import com.myq.test.cleaningrobot.model.result.Result;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonFileUtil {

    public static Input getInputJsonFile(String filePath) {
        String inputFileString;
        try {
            inputFileString = Files.readString(Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException("Read input file failed with exception: " + e);
        }
        Gson gson = new Gson();
        JsonObject sourceJson = JsonParser.parseString(inputFileString).getAsJsonObject();
        return gson.fromJson(sourceJson, Input.class);
    }

    public static void writeResultToJsonFile(String filePath, Result result) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(gson.toJson(result));
        } catch (IOException e) {
            throw new RuntimeException("Write to result file failed with exception: " + e);
        }
    }
}

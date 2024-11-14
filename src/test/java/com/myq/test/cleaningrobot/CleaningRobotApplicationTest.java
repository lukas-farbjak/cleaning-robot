package com.myq.test.cleaningrobot;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class CleaningRobotApplicationTest {

    private static final String RESULT_TEST_JSON = "result-test.json";

    @BeforeEach
    void beforeEach() throws IOException {
        // Cleanup test result file before test
        Path resultPath = Paths.get(RESULT_TEST_JSON);
        Files.deleteIfExists(resultPath);
    }

    @AfterEach
    void afterEach() throws IOException {
        // Cleanup test result file after test
        Path resultPath = Paths.get(RESULT_TEST_JSON);
        Files.deleteIfExists(resultPath);
    }

    @Test
    void mainTest1() throws IOException {
        CleaningRobotApplication.main(new String[]{"test1.json", RESULT_TEST_JSON});

        JsonObject resultJson = getJsonFromFile(RESULT_TEST_JSON);
        JsonObject expectedJson = getJsonFromFile("test1_result.json");
        assertNotNull(resultJson);
        assertEquals(expectedJson, resultJson);
    }

    @Test
    void mainTest2() throws IOException {
        CleaningRobotApplication.main(new String[]{"test2.json", RESULT_TEST_JSON});

        JsonObject resultJson = getJsonFromFile(RESULT_TEST_JSON);
        JsonObject expectedJson = getJsonFromFile("test2_result.json");
        assertNotNull(resultJson);
        assertEquals(expectedJson, resultJson);
    }

    @Test
    void mainMissingArguments() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                CleaningRobotApplication.main(new String[0]));

        assertEquals("Required program arguments are missing. Run program with two arguments: <source.json> <result.json>", exception.getMessage());
    }

    @Test
    void mainNotExistingInputFile() {
        Exception exception = assertThrows(RuntimeException.class, () ->
                CleaningRobotApplication.main(new String[]{"notExistingFile.json", "result.json"}));

        assertEquals("Read input file failed with exception: java.nio.file.NoSuchFileException: notExistingFile.json", exception.getMessage());
    }

    private JsonObject getJsonFromFile(String path) throws IOException {
        String resultFileString = Files.readString(Paths.get(path));
        return JsonParser.parseString(resultFileString).getAsJsonObject();
    }

}
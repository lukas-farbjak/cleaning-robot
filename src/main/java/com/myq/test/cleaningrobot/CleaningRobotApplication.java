package com.myq.test.cleaningrobot;

import com.myq.test.cleaningrobot.model.input.Input;
import com.myq.test.cleaningrobot.model.result.Result;
import com.myq.test.cleaningrobot.util.JsonFileUtil;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CleaningRobotApplication {

    private static final Logger logger = Logger.getLogger(CleaningRobotApplication.class.getName());

    public static void main(String[] args) {
        logger.log(Level.INFO, "Program CleaningRobot started with args: " + Arrays.asList(args));
        if (args.length < 2) {
            throw new IllegalArgumentException("Required program arguments are missing. Run program with two arguments: <source.json> <result.json>");
        }

        String inputPath = args[0];
        Input input = JsonFileUtil.getInputJsonFile(inputPath);

        CleaningRobot robot = new CleaningRobot(input);
        Result result = robot.run();

        String resultPath = args[1];
        JsonFileUtil.writeResultToJsonFile(resultPath, result);
        logger.log(Level.INFO, "CleaningRobot successfully completed.");
    }
}
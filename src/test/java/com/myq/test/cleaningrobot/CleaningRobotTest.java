package com.myq.test.cleaningrobot;

import com.myq.test.cleaningrobot.model.FacingEnum;
import com.myq.test.cleaningrobot.model.input.Input;
import com.myq.test.cleaningrobot.model.input.Start;
import com.myq.test.cleaningrobot.model.result.Cleaned;
import com.myq.test.cleaningrobot.model.result.Final;
import com.myq.test.cleaningrobot.model.result.Result;
import com.myq.test.cleaningrobot.model.result.Visited;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CleaningRobotTest {

    @Test
    void testRunSuccess() {
        Input input = createTestInput();
        CleaningRobot robot = new CleaningRobot(input);

        Result result = robot.run();

        List<Visited> visitedPositions = result.getVisited();
        assertTrue(visitedPositions.contains(new Visited(0, 0)));
        assertTrue(visitedPositions.contains(new Visited(1, 1)));
        assertTrue(visitedPositions.contains(new Visited(1, 0)));

        List<Cleaned> cleanedPositions = result.getCleaned();
        assertTrue(cleanedPositions.contains(new Cleaned(1, 0))); // Initial clean command position

        Final finalPosition = result.getFinal();
        assertEquals(0, finalPosition.getY());
        assertEquals(1, finalPosition.getX());
        assertEquals(FacingEnum.WEST.getValue(), finalPosition.getFacing());
        assertEquals(37, result.getBattery()); // Initial battery 20, with commands costs reducing it
    }

    @Test
    void testOutOfBattery() {
        Input input = createTestInput();
        input.setBattery(1);
        CleaningRobot robot = new CleaningRobot(input);

        Result result = robot.run();

        assertEquals(1, result.getBattery());
        assertEquals(1, result.getVisited().size());
        assertEquals(1, result.getVisited().size());
    }

    @Test
    void testInvalidStartingPosition() {
        Input input = createTestInput();
        input.getStart().setX(-10);
        CleaningRobot robot = new CleaningRobot(input);

        // Expect exception due to invalid start position
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, robot::run);

        assertEquals("Invalid start position in input", exception.getMessage());
    }

    @Test
    void testInvalidCommand() {
        Input input = createTestInput();
        input.setCommands(List.of("INVALID"));
        CleaningRobot robot = new CleaningRobot(input);

        // Expect exception due to invalid command
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, robot::run);

        assertEquals("Invalid command: INVALID", exception.getMessage());
    }

    @Test
    void testBackOffStrategyActivation() {
        Input input = createTestInput();
        input.setCommands(List.of("A", "C", "A"));
        CleaningRobot robot = new CleaningRobot(input);

        Result result = robot.run();

        assertEquals(2,result.getVisited().size());
        assertEquals(1, result.getCleaned().size());

        Final finalPosition = result.getFinal();
        assertEquals(1, finalPosition.getY());
        assertEquals(1, finalPosition.getX());
        assertEquals(FacingEnum.WEST.getValue(), finalPosition.getFacing());
        assertEquals(34, result.getBattery());
    }

    @Test
    void testRobotStuck() {
        Input input = createTestInput();
        input.setStart(new Start(3,3,FacingEnum.NORTH.getValue()));
        CleaningRobot robot = new CleaningRobot(input);

        Result result = robot.run();

        assertEquals(1,result.getVisited().size());
        assertEquals(0, result.getCleaned().size());

        Final finalPosition = result.getFinal();
        assertEquals(3, finalPosition.getY());
        assertEquals(3, finalPosition.getX());
        assertEquals(FacingEnum.SOUTH.getValue(), finalPosition.getFacing());
        assertEquals(31, result.getBattery());
    }

    private Input createTestInput() {
        Input input = new Input();
        input.setBattery(50);
        input.setCommands(List.of("A", "C", "TL", "A", "B"));
        input.setStart(new Start(1,1,FacingEnum.NORTH.getValue()));
        input.setMap(List.of(
                List.of("S", "S", "null", "C"),
                List.of("S", "S", "S", "C"),
                List.of("S", "S", "S", "C"),
                List.of("S", "S", "C", "S")
        ));
        return input;
    }

}
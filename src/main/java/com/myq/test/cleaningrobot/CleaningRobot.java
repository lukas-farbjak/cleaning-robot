package com.myq.test.cleaningrobot;

import com.myq.test.cleaningrobot.model.FacingEnum;
import com.myq.test.cleaningrobot.model.Position;
import com.myq.test.cleaningrobot.model.input.Input;
import com.myq.test.cleaningrobot.model.result.Cleaned;
import com.myq.test.cleaningrobot.model.result.Final;
import com.myq.test.cleaningrobot.model.result.Result;
import com.myq.test.cleaningrobot.model.result.Visited;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CleaningRobot {
    private final Set<Position> visitedPositions = new HashSet<>();
    private final Set<Position> cleanedPositions = new HashSet<>();
    private final List<String> commands;
    private final List<List<String>> map;
    private int x, y, battery, backOffStrategyLevel;
    private FacingEnum facing;
    private boolean dropRestSequence, programFinished;

    public CleaningRobot(Input input) {
        this.battery = input.getBattery();
        this.commands = input.getCommands();
        if (input.getStart() != null) {
            this.x = input.getStart().getX();
            this.y = input.getStart().getY();
            this.facing = FacingEnum.getByValue(input.getStart().getFacing());
        }
        this.map = input.getMap();
    }

    public Result run() {
        if (facing == null || !isPositionValid(x, y)) {
            throw new IllegalArgumentException("Invalid start position in input");
        }
        visitNewPosition(x, y);

        for (String command : commands) {
            if (programFinished) {
                break;
            }
            switch (command) {
                case "A" -> advance();
                case "B" -> back();
                case "C" -> clean();
                case "TL" -> turnLeft();
                case "TR" -> turnRight();
                default -> throw new IllegalArgumentException("Invalid command: " + command);
            }
        }
        return getResult();
    }

    private void turnLeft() {
        if (isOutOfBattery(1)) {
            return;
        }
        battery -= 1;
        turnByDegree(-90);
    }

    private void turnByDegree(int degree) {
        int actualDegree = facing.getDegree();
        if (degree < 0) {
            degree = 360 + (degree % 360);
        }
        int newDegree = (actualDegree + degree) % 360;
        facing = FacingEnum.getByDegree(newDegree);
    }

    private void turnRight() {
        if (isOutOfBattery(1)) {
            return;
        }
        battery -= 1;
        turnByDegree(90);
    }

    private void advance() {
        if (isOutOfBattery(2)) {
            return;
        }
        battery -= 2;
        move(1);
    }

    private void move(int newPosition) {
        int newX = x;
        int newY = y;
        switch (facing) {
            case NORTH -> newY = newY - newPosition;
            case SOUTH -> newY = newY + newPosition;
            case EAST -> newX = newX + newPosition;
            case WEST -> newX = newX - newPosition;
        }

        if (isPositionValid(newX, newY)) {
            visitNewPosition(newX, newY);
        } else {
            backOffStrategy();
        }
    }

    private void back() {
        if (isOutOfBattery(3)) {
            return;
        }
        battery -= 3;
        move(-1);
    }

    private void clean() {
        if (isOutOfBattery(5)) {
            return;
        }
        battery -= 5;
        if (map.get(y).get(x) != null && map.get(y).get(x).equals("S")) {
            cleanedPositions.add(new Position(x, y));
        }
    }

    private boolean isOutOfBattery(int batteryConsume) {
        if ((battery - batteryConsume) < 0) {
            programFinished = true;
            return true;
        }
        return false;
    }

    private void visitNewPosition(int newX, int newY) {
        backOffStrategyLevel = 0;
        dropRestSequence = false;
        x = newX;
        y = newY;
        visitedPositions.add(new Position(x, y));
    }

    private boolean isPositionValid(int newX, int newY) {
        return newY >= 0 && newY < map.size() && newX >= 0 && newX < map.get(0).size() && !map.get(newY).get(newX).equals("null") && !map.get(newY).get(newX).equals("C");
    }

    private void backOffStrategy() {
        backOffStrategyLevel++;
        dropRestSequence = true;
        switch (backOffStrategyLevel) {
            case 1 -> {
                turnRight();
                advance();
                if (!dropRestSequence) {
                    turnLeft();
                }
            }
            case 2, 3 -> {
                turnRight();
                advance();
                if (!dropRestSequence) {
                    turnRight();
                }
            }
            case 4 -> {
                turnRight();
                back();
                if (!dropRestSequence) {
                    turnRight();
                    advance();
                }
            }
            case 5 -> {
                turnLeft();
                turnLeft();
                advance();
            }
            default -> programFinished = true;
        }
    }

    private Result getResult() {
        Result result = new Result();

        List<Visited> visited = visitedPositions.stream()
                .map(visitedPosition -> new Visited(visitedPosition.x(), visitedPosition.y()))
                .sorted(Comparator.comparingInt(Visited::getX)
                        .thenComparingInt(Visited::getY))
                .collect(Collectors.toList());
        result.setVisited(visited);

        List<Cleaned> cleaned = cleanedPositions.stream()
                .map(visitedPosition -> new Cleaned(visitedPosition.x(), visitedPosition.y()))
                .sorted(Comparator.comparingInt(Cleaned::getX)
                        .thenComparingInt(Cleaned::getY))
                .collect(Collectors.toList());
        result.setCleaned(cleaned);

        result.setFinal(new Final(x, y, facing.getValue()));
        result.setBattery(battery);
        return result;
    }

}
package com.myq.test.cleaningrobot.model;

public enum FacingEnum {
    NORTH(0, "N"),
    EAST(90, "E"),
    SOUTH(180, "S"),
    WEST(270, "W");

    private final int degree;
    private final String value;

    FacingEnum(int degree, String value) {
        this.degree = degree;
        this.value = value;
    }

    public int getDegree() {
        return degree;
    }

    public String getValue() {
        return value;
    }

    public static FacingEnum getByDegree(int degree) {
        for (FacingEnum FacingEnum : values()) {
            if (FacingEnum.degree == degree) {
                return FacingEnum;
            }
        }
        return NORTH;
    }

    public static FacingEnum getByValue(String facing) {
        for (FacingEnum FacingEnum : values()) {
            if (FacingEnum.value.equalsIgnoreCase(facing)) {
                return FacingEnum;
            }
        }
        throw new IllegalArgumentException("Invalid facing value " + facing);
    }
}
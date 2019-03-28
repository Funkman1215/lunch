package com.funkman.lunch.resultEnum;

public enum LevelEnum {

    ONE(1),
    TWO(2),
    THREE(3),
    FORE(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10);

    private Integer number;

    LevelEnum(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }
}

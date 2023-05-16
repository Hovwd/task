package com.podcastle.task.type;

import java.util.Random;

public enum Color {
    RED("red"),
    BLUE("blue"),
    BLACK_AND_WHIT("black_and_white");

    private final String color;

    private Color(String color){
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    private Color randomColor() {
        int pick = new Random().nextInt(Color.values().length);
        return Color.values()[pick];
    }
}

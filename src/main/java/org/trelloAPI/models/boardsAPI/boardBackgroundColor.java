package org.trelloAPI.models.boardsAPI;

public enum boardBackgroundColor {
    BLUE("blue"),
    ORANGE("orange"),
    GREEN("green"),
    RED("red"),
    PURPLE("purple");
    private final String color;
    boardBackgroundColor(String color) {
        this.color = color;
    }
    @Override
    public String toString(){
        return color;
    }
}

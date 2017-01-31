package com.gaidelfanclub.codenames.card;

import android.graphics.Color;

public enum WordType {
    NEUTRAL(Color.WHITE, Color.BLACK), KILLER(Color.BLACK, Color.WHITE), RED(Color.RED, Color.BLACK), BLUE(Color.BLUE, Color.BLACK);

    private int color;
    private int colorOfText;

    WordType(int color, int colorOfText) { this.color = color; this.colorOfText = colorOfText; }

    public int getColor() {
        return color;
    }
    public int getTextColor() { return colorOfText; }
}

package com.gaidelfanclub.codenames.card;

import android.graphics.Color;

public enum WordType {
    NEUTRAL(Color.WHITE), KILLER(Color.BLACK), RED(Color.RED), BLUE(Color.BLUE);

    private int color;

    WordType(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}

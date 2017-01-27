package com.gaidelfanclub.codenames.card;

public class Word {

    private WordType type;
    private String word;
    private boolean opened;

    public Word(WordType type, String word, boolean opened) {
        this.type = type;
        this.word = word;
        this.opened = opened;
    }

    public WordType getType() {
        return type;
    }

    public void setType(WordType type) {
        this.type = type;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }
}

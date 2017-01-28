package com.gaidelfanclub.codenames.card;

import android.os.Parcel;
import android.os.Parcelable;

public class Word implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type.name());
        dest.writeString(word);
        dest.writeByte((byte) (opened ? 1 : 0));
    }

    private static Word parse(Parcel src) {
        WordType type = WordType.valueOf(src.readString());
        String name = src.readString();
        boolean opened = src.readByte() != 0;
        return new Word(type, name, opened);
    }

    public static final Parcelable.Creator<Word> CREATOR = new Parcelable.Creator<Word>() {
        public Word createFromParcel(Parcel in) {
            return parse(in);
        }

        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

}

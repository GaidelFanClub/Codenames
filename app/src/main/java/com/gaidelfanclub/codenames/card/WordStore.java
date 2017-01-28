package com.gaidelfanclub.codenames.card;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;
import java.util.zip.ZipInputStream;

/**
 * Created by Artem on 28.01.2017.
 */

public class WordStore {

    private static final int COUNT_OF_WORD = 25;


    private ArrayList<String> data;
    Context context;

    public WordStore(Context context){

        try {
            this.context = context;
            InputStream stream = context.getAssets().open("wrd.zip");
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(stream));
            zipInputStream.getNextEntry();
            BufferedReader reader = new BufferedReader(new InputStreamReader(zipInputStream));
            data = new ArrayList<>();
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                data.add(line);
            }

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public ArrayList<String> getData(){return data;}

    public String[] getUsingWords(Random random){
        TreeSet<Integer> used = new TreeSet<>();
        String[] words = new String[COUNT_OF_WORD];
        for (int i = 0; i < COUNT_OF_WORD; i++) {
            int value = 0;
            while (true) {
                value = random.nextInt(data.size());
                if (used.add(value)) break;
            }
            words[i] = data.get(value);
        }
        return words;
    }
}

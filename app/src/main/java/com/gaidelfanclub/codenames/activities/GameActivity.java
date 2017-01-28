package com.gaidelfanclub.codenames.activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gaidelfanclub.codenames.BaseActivity;
import com.gaidelfanclub.codenames.R;
import com.gaidelfanclub.codenames.card.Word;
import com.gaidelfanclub.codenames.card.WordStore;
import com.gaidelfanclub.codenames.card.WordType;
import com.gaidelfanclub.codenames.card.WordsAdapter;
import com.gaidelfanclub.codenames.utils.GenUtils;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;

public class GameActivity extends BaseActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_game);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        initRecycler(recyclerView);
    }

    private void initRecycler(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        Word[] words = new Word[25];
        Random rnd = new Random(1);
        String[] store = new WordStore(this).getUsingWords(rnd);
        WordType[] types = GenUtils.generateTypes(rnd);
        for (int i = 0; i < words.length; i++) {
            words[i] = new Word(types[i], store[i], false);
        }
        WordsAdapter adapter = new WordsAdapter(words);
        recyclerView.setAdapter(adapter);
    }



}

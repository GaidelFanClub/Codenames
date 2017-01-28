package com.gaidelfanclub.codenames.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.gaidelfanclub.codenames.BaseActivity;
import com.gaidelfanclub.codenames.R;
import com.gaidelfanclub.codenames.card.Word;
import com.gaidelfanclub.codenames.card.WordStore;
import com.gaidelfanclub.codenames.card.WordType;
import com.gaidelfanclub.codenames.card.WordsAdapter;
import com.gaidelfanclub.codenames.utils.GenUtils;
import com.gaidelfanclub.codenames.utils.KeywordUtils;
import com.gaidelfanclub.codenames.utils.KeywordsStore;

import java.util.Random;

public class GameActivity extends BaseActivity {

    private static final String EXTRA_LEADER = "EXTRA_LEADER";
    private static final String EXTRA_KEYWORD = "EXTRA_KEYWORD";

    private static final String TAG_WORDS = "TAG_WORDS";

    public static Intent createIntent(Context context, String keyword, boolean isLeader) {
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra(EXTRA_LEADER, isLeader);
        intent.putExtra(EXTRA_KEYWORD, keyword);
        return intent;
    }

    private RecyclerView recyclerView;
    private TextView keyView;


    private String keyword;
    private boolean isLeader;
    private Word[] words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_game);
        isLeader = getIntent().getBooleanExtra(EXTRA_LEADER, true);
        keyword = getIntent().getStringExtra(EXTRA_KEYWORD);
        if (savedInstanceState == null) {
            words = createWords();
        } else {
            words = (Word[]) savedInstanceState.getParcelableArray(TAG_WORDS);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        keyView = (TextView) findViewById(R.id.words);
        initRecycler(recyclerView);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArray(TAG_WORDS, words);
    }

    private void initRecycler(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        WordsAdapter adapter = new WordsAdapter(isLeader);
        adapter.setWords(words);

        if (isLeader) {
            keyView.setText("Слово ведущего: " + keyword + "\n Слово игроков: " + KeywordsStore.getInstance().getWord(getSeed()));
        }
        recyclerView.setAdapter(adapter);
    }

    private Word[] createWords() {
        Word[] words = new Word[25];
        Random rnd = new Random(getSeed());
        String[] store = new WordStore(this).getUsingWords(rnd);
        WordType[] types = GenUtils.generateTypes(rnd);
        for (int i = 0; i < words.length; i++) {
            words[i] = new Word(types[i], store[i], false);
        }
        return words;
    }

    private int getSeed() {
        String gameKey;
        if (isLeader) {
            gameKey = KeywordUtils.getParticipantKeyword(keyword);
        } else {
            gameKey = keyword;

        }
        return KeywordUtils.convertParticipantKeywordToSeed(gameKey);
    }


}

package com.gaidelfanclub.codenames.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;

import com.gaidelfanclub.codenames.BaseActivity;
import com.gaidelfanclub.codenames.R;
import com.gaidelfanclub.codenames.card.Word;
import com.gaidelfanclub.codenames.card.WordHolder;
import com.gaidelfanclub.codenames.card.WordStore;
import com.gaidelfanclub.codenames.card.WordType;
import com.gaidelfanclub.codenames.utils.GenUtils;
import com.gaidelfanclub.codenames.utils.KeywordUtils;

import java.util.Random;

public class GameActivity extends BaseActivity {

    private static final int DIMEN = 5;

    private static final String EXTRA_LEADER = "EXTRA_LEADER";
    private static final String EXTRA_KEYWORD = "EXTRA_KEYWORD";

    private static final String TAG_WORDS = "TAG_WORDS";

    public static Intent createIntent(Context context, String keyword, boolean isLeader) {
        Intent intent = new Intent(context, GameActivity.class);
        intent.putExtra(EXTRA_LEADER, isLeader);
        intent.putExtra(EXTRA_KEYWORD, keyword);
        return intent;
    }

    private GridLayout gridLayout;

    private String keyword;
    private boolean isLeader;
    private Word[] words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_game);
        gridLayout = (GridLayout) findViewById(R.id.container);
        isLeader = getIntent().getBooleanExtra(EXTRA_LEADER, true);
        keyword = getIntent().getStringExtra(EXTRA_KEYWORD);
        if (savedInstanceState == null) {
            words = createWords();
        } else {
            words = (Word[]) savedInstanceState.getParcelableArray(TAG_WORDS);
        }
        initGrid();

        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        if (isLeader) {
            builder.setMessage("Скажите это слово участникам: \n" + KeywordUtils.getParticipantKeyword(keyword));
        } else {
            builder.setMessage("Первой ходит команда: " + (!firstTeam(words)? "КРАСНЫЕ": "СИНИЕ"));
        }
        builder.setTitle(isLeader ? "Слово-идентификатор" : "Первый ход")
                .setCancelable(false)
                .setNegativeButton("ОК",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void initGrid() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        size.y -= getStatusBarHeight();

        int[] horizontalSpacing = getSpacing(size.x, DIMEN);
        int[] verticalSpacing = getSpacing(size.y, DIMEN);

        for (int i = 0; i < DIMEN; i++) {
            for (int j = 0; j < DIMEN; j++) {
                View view = LayoutInflater.from(this).inflate(R.layout.i_word, null);
                WordHolder holder = new WordHolder(view, isLeader);
                holder.bind(words[i * DIMEN + j]);
                GridLayout.LayoutParams param = new GridLayout.LayoutParams();
                param.height = verticalSpacing[i];
                param.width = horizontalSpacing[j];
                param.setGravity(Gravity.CENTER);
                param.columnSpec = GridLayout.spec(j);
                param.rowSpec = GridLayout.spec(i);
                gridLayout.addView(view, param);
            }
        }
    }

    private int[] getSpacing(int available, int count) {
        int alreadyUsed = 0;
        int[] spacing = new int[count];
        for (int i = 0; i < count; i++) {
            spacing[i] = (available - alreadyUsed) / (count - i);
            alreadyUsed += spacing[i];
        }
        return spacing;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArray(TAG_WORDS, words);
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

    private boolean firstTeam(Word[] a) {
        int blue = 0;
        int red = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i].getType() == WordType.RED) {
                red++;
            }
            if (a[i].getType() == WordType.BLUE){
                blue++;
            }
        }
        return (blue > red) ? true : false;
    }


}

package com.gaidelfanclub.codenames.card;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gaidelfanclub.codenames.R;

public class WordsAdapter extends RecyclerView.Adapter<WordHolder> {

    private Word[] words;
    private boolean isLeader;

    public WordsAdapter(boolean isLeader) {
        this.isLeader = isLeader;
    }

    public void setWords(Word[] words) {
        this.words = words;
        notifyDataSetChanged();
    }

    @Override
    public WordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.i_word, parent, false);
        return new WordHolder(view, isLeader);
    }

    @Override
    public void onBindViewHolder(WordHolder holder, int position) {
        holder.bind(words[position]);
    }

    @Override
    public int getItemCount() {
        return words.length;
    }
}

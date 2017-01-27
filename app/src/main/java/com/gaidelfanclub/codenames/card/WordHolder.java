package com.gaidelfanclub.codenames.card;

import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gaidelfanclub.codenames.R;

public class WordHolder extends RecyclerView.ViewHolder {

    private View view;
    private View innerContainer;
    private TextView word;
    private View shroud;

    private Word data;

    public WordHolder(View itemView) {
        super(itemView);
        word = (TextView) itemView.findViewById(R.id.word);
        innerContainer = itemView.findViewById(R.id.inner_container);
        view = itemView;
        shroud = itemView.findViewById(R.id.shroud);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shroud.setVisibility(View.GONE);
                data.setOpened(true);
            }
        });
    }

    public void bind(Word data) {
        this.data = data;
        word.setText(data.getWord());
        innerContainer.setBackgroundColor(data.getType().getColor());
        if (data.isOpened()) {
            shroud.setVisibility(View.GONE);
        } else {
            shroud.setVisibility(View.VISIBLE);
        }
    }
}

package com.gaidelfanclub.codenames.card;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gaidelfanclub.codenames.R;

public class WordHolder extends RecyclerView.ViewHolder {

    private View innerContainer;
    private TextView word;
    private int defaultColor;

    private Word data;
    private boolean isLeader;

    public WordHolder(final View itemView, final boolean isLeader) {
        super(itemView);
        this.isLeader = isLeader;

        word = (TextView) itemView.findViewById(R.id.word);
        innerContainer = itemView.findViewById(R.id.inner_container);
        defaultColor = itemView.getContext().getResources().getColor(R.color.word_gray);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.setOpened(true);
                bind(data);
            }
        });
    }

    public void bind(Word data) {
        this.data = data;
        word.setText(data.getWord());
        if (isLeader) {
            innerContainer.setBackgroundColor(data.getType().getColor());
            innerContainer.setAlpha(data.isOpened() ? 0.5f : 1f);
        } else {
            if (data.isOpened()) {
                innerContainer.setBackgroundColor(data.getType().getColor());
            } else {
                innerContainer.setBackgroundColor(defaultColor);
            }
        }
    }
}

package com.gaidelfanclub.codenames.card;

import android.animation.ObjectAnimator;
import android.support.annotation.ColorRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.gaidelfanclub.codenames.R;
import com.gaidelfanclub.codenames.activities.StartActivity;

public class WordHolder extends RecyclerView.ViewHolder {

    private View view;
    private View innerContainer;
    private TextView word;
    private View shroud;

    private Word data;

    public WordHolder(final View itemView) {
        super(itemView);

        word = (TextView) itemView.findViewById(R.id.word);
        innerContainer = itemView.findViewById(R.id.inner_container);
        view = itemView;


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                innerContainer.setBackgroundColor(data.getType().getColor());
                data.setOpened(true);
                if(!StartActivity.flag){
                    word.setText("");
                }
            }
        });
    }

    public void bind(Word data) {
        this.data = data;
        word.setText(data.getWord());
        if (data.isOpened()) {
            innerContainer.setBackgroundColor(data.getType().getColor());
            if(!StartActivity.flag){
                word.setText("");
            }
        }else{
            if(StartActivity.flag) {
                innerContainer.setBackgroundColor(data.getType().getColor());
                innerContainer.getBackground().setAlpha(50);
            }
        }
    }
}

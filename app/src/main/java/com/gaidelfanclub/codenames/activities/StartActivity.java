package com.gaidelfanclub.codenames.activities;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gaidelfanclub.codenames.BaseActivity;
import com.gaidelfanclub.codenames.R;

public class StartActivity extends BaseActivity {

    private interface OnKeywordEnteredListener {
        void onKeywordEntered(String keyword);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_start);
        findViewById(R.id.leader).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameDialog(R.string.start_dialog_leader_title, new OnKeywordEnteredListener() {
                    @Override
                    public void onKeywordEntered(String keyword) {
                        if (TextUtils.isEmpty(keyword)) {
                            Toast.makeText(StartActivity.this, R.string.start_dialog_empty_keyword_error, Toast.LENGTH_SHORT).show();
                        } else {
                            startGameAsLeader(keyword);
                        }
                    }
                });
            }
        });

        findViewById(R.id.participant).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameDialog(R.string.start_dialog_participant_title, new OnKeywordEnteredListener() {
                    @Override
                    public void onKeywordEntered(String keyword) {
                        if (TextUtils.isEmpty(keyword)) {
                            Toast.makeText(StartActivity.this, R.string.start_dialog_empty_keyword_error, Toast.LENGTH_SHORT).show();
                        } else {
                            startGameAsParticipant(keyword);
                        }
                    }
                });
            }
        });
    }

    private void startGameAsLeader(String keyword) {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    private void startGameAsParticipant(String keyword) {

    }

    private void startGameDialog(int titleId, final OnKeywordEnteredListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(StartActivity.this);
        builder.setTitle(getString(titleId));

        final EditText input = new EditText(StartActivity.this);
        builder.setView(input);

        builder.setPositiveButton(R.string.start_dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listener.onKeywordEntered(input.getText().toString());
            }
        });
        builder.setNegativeButton(R.string.start_dialog_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


}

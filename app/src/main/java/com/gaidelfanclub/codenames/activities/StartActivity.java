package com.gaidelfanclub.codenames.activities;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gaidelfanclub.codenames.Analytics;
import com.gaidelfanclub.codenames.BaseActivity;
import com.gaidelfanclub.codenames.BuildConfig;
import com.gaidelfanclub.codenames.R;

import java.util.Locale;

public class StartActivity extends BaseActivity {

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
                            startGame(keyword, true);
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
                            startGame(keyword, false);
                        }
                    }
                });
            }
        });

        findViewById(R.id.rate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appPackageName = getPackageName();
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });

        findViewById(R.id.rules).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://tesera.ru/images/items/775882/Codenames%20GaGa%20Games.pdf")));
            }
        });
        findViewById(R.id.share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://play.google.com/store/apps/details?id=" + getPackageName();
                String shareMessage = getString(R.string.share_message, url);
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.share_title)));
            }
        });
        ((TextView)findViewById(R.id.version)).setText(String.format(Locale.US, "v%s.%d", BuildConfig.VERSION_NAME, BuildConfig.VERSION_CODE));
    }

    private void startGame(String keyword, boolean asLeader) {
        startActivity(GameActivity.createIntent(this, keyword, asLeader));
    }

    private void startGameAsParticipant(String keyword) {

    }

    private void startGameDialog(int titleId, final OnKeywordEnteredListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(StartActivity.this);
        builder.setTitle(getString(titleId));

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.v_start_dialog, null);
        builder.setView(view);
        final EditText input = (EditText) view.findViewById(R.id.text_field);

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

    private interface OnKeywordEnteredListener {
        void onKeywordEntered(String keyword);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Analytics.getInstance().sendEvent("StartScreen onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Analytics.getInstance().sendEvent("StartScreen onPause");
    }
}

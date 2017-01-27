package com.gaidelfanclub.codenames;


import android.app.Application;

import com.gaidelfanclub.codenames.utils.KeywordsStore;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        KeywordsStore.getInstance().init(this);
    }
}

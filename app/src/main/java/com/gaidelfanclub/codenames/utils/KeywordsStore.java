package com.gaidelfanclub.codenames.utils;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.zip.ZipInputStream;

public class KeywordsStore {

    private static KeywordsStore instance = new KeywordsStore();

    public static KeywordsStore getInstance() {
        return instance;
    }

    private String[] data;
    private CountDownLatch latch = new CountDownLatch(1);

    public void init(final Context context) {
        new Thread() {
            @Override
            public void run() {
                innerInit(context);
                latch.countDown();
            }
        }.start();
    }

    private void awaitInit() {
        try {
            latch.await();
        } catch (InterruptedException ignored) {
        }
    }

    public int getSeedByPublicKey(String keyword) {
        awaitInit();
        return Arrays.binarySearch(data, keyword);
    }

    public String getParticipantKeyword(String keyword) {
        awaitInit();
        return data[new Random(keyword.hashCode()).nextInt(data.length)];
    }

    public String getWord(int index) {
        awaitInit();
        return data[index];
    }

    public int findLowerPosition(String keyword) {
        awaitInit();
        int left = 0;
        int right = data.length - 1;
        int result = data.length - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (data[mid].compareTo(keyword) <= 0) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    private void innerInit(Context context) {
        try {
            InputStream stream = context.getAssets().open("dic.zip");
            ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(stream));
            zipInputStream.getNextEntry();
            BufferedReader reader = new BufferedReader(new InputStreamReader(zipInputStream));
            List<String> list = new ArrayList<>();
            while (true) {
                String line = reader.readLine();
                if (line == null) break;
                list.add(line);
            }
            data = list.toArray(new String[list.size()]);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


}

package com.gaidelfanclub.codenames.utils;

import com.gaidelfanclub.codenames.card.WordType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GenUtils {

    public static WordType[] generateTypes(Random random) {
        Set<Integer> set = new HashSet<>();
        WordType[] types = new WordType[25];
        Arrays.fill(types, WordType.NEUTRAL);
        int killerCount = 1;
        int redCount = 8 + random.nextInt(2);
        int blueCount = 8 + 9 - redCount;
        for (int i = 0; i < killerCount; i++) {
            types[generate(set, random, 25)] = WordType.KILLER;
        }
        for (int i = 0; i < redCount; i++) {
            types[generate(set, random, 25)] = WordType.RED;
        }
        for (int i = 0; i < blueCount; i++) {
            types[generate(set, random, 25)] = WordType.BLUE;
        }
        return types;
    }

    private static int generate(Set<Integer> set, Random random, int max) {
        while (true) {
            int value = random.nextInt(max);
            if (set.add(value)) return value;
        }
    }

}

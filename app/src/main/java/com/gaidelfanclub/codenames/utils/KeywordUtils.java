package com.gaidelfanclub.codenames.utils;


public class KeywordUtils {

    public static String getParticipantKeyword(String leaderKeyword) {
        return KeywordsStore.getInstance().getParticipantKeyword(leaderKeyword);
    }

    public static int convertParticipantKeywordToSeed(String participantKeyword) {
        return KeywordsStore.getInstance().getSeedByPublicKey(participantKeyword);
    }

    public static int convertLeaderKeywordToSeed(String leaderKeyword) {
        return convertParticipantKeywordToSeed(getParticipantKeyword(leaderKeyword));
    }

}

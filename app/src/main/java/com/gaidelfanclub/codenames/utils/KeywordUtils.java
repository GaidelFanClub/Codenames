package com.gaidelfanclub.codenames.utils;


public class KeywordUtils {

    public static String getParticipantKeyword(String leaderKeyword) {
        return leaderKeyword;
    }

    public static int convertParticipantKeywordToSeed(String participantKeyword) {
        return participantKeyword.hashCode();
    }

    public static int convertLeaderKeywordToSeed(String leaderKeyword) {
        return convertParticipantKeywordToSeed(getParticipantKeyword(leaderKeyword));
    }

}

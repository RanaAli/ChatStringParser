package com.ranaali.andro.ChatStringParser.utils;

import org.json.JSONArray;


/**
 * Created by andro on 12/19/2016.
 */

public class MentionsExtractor {

    private static final String NON_WORD_REGEX = "[^\\w']+";
    private static final String MENTIONS_CHAR = "@";
    private static final String STRING_SPACE = " ";

    /**
     * The function checks for any mentions
     * in the text and returns JSONArray
     *
     * @param inputString text to be processed
     * @return JSONArray with identified items
     *
     */
    public static JSONArray checkForMentions(String inputString) {

        JSONArray mentions = new JSONArray();

        if (inputString.contains(MENTIONS_CHAR)) {
            String[] words = inputString.split(STRING_SPACE);

            for (String splitObject : words) {
                extractMentionFromWord(splitObject, mentions);
            }
        }

        return mentions;
    }

    private static void extractMentionFromWord(String word, JSONArray mentions) {

        if (word.contains(MENTIONS_CHAR)) {
            String[] wordSplits = word.split(MENTIONS_CHAR);

            for (String wordSplit : wordSplits) {

                if (wordSplit.contains(MENTIONS_CHAR)) {
                    extractMentionFromWord(wordSplit, mentions);
                } else {
                    String[] finalArray = wordSplit.split(NON_WORD_REGEX);
                    if (finalArray.length > 0 && !finalArray[0].isEmpty()) {
                        mentions.put(finalArray[0]);
                    }
                }
            }

        }

    }

}

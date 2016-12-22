package com.ranaali.andro.ChatStringParser.utils;

import org.json.JSONArray;

/**
 * Created by andro on 12/19/2016.
 */

public class EmoticonsExtractor {
    private static final String EMOTICONS_START = "(";
    private static final String EMOTICONS_END = ")";
    private static final String EMOTICONS_CLOSING_OPENING_BRACKET = ")(";
    private static final String STRING_SPACE = " ";
    private static final String REGEX_ALPHANUMERIC = "[a-zA-Z0-9]+";
    private static final String REPLACEMENT = ") (";

    /**
     * The function checks for any emoticons
     * in the text and returns JSONArray.
     *
     * @param inputString text to be processed
     * @return JSONArray with identified items
     *
     */
    public static JSONArray checkForEmoticons(String inputString) {
        JSONArray emoticons = new JSONArray();

        if (inputString.contains(EMOTICONS_START) && inputString.contains(EMOTICONS_END)) {
            String[] words = inputString.split(STRING_SPACE);

            for (String word : words) {

                if (word.contains(EMOTICONS_CLOSING_OPENING_BRACKET)) {
                    word = word.replace(EMOTICONS_CLOSING_OPENING_BRACKET, REPLACEMENT);

                    String[] wordFurtherSplits = word.split(STRING_SPACE);

                    for (String wordFurtherSplit : wordFurtherSplits) {
                        extractEmoticonString(emoticons, wordFurtherSplit);
                    }

                } else {
                    extractEmoticonString(emoticons, word);
                }
            }
        }

        return emoticons;
    }

    private static void extractEmoticonString(JSONArray emoticons, String word) {
        if (word.contains(EMOTICONS_START)
                && word.contains (EMOTICONS_END)) {

            int startIndex = word.lastIndexOf(EMOTICONS_START) + EMOTICONS_START.length();
            int endingIndex = word.indexOf(EMOTICONS_END, startIndex);

            String emoticonString = word.substring(startIndex, endingIndex);

            if (!emoticonString.isEmpty()
                    && emoticonString.length() <= 15
                    && emoticonString.matches(REGEX_ALPHANUMERIC)) {
                emoticons.put(emoticonString);
            }
        }
    }

}

package com.example.andro.ChatStringParser.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by andro on 12/19/2016.
 */

public class EmoticonsExtractor {
    private static final String EMOTICONS_START = "(";
    private static final String EMOTICONS_END = ")";
    private static final String EMOTICONS_CLOSING_OPENING_BRACKET = ")(";
    private static final String STRING_SPACE = " ";
    public static final String JSON_ARRAY_NAME = "emoticons";
    private static final String REGEX_ALPHANUMERIC = "[a-zA-Z0-9]+";
    private static final String REPLACEMENT = ") (";

    public static JSONObject checkForEmoticons(String inputString) {
        JSONObject jsonObject = new JSONObject();
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

        try {
            jsonObject.put(JSON_ARRAY_NAME, emoticons);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
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

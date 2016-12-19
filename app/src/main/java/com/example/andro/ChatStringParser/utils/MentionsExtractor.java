package com.example.andro.ChatStringParser.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by andro on 12/19/2016.
 */

public class MentionsExtractor {
    private static final String MENTIONS_CHAR = "@";
    private static final String JSON_ARRAY_NAME_MENTIONS = "mentions";
    private static final String STRING_SPACE = " ";

    public static JSONObject checkForMentions(String inputString) {
        JSONObject jsonObject = new JSONObject();
        JSONArray mentions = new JSONArray();

        if (inputString.contains(MENTIONS_CHAR)) {
            String[] words = inputString.split(STRING_SPACE);

            for (String splitObject : words) {
                extractMentionFromWord(splitObject, mentions);
            }
        }

        try {
            jsonObject.put(JSON_ARRAY_NAME_MENTIONS, mentions);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

    private static void extractMentionFromWord(String word, JSONArray mentions) {

        if (word.contains(MENTIONS_CHAR)) {
            String[] wordSplits = word.split(MENTIONS_CHAR);

            for (String wordSplit : wordSplits) {

                if (wordSplit.contains(MENTIONS_CHAR)) {
                    extractMentionFromWord(word, mentions);
                } else if (!wordSplit.isEmpty()) {
                    mentions.put(wordSplit);
                }
            }

        }

    }

}

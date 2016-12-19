package com.example.andro.ChatStringParser.mainscreen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by andro on 12/18/2016.
 */

public class MainScreenPresenter {
    public static final String EMOTICONS_START = "(";
    public static final String EMOTICONS_END = ")";
    public static final String SPLIT_REGEX_NON_WORD = "\\\\W";
    public static final String MENTIONS_CHAR = "@";
    public static final String JSONARRAY_NAME_MENTIONS = "mentions";
    public static final String STRING_SPACE = " ";
    private MainScreenView mainScreenView;

    public MainScreenPresenter(MainScreenView mainScreenView) {
        this.mainScreenView = mainScreenView;
        this.mainScreenView.setMainScreenViewInterface(mainScreenViewInterface);

    }

    private MainScreenView.MainScreenViewInterface
            mainScreenViewInterface = new MainScreenView.MainScreenViewInterface() {

        @Override
        public void inputString(String inputString) {

            String mentions = checkForMentions(inputString).toString();
            String emoticons = checkForEmoticons(inputString).toString();
            String links;

            String displaytext = inputString + "\n" + mentions + "\n" + emoticons;
            mainScreenView.setOutputText(displaytext);
        }

    };

    JSONObject checkForEmoticons(String inputString) {
        JSONObject jsonObject = new JSONObject();
        JSONArray emoticons = new JSONArray();


        if (inputString.contains(EMOTICONS_START) && inputString.contains(EMOTICONS_END)) {
            String[] splits = inputString.split(SPLIT_REGEX_NON_WORD);

            for (String splitObject : splits) {
                if (splitObject.contains(EMOTICONS_START) && splitObject.contains(EMOTICONS_END)) {
                    int startIndex = splitObject.lastIndexOf(EMOTICONS_START) + EMOTICONS_START.length();
                    int endingIndex = splitObject.indexOf(EMOTICONS_END, startIndex);

                    String emoticonString = splitObject.substring(startIndex, endingIndex);

                    if (!emoticonString.isEmpty()) {
                        emoticons.put(emoticonString);
                    }
                }
            }
        }

        try {
            jsonObject.put("emoticons", emoticons);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }

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
            jsonObject.put(JSONARRAY_NAME_MENTIONS, mentions);
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

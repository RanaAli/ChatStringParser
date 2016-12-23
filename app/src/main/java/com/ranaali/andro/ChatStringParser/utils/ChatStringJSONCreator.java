package com.ranaali.andro.ChatStringParser.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.ranaali.andro.ChatStringParser.utils.EmoticonsExtractor.checkForEmoticons;
import static com.ranaali.andro.ChatStringParser.utils.MentionsExtractor.checkForMentions;


/**
 * Created by andro on 12/22/2016.
 */

public class ChatStringJSONCreator {
    public static final String MENTIONS_JSON_ARRAY_NAME = "mentions";
    public static final String EMOTICONS_JSON_ARRAY_NAME = "emoticons";
    public static final String LINK_JSON_ARRAY_NAME = "links";

    /**
     * The function checks for any mentions, emoticons
     * and links in the text and returns JSONObject in the following format
     {
     "mentions": ["bob"],
     "emoticons": ["success"],
     "links": [
            { "url": "https://abc.xyz/",
                "title": "abcxyz..." }
        ]
     }
     *
     * @param inputString text to be processed
     * @return JSONObject with arrays of identified items
     */
    public static JSONObject ChatStringToJSON(String inputString) {
        JSONObject jsonObject = new JSONObject();

        JSONArray mentions = checkForMentions(inputString);
        JSONArray emoticons = checkForEmoticons(inputString);
        JSONArray links = LinkExtractor.checkForLinks(inputString);

        try {

            jsonObject.put(MENTIONS_JSON_ARRAY_NAME, mentions);
            jsonObject.put(EMOTICONS_JSON_ARRAY_NAME, emoticons);
            jsonObject.put(LINK_JSON_ARRAY_NAME, links);

        } catch (JSONException e) {
            e.printStackTrace();

        }

        return jsonObject;
    }
}
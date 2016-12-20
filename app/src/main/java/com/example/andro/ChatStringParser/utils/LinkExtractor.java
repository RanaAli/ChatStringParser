package com.example.andro.ChatStringParser.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by andro on 12/20/2016.
 */

public class LinkExtractor {

    public static final String REGEX_URL = "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)(([\\w\\-]+\\.)" +
            "{1,}?([\\w\\-.~]+\\/?)*[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)";

    public static final String JSON_ARRAY_NAME = "links";
    public static final String JSON_OBJECT_URL = "url";
    public static final String JSON_OBJECT_TITLE = "title";

    public static JSONObject checkForLinks(String inputString) {
        JSONObject jsonObject = new JSONObject();
        JSONArray links = new JSONArray();

        Pattern urlPattern = Pattern.compile(REGEX_URL,
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

        Matcher matcher = urlPattern.matcher(inputString);

        String output = null;

        while (matcher.find()) {
            int start = matcher.start(1);
            int end = matcher.end();

            output = inputString.substring(start, end);
        }

        try {

        if (output != null) {
            JSONObject linkObject = new JSONObject();
            linkObject.put(JSON_OBJECT_URL, output);
            linkObject.put(JSON_OBJECT_TITLE, "");

            links.put(linkObject);
        }

            jsonObject.put(JSON_ARRAY_NAME, links);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}

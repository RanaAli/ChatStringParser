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

    private static final String REGEX_URL = "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)(([\\w\\-]+\\.)" +
            "{1,}?([\\w\\-.~]+\\/?)*[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)";

    public static final String JSON_ARRAY_NAME = "links";
    public static final String JSON_OBJECT_URL = "url";
    private static final String JSON_OBJECT_TITLE = "title";
    private static final String WWW = "www";
    private static final String HTTP = "http://";
    private static final String HTTPS = "https://";
    private static final String FTP = "ftp://";

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

            if (output.startsWith(WWW)) {
                String urlProtocol = inputString.substring(0, start);

                if (urlProtocol.endsWith(HTTP)) {
                    output = HTTP + output;
                } else if (urlProtocol.endsWith(HTTPS)) {
                    output = HTTPS + output;
                } else if (urlProtocol.endsWith(FTP)) {
                    output = FTP + output;
                }
            }
        }

        try {

            JSONObject linkObject = new JSONObject();
            linkObject.put(JSON_OBJECT_URL, output);
            linkObject.put(JSON_OBJECT_TITLE, "");

            links.put(linkObject);

            jsonObject.put(JSON_ARRAY_NAME, links);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}

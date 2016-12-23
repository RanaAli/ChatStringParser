package com.ranaali.andro.ChatStringParser.utils;

import android.support.annotation.NonNull;

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

    public static final String JSON_OBJECT_URL = "url";
    private static final String JSON_OBJECT_TITLE = "title";
    private static final String WWW = "www";
    private static final String HTTP = "http://";
    private static final String HTTPS = "https://";
    private static final String FTP = "ftp://";

    /**
     * The function checks for any links
     * in the text and returns JSONArray.
     *
     * @param inputString text to be processed
     * @return JSONArray with identified items
     *
     */
    public static JSONArray checkForLinks(String inputString) {
        JSONArray links = new JSONArray();

        Pattern urlPattern = Pattern.compile(REGEX_URL,
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

        Matcher matcher = urlPattern.matcher(inputString);

        try {

            while (matcher.find()) {
                String output;
                int start = matcher.start(1);
                int end = matcher.end();

                output = inputString.substring(start, end);

                output = addUrlProtocolIfNeeded(inputString, output, start);

                String urlTitle;

                try {
                    urlTitle = TitleExtractor.getPageTitle(output);
                } catch (Exception e) {
                    e.printStackTrace();
                    urlTitle = "";
                }

                JSONObject linkObject = new JSONObject();
                linkObject.put(JSON_OBJECT_URL, output);
                linkObject.put(JSON_OBJECT_TITLE, urlTitle);

                links.put(linkObject);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return links;
    }

    @NonNull
    private static String addUrlProtocolIfNeeded(String inputString, String output, int start) {
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
        return output;
    }
}

package com.ranaali.andro.myapplication.mainscreen;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static com.ranaali.andro.ChatStringParser.utils.LinkExtractor.JSON_OBJECT_URL;
import static com.ranaali.andro.ChatStringParser.utils.LinkExtractor.checkForLinks;

/**
 * Created by andro on 12/20/2016.
 */

public class LinkExtractorTest {

    private static final String TEST_LINKS_NORMAL = "Olympics are starting soon; " +
            "http://www.nbcolympics.com";
    private static final String TEST_LINKS_NORMAL_TWO = "Olympics are starting soon; " +
            "http://www.nbcolympics.com https://www.youtube.com/watch?v=fbrP5GiNxj0";
    private static final String TEST_LINKS_CONCATENATED_TEXT_START = "Olympics are starting soon; " +
            "asdfasdfhttp://www.nbcolympics.com";
    private static final String TEST_LINKS_CONCATENATED_TEXT_END = "Olympics are starting soon; " +
            "http://olympics.nbcsports.com/2016/12/15/gabby-douglas-leslie-jones-mom-natalie-hawkins/";
    private static final String TEST_LINKS_CONCATENATED_SPECIAL_CHARS_START = "Olympics are " +
            "starting soon; #$%http://www.nbcolympics.com";
    private static final String TEST_LINKS_CONCATENATED_SPECIAL_CHARS_END = "Olympics are " +
            "starting soon; http://www.nbcolympics.com/search/results?search_term=$";

    @Test
    public void normalLink() throws Exception {
        JSONArray links = checkForLinks(TEST_LINKS_NORMAL);

        Assert.assertEquals(1, links.length());

        JSONObject linksJSONObject = links.getJSONObject(0);
        Assert.assertEquals("http://www.nbcolympics.com",
                linksJSONObject.getString(JSON_OBJECT_URL));
    }

    @Test
    public void normalTwoLink() throws Exception {
        JSONArray links = checkForLinks(TEST_LINKS_NORMAL_TWO);

        Assert.assertEquals(2, links.length());

        JSONObject linksJSONObject = links.getJSONObject(0);
        Assert.assertEquals("http://www.nbcolympics.com",
                linksJSONObject.getString(JSON_OBJECT_URL));

        linksJSONObject = links.getJSONObject(1);
        Assert.assertEquals("https://www.youtube.com/watch?v=fbrP5GiNxj0",
                linksJSONObject.getString(JSON_OBJECT_URL));
    }

    @Test
    public void concatenatedTextStartLink() throws Exception {
        JSONArray links = checkForLinks(TEST_LINKS_CONCATENATED_TEXT_START);

        Assert.assertEquals(1, links.length());

        JSONObject linksJSONObject = links.getJSONObject(0);
        Assert.assertEquals("http://www.nbcolympics.com",
                linksJSONObject.getString(JSON_OBJECT_URL));
    }

    @Test
    public void concatenatedTextEndLink() throws Exception {
        JSONArray links = checkForLinks(TEST_LINKS_CONCATENATED_TEXT_END);

        Assert.assertEquals(1, links.length());


        JSONObject linksJSONObject = links.getJSONObject(0);
        Assert.assertEquals("http://olympics.nbcsports.com/2016/12/15/" +
                "gabby-douglas-leslie-jones-mom-natalie-hawkins/",
                linksJSONObject.getString(JSON_OBJECT_URL));
    }

    @Test
    public void concatenatedSpecialCharsStartLink() throws Exception {
        JSONArray links = checkForLinks(TEST_LINKS_CONCATENATED_SPECIAL_CHARS_START);

        Assert.assertEquals(1, links.length());

        JSONObject linksJSONObject = links.getJSONObject(0);
        Assert.assertEquals("http://www.nbcolympics.com",
                linksJSONObject.getString(JSON_OBJECT_URL));
    }

    @Test
    public void concatenatedSpecialCharsEndLink() throws Exception {
        JSONArray links = checkForLinks(TEST_LINKS_CONCATENATED_SPECIAL_CHARS_END);

        Assert.assertEquals(1, links.length());

        JSONObject linksJSONObject = links.getJSONObject(0);
        Assert.assertEquals("http://www.nbcolympics.com/search/results?search_term=$",
                linksJSONObject.getString(JSON_OBJECT_URL));
    }
}

package com.example.andro.myapplication.mainscreen;

import com.example.andro.ChatStringParser.utils.LinkExtractor;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static com.example.andro.ChatStringParser.utils.LinkExtractor.*;

/**
 * Created by andro on 12/20/2016.
 */

public class LinkExtractorTest {

    private static final String TEST_LINKS_NORMAL = "Olympics are starting soon; http://www.nbcolympics.com";
    private static final String TEST_LINKS_NORMAL_TWO = "Olympics are starting soon; www.nbcolympics.com";
    private static final String TEST_LINKS_CONCATENATED_TEXT_START = "Olympics are starting soon; asdfasdfhttp://www.nbcolympics.com";
    private static final String TEST_LINKS_CONCATENATED_TEXT_END = "Olympics are starting soon; http://www.nbcolympics.comsagfsdfg";
    private static final String TEST_LINKS_CONCATENATED_SPECIAL_CHARS_START = "Olympics are starting soon; #$%http://www.nbcolympics.com";
    private static final String TEST_LINKS_CONCATENATED_SPECIAL_CHARS_END = "Olympics are starting soon; http://www.nbcolympics.com@#$#";

    @Test
    public void normalLink() throws Exception {
        JSONObject jsonObject = checkForLinks(TEST_LINKS_NORMAL);

        JSONArray links = jsonObject.getJSONArray(LinkExtractor.JSON_ARRAY_NAME);

        Assert.assertEquals(links.length(), 1);

        JSONObject linksJSONObject = links.getJSONObject(0);
        Assert.assertEquals("http://www.nbcolympics.com",
                linksJSONObject.getString(JSON_OBJECT_URL));
    }

    @Test
    public void normalTwoLink() throws Exception {
        JSONObject jsonObject = checkForLinks(TEST_LINKS_NORMAL_TWO);

        JSONArray links = jsonObject.getJSONArray(LinkExtractor.JSON_ARRAY_NAME);

        Assert.assertEquals(links.length(), 1);

        JSONObject linksJSONObject = links.getJSONObject(0);
        Assert.assertEquals("www.nbcolympics.com",
                linksJSONObject.getString(JSON_OBJECT_URL));
    }

    @Test
    public void concatenatedTextStartLink() throws Exception {
        JSONObject jsonObject = checkForLinks(TEST_LINKS_CONCATENATED_TEXT_START);

        JSONArray links = jsonObject.getJSONArray(LinkExtractor.JSON_ARRAY_NAME);

        Assert.assertEquals(links.length(), 1);

        JSONObject linksJSONObject = links.getJSONObject(0);
        Assert.assertEquals("http://www.nbcolympics.com",
                linksJSONObject.getString(JSON_OBJECT_URL));
    }

    @Test
    public void concatenatedTextEndLink() throws Exception {
        JSONObject jsonObject = checkForLinks(TEST_LINKS_CONCATENATED_TEXT_END);

        JSONArray links = jsonObject.getJSONArray(LinkExtractor.JSON_ARRAY_NAME);

        Assert.assertEquals(links.length(), 1);

        JSONObject linksJSONObject = links.getJSONObject(0);
        Assert.assertEquals("http://www.nbcolympics.comsagfsdfg",
                linksJSONObject.getString(JSON_OBJECT_URL));
    }

    @Test
    public void concatenatedSpecialCharsStartLink() throws Exception {
        JSONObject jsonObject = checkForLinks(TEST_LINKS_CONCATENATED_SPECIAL_CHARS_START);

        JSONArray links = jsonObject.getJSONArray(LinkExtractor.JSON_ARRAY_NAME);

        Assert.assertEquals(links.length(), 1);

        JSONObject linksJSONObject = links.getJSONObject(0);
        Assert.assertEquals("http://www.nbcolympics.com",
                linksJSONObject.getString(JSON_OBJECT_URL));
    }

    @Test
    public void concatenatedSpecialCharsEndLink() throws Exception {
        JSONObject jsonObject = checkForLinks(TEST_LINKS_CONCATENATED_SPECIAL_CHARS_END);

        JSONArray links = jsonObject.getJSONArray(LinkExtractor.JSON_ARRAY_NAME);

        Assert.assertEquals(links.length(), 1);

        JSONObject linksJSONObject = links.getJSONObject(0);
        Assert.assertEquals("http://www.nbcolympics.com@#$#",
                linksJSONObject.getString(JSON_OBJECT_URL));
    }
}

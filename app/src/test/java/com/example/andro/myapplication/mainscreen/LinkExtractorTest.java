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

    public static final String TEST_LINKS_NORMAL = "Olympics are starting soon; http://www.nbcolympics.com";

    @Test
    public void normalString() throws Exception {
        JSONObject jsonObject = checkForLinks(TEST_LINKS_NORMAL);

        JSONArray links = jsonObject.getJSONArray(LinkExtractor.JSON_ARRAY_NAME);

        Assert.assertEquals(links.length(), 1);

        JSONObject linksJSONObject = links.getJSONObject(0);
        Assert.assertEquals("http://www.nbcolympics.com",
                linksJSONObject.getString(JSON_OBJECT_URL));
    }
}

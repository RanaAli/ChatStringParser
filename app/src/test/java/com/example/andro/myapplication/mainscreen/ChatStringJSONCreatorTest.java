package com.example.andro.myapplication.mainscreen;

import com.example.andro.ChatStringParser.utils.ChatStringJSONCreator;

import junit.framework.Assert;

import org.json.JSONObject;
import org.junit.Test;

/**
 * Created by andro on 12/22/2016.
 */

public class ChatStringJSONCreatorTest {
    private static final String TEST_MAIN_SCREEN_PRE_NORMAL = "good morning! this is @ali. " +
            "good morning! this is (happy). Olympics are starting soon; \" +\n" +
            "            \"http://www.nbcolympics.com";


    @Test
    public void normalTest() throws Exception {
        JSONObject jsonObject = ChatStringJSONCreator.ChatStringToJSON(TEST_MAIN_SCREEN_PRE_NORMAL);

        Assert.assertEquals(3, jsonObject.length());

    }


}

package com.example.andro.myapplication.mainscreen;

import com.example.andro.ChatStringParser.mainscreen.MainScreenPresenter;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

/**
 * Created by andro on 12/19/2016.
 */

public class MentionTest {

    public static final String TEST_MENTION_NORMAL = "good morning! this is @ali";
    public static final String TEST_MENTION_MORE_THEN_ONE = "good morning! this is @ali @rana";
    public static final String TEST_MENTION_EMTY = "good morning! this is @ali @";
    public static final String TEST_MENTION_NO_MENTION = "good morning! this is";

    @Test
    public void normalMention() throws Exception {
        JSONObject jsonObject = MainScreenPresenter
                .checkForMentions(TEST_MENTION_NORMAL);

        JSONArray mentions = jsonObject.getJSONArray(MainScreenPresenter.JSONARRAY_NAME_MENTIONS);

        Assert.assertEquals(mentions.length(), 1);

        String mentionString = mentions.getString(0);
        Assert.assertEquals("ali", mentionString);
    }

    @Test
    public void moreThenOneMention() throws Exception {
        JSONObject jsonObject = MainScreenPresenter
                .checkForMentions(TEST_MENTION_MORE_THEN_ONE);

        JSONArray mentions = jsonObject.getJSONArray(MainScreenPresenter.JSONARRAY_NAME_MENTIONS);

        Assert.assertEquals(2, mentions.length());

        String mentionString = mentions.getString(0);
        Assert.assertEquals("ali", mentionString);

        mentionString = mentions.getString(1);
        Assert.assertEquals("rana", mentionString);
    }

    @Test
    public void emptyMention() throws Exception {
        JSONObject jsonObject = MainScreenPresenter
                .checkForMentions(TEST_MENTION_EMTY);

        JSONArray mentions = jsonObject.getJSONArray(MainScreenPresenter.JSONARRAY_NAME_MENTIONS);

        Assert.assertEquals(1, mentions.length());

        String mentionString = mentions.getString(0);
        Assert.assertEquals("ali", mentionString);

    }

    @Test
    public void noMention() throws Exception {
        JSONObject jsonObject = MainScreenPresenter
                .checkForMentions(TEST_MENTION_NO_MENTION);

        JSONArray mentions = jsonObject.getJSONArray(MainScreenPresenter.JSONARRAY_NAME_MENTIONS);

        Assert.assertEquals(0, mentions.length());

    }
}

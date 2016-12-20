package com.example.andro.myapplication.mainscreen;

import com.example.andro.ChatStringParser.utils.MentionsExtractor;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static com.example.andro.ChatStringParser.utils.MentionsExtractor.checkForMentions;

/**
 * Created by andro on 12/19/2016.
 */

public class MentionTest {

    private static final String TEST_MENTION_NORMAL = "good morning! this is @ali";
    private static final String TEST_MENTION_MORE_THEN_ONE = "good morning! this is @ali @rana";
    private static final String TEST_MENTION_EMPTY = "good morning! this is @ali @";
    private static final String TEST_MENTION_NO_MENTION = "good morning! this is";
    private static final String TEST_MENTION_DOUBLE_MENTION_SYMBOL = "good morning! this is @@ali";
    private static final String TEST_MENTION_ENDING_NON_WORD_CHAR = "good morning! this is @ali@";
    private static final String TEST_MENTION_TWO_CONCATENATED_MENTIONS = "good morning! this is @rana@ali@";
    private static final String TEST_MENTION_UNDERSCORE = "good morning! this is @rana_ali";
    private static final String TEST_MENTION_UNDERSCORE_ENDING_SYMBOL = "good morning! this is @rana_ali@";
    private static final String TEST_STRING = "@ali_! @AlU_!";

    @Test
    public void normalMention() throws Exception {
        JSONObject jsonObject = checkForMentions(TEST_MENTION_NORMAL);

        JSONArray mentions = jsonObject.getJSONArray(MentionsExtractor.JSON_ARRAY_NAME);

        Assert.assertEquals(mentions.length(), 1);

        String mentionString = mentions.getString(0);
        Assert.assertEquals("ali", mentionString);
    }

    @Test
    public void moreThenOneMention() throws Exception {
        JSONObject jsonObject = checkForMentions(TEST_MENTION_MORE_THEN_ONE);

        JSONArray mentions = jsonObject.getJSONArray(MentionsExtractor.JSON_ARRAY_NAME);

        Assert.assertEquals(2, mentions.length());

        String mentionString = mentions.getString(0);
        Assert.assertEquals("ali", mentionString);

        mentionString = mentions.getString(1);
        Assert.assertEquals("rana", mentionString);
    }

    @Test
    public void emptyMention() throws Exception {
        JSONObject jsonObject = checkForMentions(TEST_MENTION_EMPTY);

        JSONArray mentions = jsonObject.getJSONArray(MentionsExtractor.JSON_ARRAY_NAME);

        Assert.assertEquals(1, mentions.length());

        String mentionString = mentions.getString(0);
        Assert.assertEquals("ali", mentionString);

    }

    @Test
    public void noMention() throws Exception {
        JSONObject jsonObject = checkForMentions(TEST_MENTION_NO_MENTION);

        JSONArray mentions = jsonObject.getJSONArray(MentionsExtractor.JSON_ARRAY_NAME);

        Assert.assertEquals(0, mentions.length());

    }

    @Test
    public void doubleSymbolMention() throws Exception {
        JSONObject jsonObject = checkForMentions(TEST_MENTION_DOUBLE_MENTION_SYMBOL);

        JSONArray mentions = jsonObject.getJSONArray(MentionsExtractor.JSON_ARRAY_NAME);

        Assert.assertEquals(1, mentions.length());

        String mentionString = mentions.getString(0);
        Assert.assertEquals("ali", mentionString);
    }

    @Test
    public void nonWordCharEndingMention() throws Exception {
        JSONObject jsonObject = checkForMentions(TEST_MENTION_ENDING_NON_WORD_CHAR);

        JSONArray mentions = jsonObject.getJSONArray(MentionsExtractor.JSON_ARRAY_NAME);

        Assert.assertEquals(1, mentions.length());

        String mentionString = mentions.getString(0);
        Assert.assertEquals("ali", mentionString);
    }

    @Test
    public void concatenatedMention() throws Exception {
        JSONObject jsonObject = checkForMentions(TEST_MENTION_TWO_CONCATENATED_MENTIONS);

        JSONArray mentions = jsonObject.getJSONArray(MentionsExtractor.JSON_ARRAY_NAME);

        Assert.assertEquals(2, mentions.length());

        String mentionString = mentions.getString(0);
        Assert.assertEquals("rana", mentionString);

        mentionString = mentions.getString(1);
        Assert.assertEquals("ali", mentionString);
    }


    @Test
    public void underscoreMention() throws Exception {
        JSONObject jsonObject = checkForMentions(TEST_MENTION_UNDERSCORE);

        JSONArray mentions = jsonObject.getJSONArray(MentionsExtractor.JSON_ARRAY_NAME);

        Assert.assertEquals(1, mentions.length());

        String mentionString = mentions.getString(0);
        Assert.assertEquals("rana_ali", mentionString);

    }

    @Test
    public void underscoreEndingSymbolMention() throws Exception {
        JSONObject jsonObject = checkForMentions(TEST_MENTION_UNDERSCORE_ENDING_SYMBOL);

        JSONArray mentions = jsonObject.getJSONArray(MentionsExtractor.JSON_ARRAY_NAME);

        Assert.assertEquals(1, mentions.length());

        String mentionString = mentions.getString(0);
        Assert.assertEquals("rana_ali", mentionString);

    }

    @Test
    public void otherTestMention() throws Exception {
        JSONObject jsonObject = checkForMentions(TEST_STRING);

        JSONArray mentions = jsonObject.getJSONArray(MentionsExtractor.JSON_ARRAY_NAME);

        Assert.assertEquals(2, mentions.length());

        String mentionString = mentions.getString(0);
        Assert.assertEquals("ali_", mentionString);

        mentionString = mentions.getString(1);
        Assert.assertEquals("AlU_", mentionString);
    }

}

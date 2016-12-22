package com.example.andro.myapplication.mainscreen;

import junit.framework.Assert;

import org.json.JSONArray;
import org.junit.Test;

import static com.example.andro.ChatStringParser.utils.EmoticonsExtractor.checkForEmoticons;

/**
 * Created by andro on 12/19/2016.
 */

public class EmoticonsTest {
    private static final String TEST_EMOTICON_ONE_CHAR = "take me as your input (A)";
    private static final String TEST_EMOTICON_ONE_NUM = "take me as your input (1)";
    private static final String TEST_EMOTICON_MISSING_BRACKET = "take me as your input (happy";
    private static final String TEST_EMOTICON_NORMAL = "good morning! this is (happy)";
    private static final String TEST_EMOTICON_MULTIPLE_BRACKET_1 = "good morning! this is ((happy)";
    private static final String TEST_EMOTICON_MULTIPLE_BRACKET_2 = "good morning! this is ((happy))";
    private static final String TEST_EMOTICON_MULTIPLE_BRACKET_3 = "good morning! this is (happy))";
    private static final String TEST_EMOTICON_EMPTY = "good morning! this is ()";
    private static final String TEST_EMOTICON_TWO = "good morning! this is (happy) (android)";
    private static final String TEST_EMOTICON_TWO_CONCATENATED = "good morning! this is (happy)(android)";
    private static final String TEST_EMOTICON_THREE_CONCATENATED = "good morning! this is (happy)(android)(bamboo)";
    private static final String TEST_EMOTICON_EXTRA_CONCATENATED = "good morning! this is (happy)android";
    private static final String TEST_EMOTICON_TOO_LONG = "good morning! this is (happyhappyhappyhappy)";
    private static final String TEST_EMOTICON_MAX_LENGTH = "good morning! this is (happyhappyhappy)";
    private static final String TEST_EMOTICON_ALPHANUMERIC = "good morning! this is (happy123)";
    private static final String TEST_EMOTICON_NON_ALPHANUMERIC = "good morning! this is (happy1@!3)";

    @Test
    public void normalEmoticon() throws Exception {
        JSONArray emoticons = checkForEmoticons(TEST_EMOTICON_NORMAL);

        Assert.assertEquals(1, emoticons.length());

        String emoticonsString = emoticons.getString(0);
        Assert.assertEquals("happy", emoticonsString);
    }

    @Test
    public void emptyEmoticon() throws Exception {
        JSONArray emoticons = checkForEmoticons(TEST_EMOTICON_EMPTY);

        Assert.assertEquals(0, emoticons.length());
    }

    @Test
    public void twoEmoticon() throws Exception {
        JSONArray emoticons = checkForEmoticons(TEST_EMOTICON_TWO);

        Assert.assertEquals(2, emoticons.length());

        String emoticonsString = emoticons.getString(0);
        Assert.assertEquals("happy", emoticonsString);

        emoticonsString = emoticons.getString(1);
        Assert.assertEquals("android", emoticonsString);
    }

    @Test
    public void concatenatedEmoticon() throws Exception {
        JSONArray emoticons = checkForEmoticons(TEST_EMOTICON_TWO_CONCATENATED);

        Assert.assertEquals(2, emoticons.length());

        String emoticonsString = emoticons.getString(0);
        Assert.assertEquals("happy", emoticonsString);

        emoticonsString = emoticons.getString(1);
        Assert.assertEquals("android", emoticonsString);
    }

    @Test
    public void threeConcatenatedEmoticon() throws Exception {
        JSONArray emoticons = checkForEmoticons(TEST_EMOTICON_THREE_CONCATENATED);

        Assert.assertEquals(3, emoticons.length());

        String emoticonsString = emoticons.getString(0);
        Assert.assertEquals("happy", emoticonsString);

        emoticonsString = emoticons.getString(1);
        Assert.assertEquals("android", emoticonsString);

        emoticonsString = emoticons.getString(2);
        Assert.assertEquals("bamboo", emoticonsString);
    }

    @Test
    public void extraConcatenatedEmoticon() throws Exception {
        JSONArray emoticons = checkForEmoticons(TEST_EMOTICON_EXTRA_CONCATENATED);

        Assert.assertEquals(1, emoticons.length());

        String emoticonsString = emoticons.getString(0);
        Assert.assertEquals("happy", emoticonsString);
    }

    @Test
    public void tooLongEmoticon() throws Exception {
        JSONArray emoticons = checkForEmoticons(TEST_EMOTICON_TOO_LONG);

        Assert.assertEquals(0, emoticons.length());
    }

    @Test
    public void maxLengthEmoticon() throws Exception {
        JSONArray emoticons = checkForEmoticons(TEST_EMOTICON_MAX_LENGTH);

        Assert.assertEquals(1, emoticons.length());

        String emoticonsString = emoticons.getString(0);
        Assert.assertEquals("happyhappyhappy", emoticonsString);
    }

    @Test
    public void alphaNumericEmoticon() throws Exception {
        JSONArray emoticons = checkForEmoticons(TEST_EMOTICON_ALPHANUMERIC);

        Assert.assertEquals(1, emoticons.length());

        String emoticonsString = emoticons.getString(0);
        Assert.assertEquals("happy123", emoticonsString);
    }

    @Test
    public void nonAlphaNumericEmoticon() throws Exception {
        JSONArray emoticons = checkForEmoticons(TEST_EMOTICON_NON_ALPHANUMERIC);

        Assert.assertEquals(0, emoticons.length());
    }

    @Test
    public void multipleBracketsOneEmoticon() throws Exception {
        JSONArray emoticons = checkForEmoticons(TEST_EMOTICON_MULTIPLE_BRACKET_1);

        Assert.assertEquals(1, emoticons.length());

        String emoticonsString = emoticons.getString(0);
        Assert.assertEquals("happy", emoticonsString);
    }

    @Test
    public void multipleBracketsTwoEmoticon() throws Exception {
        JSONArray emoticons =  checkForEmoticons(TEST_EMOTICON_MULTIPLE_BRACKET_2);

        Assert.assertEquals(1, emoticons.length());

        String emoticonsString = emoticons.getString(0);
        Assert.assertEquals("happy", emoticonsString);
    }

    @Test
    public void multipleBracketsThreeEmoticon() throws Exception {
        JSONArray emoticons = checkForEmoticons(TEST_EMOTICON_MULTIPLE_BRACKET_3);

        Assert.assertEquals(1, emoticons.length());

        String emoticonsString = emoticons.getString(0);
        Assert.assertEquals("happy", emoticonsString);
    }
}

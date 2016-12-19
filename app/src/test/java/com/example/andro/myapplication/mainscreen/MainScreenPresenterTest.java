package com.example.andro.myapplication.mainscreen;

import com.example.andro.ChatStringParser.mainscreen.MainScreenPresenter;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

/**
 * Created by andro on 12/19/2016.
 */

public class MainScreenPresenterTest {

    @Test
    public void normalMention() throws Exception {
        JSONObject jsonObject = MainScreenPresenter
                .checkForMentions("good morning! this is @ali @ ");

        JSONArray mentions = jsonObject.getJSONArray(MainScreenPresenter.JSONARRAY_NAME_MENTIONS);

        Assert.assertEquals(mentions.length(), 1);

    }
}

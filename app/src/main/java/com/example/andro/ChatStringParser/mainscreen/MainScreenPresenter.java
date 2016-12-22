package com.example.andro.ChatStringParser.mainscreen;

import android.os.AsyncTask;

import com.example.andro.ChatStringParser.utils.LinkExtractor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.andro.ChatStringParser.utils.EmoticonsExtractor.checkForEmoticons;
import static com.example.andro.ChatStringParser.utils.MentionsExtractor.checkForMentions;

/**
 * Created by andro on 12/18/2016.
 */

public class MainScreenPresenter {
    public static final String MENTIONS_JSON_ARRAY_NAME = "mentions";
    public static final String EMOTICONS_JSON_ARRAY_NAME = "emoticons";
    public static final String LINK_JSON_ARRAY_NAME = "links";

    private MainScreenView mainScreenView;

    public MainScreenPresenter(MainScreenView mainScreenView) {
        this.mainScreenView = mainScreenView;
        this.mainScreenView.setMainScreenViewInterface(mainScreenViewInterface);

    }

    private MainScreenView.MainScreenViewInterface
            mainScreenViewInterface = new MainScreenView.MainScreenViewInterface() {

        @Override
        public void inputString(String inputString) {

            JSONObject jsonObject = getCreateJsonOfInputText(inputString);

            String displayText = inputString + jsonObject.toString();
            mainScreenView.setOutputText(displayText);
        }

    };

    private JSONObject getCreateJsonOfInputText(String inputString) {

        JSONObject jsonObject;

        try {
            jsonObject = new CreateJson().execute(inputString).get();

        } catch (Exception e) {
            e.printStackTrace();

            jsonObject = new JSONObject();
        }

        return jsonObject;
    }


    private class CreateJson extends AsyncTask<String, String, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            JSONObject jsonObject = new JSONObject();

            String inputString = params[0];

            JSONArray mentions = checkForMentions(inputString);
            JSONArray emoticons = checkForEmoticons(inputString);
            JSONArray links = LinkExtractor.checkForLinks(inputString);

            try {
                jsonObject.put(MENTIONS_JSON_ARRAY_NAME, mentions);
                jsonObject.put(EMOTICONS_JSON_ARRAY_NAME, emoticons);
                jsonObject.put(LINK_JSON_ARRAY_NAME, links);
            } catch (JSONException e) {
                e.printStackTrace();

            }

            return jsonObject;
        }
    }

}

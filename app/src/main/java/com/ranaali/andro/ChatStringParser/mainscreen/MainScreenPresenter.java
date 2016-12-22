package com.ranaali.andro.ChatStringParser.mainscreen;

import android.os.AsyncTask;

import com.ranaali.andro.ChatStringParser.utils.ChatStringJSONCreator;

import org.json.JSONObject;

/**
 * Created by andro on 12/18/2016.
 */

public class MainScreenPresenter {

    private static final String NEW_LINE = "\n";
    private MainScreenView mainScreenView;

    MainScreenPresenter(MainScreenView mainScreenView) {
        this.mainScreenView = mainScreenView;
        this.mainScreenView.setMainScreenViewInterface(mainScreenViewInterface);

    }

    private IMainScreenView
            mainScreenViewInterface = new IMainScreenView() {

        @Override
        public void inputString(String inputString) {

            try {
                JSONObject jsonObject = new CreateJson().execute(inputString).get();

                String displayText = inputString + NEW_LINE + NEW_LINE + jsonObject.toString();
                mainScreenView.setOutputText(displayText);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    };

    private class CreateJson extends AsyncTask<String, String, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            return ChatStringJSONCreator.ChatStringToJSON(params[0]);
        }
    }

}

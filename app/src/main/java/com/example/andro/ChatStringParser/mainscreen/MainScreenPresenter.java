package com.example.andro.ChatStringParser.mainscreen;

import android.os.AsyncTask;

import com.example.andro.ChatStringParser.utils.ChatStringJSONCreator;

import org.json.JSONObject;

/**
 * Created by andro on 12/18/2016.
 */

public class MainScreenPresenter {

    private MainScreenView mainScreenView;

    public MainScreenPresenter(MainScreenView mainScreenView) {
        this.mainScreenView = mainScreenView;
        this.mainScreenView.setMainScreenViewInterface(mainScreenViewInterface);

    }

    private MainScreenView.MainScreenViewInterface
            mainScreenViewInterface = new MainScreenView.MainScreenViewInterface() {

        @Override
        public void inputString(String inputString) {

            try {

                JSONObject jsonObject = new CreateJson().execute(inputString).get();

                String displayText = inputString + "\n" + "\n" + jsonObject.toString();
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

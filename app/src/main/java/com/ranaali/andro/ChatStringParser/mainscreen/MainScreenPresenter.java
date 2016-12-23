package com.ranaali.andro.ChatStringParser.mainscreen;

import android.os.AsyncTask;

import com.ranaali.andro.ChatStringParser.utils.ChatStringJSONCreator;

import org.json.JSONObject;

import static com.ranaali.andro.ChatStringParser.Constants.Constants.NEW_LINE;

/**
 * Created by andro on 12/18/2016.
 */

public class MainScreenPresenter {

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

                mainScreenView.showProgress();

                JSONObject jsonObject = new CreateJson().execute(inputString).get();

                String displayText = inputString + NEW_LINE + NEW_LINE + jsonObject.toString();
                mainScreenView.setOutputText(displayText);
            } catch (Exception e) {
                e.printStackTrace();
            }

            mainScreenView.hideProgress();
        }

    };

    private class CreateJson extends AsyncTask<String, String, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            return ChatStringJSONCreator.ChatStringToJSON(params[0]);
        }

    }

}

package com.ranaali.andro.ChatStringParser.mainscreen;

import android.os.AsyncTask;

import com.ranaali.andro.ChatStringParser.utils.ChatStringJSONCreator;

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

                String jsonString = new CreateJsonString().execute(inputString).get();

                String displayText = inputString + NEW_LINE + NEW_LINE + jsonString;
                mainScreenView.setOutputText(displayText);
            } catch (Exception e) {
                e.printStackTrace();
            }

            mainScreenView.hideProgress();
        }

    };

    private class CreateJsonString extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            return ChatStringJSONCreator.ChatStringToJSON(params[0]).toString();
        }

    }

}

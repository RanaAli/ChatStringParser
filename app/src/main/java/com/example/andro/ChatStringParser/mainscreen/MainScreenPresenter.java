package com.example.andro.ChatStringParser.mainscreen;

import static com.example.andro.ChatStringParser.utils.EmoticonsExtractor.checkForEmoticons;
import static com.example.andro.ChatStringParser.utils.MentionsExtractor.checkForMentions;

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

            String mentions = checkForMentions(inputString).toString();
            String emoticons = checkForEmoticons(inputString).toString();
            String links;

            String displaytext = inputString + "\n" + mentions + "\n" + emoticons;
            mainScreenView.setOutputText(displaytext);
        }

    };




}

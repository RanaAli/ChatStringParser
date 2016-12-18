package com.example.andro.ChatStringParser.mainscreen;

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
            mainScreenView.setOutputText(inputString);
        }

    };
}

package com.ranaali.andro.ChatStringParser.mainscreen;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ranaali.andro.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by andro on 12/18/2016.
 */

public class MainScreenView {

    private static final String NEW_LINE = "\n";

    @BindView(R.id.mainScreenOutputTextView)
    protected TextView outputTextView;

    @BindView(R.id.mainScreenInputEditText)
    protected EditText inputEditText;

    @BindView(R.id.mainScreenProgressRelativeLayout)
    protected RelativeLayout progressBarRelativeLayout;

    private IMainScreenView mainScreenViewInterface;

    MainScreenView(View view) {
        ButterKnife.bind(this, view);

        setupView();
    }

    private void setupView() {
        hideProgress();
    }

    @OnClick(R.id.mainScreenInputButton)
    protected void onEnterButtonClick(View v) {
            if(mainScreenViewInterface != null){
                mainScreenViewInterface.inputString(getInputText());
            }
        }

    void setOutputText(String outputText){
        outputTextView.setText(outputText);
    }

    void appendOutputText(String outputText){
        outputTextView.append(NEW_LINE + outputText);
    }

    String getInputText(){
        return inputEditText.getText().toString();
    }

    void showProgress(){
        progressBarRelativeLayout.setVisibility(View.VISIBLE);
    }

    void hideProgress(){
        progressBarRelativeLayout.setVisibility(View.INVISIBLE);
    }

    void setMainScreenViewInterface(IMainScreenView mainScreenViewInterface) {
        this.mainScreenViewInterface = mainScreenViewInterface;
    }
}

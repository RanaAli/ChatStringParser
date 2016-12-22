package com.ranaali.andro.ChatStringParser.mainscreen;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ranaali.andro.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andro on 12/18/2016.
 */

public class MainScreenView {

    private static final String NEW_LINE = "\n";

    @BindView(R.id.mainScreenOutputTextView)
    protected TextView outputTextView;

    @BindView(R.id.mainScreenInputEditText)
    protected EditText inputEditText;

    @BindView(R.id.mainScreenInputButton)
    protected Button inputButton;

    private IMainScreenView mainScreenViewInterface;

    MainScreenView(View view) {
        ButterKnife.bind(this, view);

        setupView();
    }

    private void setupView() {
        inputButton.setOnClickListener(inputButtonOnClickListener);
    }

    private View.OnClickListener inputButtonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mainScreenViewInterface != null){
                mainScreenViewInterface.inputString(getInputText());
            }
        }
    };
    void setOutputText(String outputText){
        outputTextView.setText(outputText);
    }

    void appendOutputText(String outputText){
        outputTextView.append(NEW_LINE + outputText);
    }

    String getInputText(){
        return inputEditText.getText().toString();
    }

    public void setMainScreenViewInterface(IMainScreenView mainScreenViewInterface) {
        this.mainScreenViewInterface = mainScreenViewInterface;
    }
}

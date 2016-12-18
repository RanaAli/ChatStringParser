package com.example.andro.ChatStringParser.mainscreen;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.andro.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andro on 12/18/2016.
 */

public class MainScreenView {
    @BindView(R.id.mainScreenOutputTextView)
    private TextView outputTextView;

    @BindView(R.id.mainScreenInputEditText)
    private EditText inputEditText;

    public MainScreenView(View view) {
        ButterKnife.bind(this, view);
    }

    public void setOutputText(String outputText){
        outputTextView.setText(outputText);
    }

    public String getInputText(){
        return inputEditText.getText().toString();
    }
}

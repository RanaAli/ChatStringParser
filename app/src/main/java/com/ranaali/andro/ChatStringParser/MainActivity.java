package com.ranaali.andro.ChatStringParser;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ranaali.andro.ChatStringParser.mainscreen.MainScreenFragment;
import com.ranaali.andro.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activty);

        if (savedInstanceState == null) {
            MainScreenFragment mainScreenFragment= new MainScreenFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.mainActivityFrameLayout, mainScreenFragment).commit();
        }
    }
}

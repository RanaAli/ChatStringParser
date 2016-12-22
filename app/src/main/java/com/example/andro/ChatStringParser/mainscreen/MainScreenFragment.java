package com.example.andro.ChatStringParser.mainscreen;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andro.myapplication.R;

/**
 * Created by andro on 12/18/2016.
 */

public class MainScreenFragment extends Fragment {
    private MainScreenView mainScreenView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.main_fragment, container, false);
        mainScreenView = new MainScreenView(view);
        MainScreenPresenter mainScreenPresenter = new MainScreenPresenter(mainScreenView);

        return view;

    }
}

package com.cretin.www.clearedittextproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cretin.www.clearedittext.view.ClearEditText;

public class MainActivity extends AppCompatActivity {
    private ClearEditText clearEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

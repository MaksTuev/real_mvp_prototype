package com.agna.realmvp.realmvpsample.ui.screen.test;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

public class TestActivity extends AppCompatActivity {

    public static void startForResult(Activity c){
        Intent i = new Intent(c, TestActivity.class);
        c.startActivityForResult(i, 9);
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setResult(RESULT_OK);
        finish();
    }
}

package com.spiner_checkbox.android.spiner_checkbox.Activity;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import com.spiner_checkbox.android.spiner_checkbox.R;

public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TabHost tabHost = getTabHost();

        tabHost.addTab(tabHost.newTabSpec("Add").setIndicator("Add").setContent(new Intent(this,AddActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("View").setIndicator("View").setContent(new Intent(this,ViewActivity.class)));
        tabHost.setCurrentTab(1);
    }
}

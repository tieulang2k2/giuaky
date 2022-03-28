package com.spiner_checkbox.android.spiner_checkbox.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.spiner_checkbox.android.spiner_checkbox.Model.SinhVien;
import com.spiner_checkbox.android.spiner_checkbox.R;

public class Infomation extends AppCompatActivity {
    TextView viewid,viewname,viewsex,viewjox,viewbirthday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        init();
        Getinfomation();
    }

    private void Getinfomation() {
        SinhVien sinhVien = (SinhVien) getIntent().getSerializableExtra("thongtin");
        viewid.setText("Id : "+sinhVien.getId());
        viewname.setText("Name : "+sinhVien.getName());
        viewsex.setText("Sex : "+sinhVien.getSex());
        viewjox.setText("Job : "+sinhVien.getJob());
        viewbirthday.setText("Brithday : "+sinhVien.getBirhday());
    }

    private void init() {
        viewid = (TextView) findViewById(R.id.viewid);
        viewname = (TextView) findViewById(R.id.viewname);
        viewsex = (TextView) findViewById(R.id.viewsex);
        viewjox = (TextView) findViewById(R.id.viewjox);
        viewbirthday = (TextView) findViewById(R.id.viewbrithday);
    }
}

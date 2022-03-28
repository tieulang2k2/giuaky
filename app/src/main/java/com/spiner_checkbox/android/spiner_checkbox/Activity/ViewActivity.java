package com.spiner_checkbox.android.spiner_checkbox.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.spiner_checkbox.android.spiner_checkbox.Adapter.SinhVienAdapter;
import com.spiner_checkbox.android.spiner_checkbox.Database.SQLiteHanderl;
import com.spiner_checkbox.android.spiner_checkbox.Model.SinhVien;
import com.spiner_checkbox.android.spiner_checkbox.R;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    SQLiteHanderl db;
    ListView lvview;
    ArrayList<SinhVien> sinhViens;
    SinhVienAdapter sinhVienAdapter;
    TextView txttong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        db =new SQLiteHanderl(getApplicationContext());
        init();
    }

    private void init() {
        lvview = (ListView)findViewById(R.id.lview);
         sinhViens = new ArrayList<>();
        sinhViens = getInfo();
        sinhVienAdapter = new SinhVienAdapter(this,sinhViens);
        lvview.setAdapter(sinhVienAdapter);
        lvview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ViewActivity.this,Infomation.class);
                intent.putExtra("thongtin",sinhViens.get(i));
                startActivity(intent);
            }
        });
        txttong = (TextView) findViewById(R.id.txttong);
        txttong.setText("Số lượng :"+sinhViens.size());
    }

    private ArrayList<SinhVien> getInfo() {
        Cursor cursor = db.getAllDataSV();
        ArrayList<SinhVien>sinhViens = new ArrayList<>();
        while (cursor.moveToNext()){
            String ma = cursor.getString(0);
            String id = cursor.getString(1);
            String name = cursor.getString(2);
            String sex = cursor.getString(3);
            String job = cursor.getString(4);
            String birhday = cursor.getString(5);
            SinhVien sinhVien = new SinhVien(ma,id,name,sex,job,birhday);
            sinhViens.add(sinhVien);
        }
        return  sinhViens;
    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
    }
    public void deletesv(String ma){
        db.delete(ma);
        Toast.makeText(this, "Xóa thành công", Toast.LENGTH_SHORT).show();
        init();
    }
}

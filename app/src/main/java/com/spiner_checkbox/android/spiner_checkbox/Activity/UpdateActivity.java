package com.spiner_checkbox.android.spiner_checkbox.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.spiner_checkbox.android.spiner_checkbox.Database.SQLiteHanderl;
import com.spiner_checkbox.android.spiner_checkbox.Model.SinhVien;
import com.spiner_checkbox.android.spiner_checkbox.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {
    EditText edIdupdate,edNameupdate,edbrithdayupdate;
    Spinner spjobupdate;
    RadioButton rdnamupdate,rdnuupdate;
    Button bntupdate;
    SQLiteHanderl db;
    String job = "";
    String checkjox = "";
    RadioGroup rdgrupupdate;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
    String ma = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db = new SQLiteHanderl(getApplicationContext());
        init();
        Getinfo();
        update();
    }
    private void init() {
        edIdupdate = (EditText) findViewById(R.id.edIdupdate);
        edNameupdate = (EditText) findViewById(R.id.edNameupdate);
        edbrithdayupdate = (EditText) findViewById(R.id.edbrithdayupdate);
        spjobupdate = (Spinner) findViewById(R.id.spjobupdate);
        rdnamupdate = (RadioButton) findViewById(R.id.rdnamupdate);
        rdnuupdate = (RadioButton) findViewById(R.id.rdnuupdate);
        bntupdate = (Button) findViewById(R.id.bntupdate);
        rdgrupupdate = (RadioGroup) findViewById(R.id.rdgrupupdate);
    }
    private void Getinfo(){
        SinhVien sinhVien = (SinhVien) getIntent().getSerializableExtra("ttupdate");
        ma = sinhVien.getMa();
        edIdupdate.setText(sinhVien.getId());
        edNameupdate.setText(sinhVien.getName());
        edbrithdayupdate.setText(sinhVien.getBirhday());
        if(sinhVien.getJob().equalsIgnoreCase("Sinh viên")){
            spjobupdate.setSelection(1);
        }else if (sinhVien.getJob().equalsIgnoreCase("Giảng viên")){
            spjobupdate.setSelection(2);
        }else if (sinhVien.getJob().equalsIgnoreCase("NVVP")){
            spjobupdate.setSelection(3);
        }
        else if (sinhVien.getJob().equalsIgnoreCase("Xây dựng")){
            spjobupdate.setSelection(4);
        }
        else if (sinhVien.getJob().equalsIgnoreCase("Khác")){
            spjobupdate.setSelection(5);
        }else if (sinhVien.getJob().equals("")){
            spjobupdate.setSelection(0);
        }
        if (sinhVien.getSex().equalsIgnoreCase("Nam")){
            rdnamupdate.setChecked(true);
        }else {
            rdnuupdate.setChecked(true);
        }
    }
    private void update(){
        spjobupdate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        job = "";
                        break;
                    case 1:
                        job = "Sinh viên";
                        break;
                    case 2:
                        job = "Giảng viên";
                        break;
                    case 3:
                        job = "NVVP";
                        break;
                    case 4:
                        job = "Xây dựng";
                        break;
                    case 5:
                        job = "Khác";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        rdnamupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkjox = "Nam";
            }
        });
        rdnuupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkjox = "Nữ";
            }
        });
        edbrithdayupdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){

                    final DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            calendar.set(Calendar.YEAR,year);
                            calendar.set(Calendar.MONTH,month);
                            calendar.set(Calendar.DAY_OF_MONTH,day);
                            edbrithdayupdate.setText(simpleDateFormat1.format(calendar.getTime()));
                        }
                    };
                    DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateActivity.this,
                            callback,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();
                }
            }
        });
        bntupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String id = edIdupdate.getText().toString();
                 String name = edNameupdate.getText().toString();
                String brithday = edbrithdayupdate.getText().toString();
                boolean kt = true;
                if (id.equals("")){
                    edIdupdate.setError("Vui lòng nhập id");
                    edIdupdate.requestFocus();
                    kt = false;
                }
                if (name.equals("")){
                    edNameupdate.setError("Vui lòng nhập name");
                    edNameupdate.requestFocus();
                    kt = false;
                }
                if (brithday.equals("")){
                    edbrithdayupdate.setError("Vui lòng nhập brithday");
                    edbrithdayupdate.requestFocus();
                    kt = false;
                }
                if (rdnamupdate.isChecked()){
                    checkjox = "Nam";
                }
                if (rdnuupdate.isChecked()){
                    checkjox = "Nữ";
                }
                if (kt == true) {
                    db.updateDataList(ma, id, name, checkjox, job, brithday);
                    Toast.makeText(UpdateActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    edIdupdate.setText("");
                    edNameupdate.setText("");
                    edbrithdayupdate.setText("");
                    spjobupdate.setSelection(0);
                    if (rdnamupdate.isChecked()) {
                        rdnamupdate.setChecked(false);
                    } else {
                        rdnuupdate.setChecked(false);
                    }
                    Intent intent = new Intent(UpdateActivity.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }
}

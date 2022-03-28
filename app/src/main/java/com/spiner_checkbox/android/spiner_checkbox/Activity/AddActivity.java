package com.spiner_checkbox.android.spiner_checkbox.Activity;

import android.app.DatePickerDialog;
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
import com.spiner_checkbox.android.spiner_checkbox.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    EditText edId,edName,edbrithday;
    Spinner spjob;
    RadioButton rdnam,rdnu;
    Button bntadd;
    SQLiteHanderl db;
    String job = "";
    String checkjox = "";
    RadioGroup rdgrup;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        db = new SQLiteHanderl(getApplicationContext());
        init();
        them();
    }

    private void init() {
        edId = (EditText) findViewById(R.id.edId);
        edName = (EditText) findViewById(R.id.edName);
        edbrithday = (EditText) findViewById(R.id.edbrithday);
        spjob = (Spinner) findViewById(R.id.spjob);
        rdnam = (RadioButton) findViewById(R.id.rdnam);
        rdnu = (RadioButton) findViewById(R.id.rdnu);
        bntadd = (Button) findViewById(R.id.bntadd);
        rdgrup = (RadioGroup) findViewById(R.id.rdgrup);
    }
    private void them(){
        spjob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        job = " ";
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
        edbrithday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    final DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                            calendar.set(Calendar.YEAR,year);
                            calendar.set(Calendar.MONTH,month);
                            calendar.set(Calendar.DAY_OF_MONTH,day);
                            edbrithday.setText(simpleDateFormat1.format(calendar.getTime()));
                        }
                    };
                    DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this,
                            callback,
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH));
                    datePickerDialog.show();
                }
            }
        });
        bntadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edId.getText().toString();
                String name = edName.getText().toString();
                String brithday = edbrithday.getText().toString();
                boolean kt = true;
                if (id.equals("")){
                    edId.setError("Vui lòng nhập id");
                    edId.requestFocus();
                    kt = false;
                }
                if (name.equals("")){
                    edName.setError("Vui lòng nhập name");
                    edName.requestFocus();
                    kt = false;
                }
                if (brithday.equals("")){
                    edbrithday.setError("Vui lòng nhập brithday");
                    edbrithday.requestFocus();
                    kt = false;
                }
                if (kt == true){
                    db.themsv(id,name,checkjox,job,brithday);
                    Toast.makeText(AddActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    edId.setText("");
                    edName.setText("");
                    edbrithday.setText("");
                    spjob.setSelection(0);
                    if (rdnam.isChecked()){
                        rdnam.setChecked(false);
                    }else {
                        rdnu.setChecked(false);
                    }
                }
            }
        });
    }
    public void RadioButtonClicked(View view) {
        boolean check = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rdnam:
                if (check) {
                    checkjox = "Nam";
                }
                break;
            case R.id.rdnu:
                if (check) {
                    checkjox = "Nữ";
                }
                break;
        }
    }
}

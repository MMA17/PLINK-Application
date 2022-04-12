package com.example.plink;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditUserActivity extends AppCompatActivity {
    private Spinner spinnerPosition;
    private TextView txtPositon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        txtPositon = findViewById(R.id.txtPosition);
        spinnerPosition = findViewById(R.id.spinner_position);
        String [] position = {"Giáo viên","Sinh viên"};
        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        this.spinnerPosition.setAdapter(adapter);

        spinnerPosition.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Adapter a = adapterView.getAdapter();
                String res = (String) a.getItem(i);
                txtPositon.setText("Chức danh\n\t\t"+res);
                Toast.makeText(getApplicationContext(),"Chức vụ:"+res,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package com.example.serviceswe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mBtnSave;
    private EditText mEtName;
    private BroadcastReceiver serviceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,intent.getStringExtra("data"),Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();
        IntentFilter intentFilter = new IntentFilter("com.xyz.service");
        registerReceiver(serviceReceiver,intentFilter);

    }

    private void initviews() {
    mBtnSave = findViewById(R.id.btnSave);
    mEtName = findViewById(R.id.etName);
    mBtnSave.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,FileOperationService.class);
            intent.putExtra("name",mEtName.getText().toString());
            startService(intent);

        }
    });
    }
}
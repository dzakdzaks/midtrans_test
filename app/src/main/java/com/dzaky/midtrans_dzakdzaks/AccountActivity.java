package com.dzaky.midtrans_dzakdzaks;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dzaky.midtrans_dzakdzaks.widgets.WidgetsTextView;

public class AccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        ((WidgetsTextView) findViewById(R.id.tv_name)).setText(CustomerData.NAME);
        ((WidgetsTextView) findViewById(R.id.tv_email)).setText(CustomerData.EMAIL);
        ((WidgetsTextView) findViewById(R.id.tv_phone)).setText(CustomerData.PHONE);
        ((WidgetsTextView) findViewById(R.id.tv_address)).setText(CustomerData.ADDRESS);
    }
}

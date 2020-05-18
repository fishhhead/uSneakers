package com.example.usneakers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class UserListActivity extends AppCompatActivity {

    TextView tv_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        tv_email = findViewById(R.id.tv_email);
        String emailfromintent = getIntent().getStringExtra("Email");
        tv_email.setText(emailfromintent);
    }
}

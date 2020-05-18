package com.example.usneakers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class SigninActivity extends AppCompatActivity implements View.OnClickListener {
    private final SigninActivity activity = SigninActivity.this;
    private NestedScrollView nestedScrollView;

    private TextInputLayout lay_Email;
    private TextInputLayout lay_Pass;

    private EditText et_Email;
    private EditText et_Pass;

    private AppCompatButton btn_login;
    private AppCompatTextView tv_login_link;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        initview();;
        initListener();
        initObjects();
    }

    private void initview() {
        nestedScrollView = findViewById(R.id.nestedscrillview);

        lay_Email = findViewById(R.id.lay_Email);
        lay_Pass = findViewById(R.id.lay_Pass);

        et_Email = findViewById(R.id.et_Email);
        et_Pass = findViewById(R.id.et_Pass);

        btn_login = findViewById(R.id.btn_login);
        tv_login_link = findViewById(R.id.tv_link_reg);
    }

    private void initListener() {
        btn_login.setOnClickListener(this);
        tv_login_link.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                verifyFromSQLite();
                break;
            case R.id.tv_link_reg:
                startActivity(new Intent(getApplicationContext(), RegistrationActivity.class));
                break;
        }
    }

    private void verifyFromSQLite() {

        if(!inputValidation. isInputEditTextFilled(et_Email, lay_Email, getString(R.string.error_message_email))){
            return;
        }

        if(!inputValidation. isInputEditTextEmail(et_Email, lay_Email, getString(R.string.error_message_email))){
            return;
        }

        if(!inputValidation. isInputEditTextFilled(et_Pass, lay_Pass, getString(R.string.error_message_password))){
            return;
        }

        if(databaseHelper.checkUser(et_Email.getText().toString().trim(), et_Pass.getText().toString().trim())){
            Intent intent = new Intent(activity, MainActivity.class);
            intent.putExtra("Email", et_Email.getText().toString().trim());
            emptyInputEdittext();
            startActivity(intent);
        } else {
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEdittext() {
        et_Email.setText(null);
        et_Pass.setText(null);
    }
}

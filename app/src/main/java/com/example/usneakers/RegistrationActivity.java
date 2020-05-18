package com.example.usneakers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    private final RegistrationActivity activity = RegistrationActivity.this;
    private NestedScrollView nestedScrollView;

    private TextInputLayout lay_Name;
    private TextInputLayout lay_Email;
    private TextInputLayout lay_Pass;
    private TextInputLayout lay_ConPass;

    private EditText et_Name;
    private EditText et_Email;
    private EditText et_Pass;
    private EditText et_ConPass;

    private AppCompatButton btn_register;
    private AppCompatTextView tv_al_register;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initview();;
        initListener();
        initObjects();
    }

    private void initview() {
        nestedScrollView = findViewById(R.id.nestedscrillview);

        lay_Name = findViewById(R.id.lay_Name);
        lay_Email = findViewById(R.id.lay_Email);
        lay_Pass = findViewById(R.id.lay_Pass);
        lay_ConPass = findViewById(R.id.lay_ConPass);

        et_Name = findViewById(R.id.et_Name);
        et_Email = findViewById(R.id.et_Email);
        et_Pass = findViewById(R.id.et_Pass);
        et_ConPass = findViewById(R.id.et_ConPass);

        btn_register = findViewById(R.id.btn_register);
        tv_al_register = findViewById(R.id.tv_al_register);
    }

    private void initListener() {
        btn_register.setOnClickListener(this);
        tv_al_register.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_register:
                PostDataSqlite();
                break;
            case R.id.tv_al_register:
                finish();
                break;
        }
    }

    private void PostDataSqlite() {
        if(!inputValidation.isInputEditTextFilled(et_Name, lay_Name, getString(R.string.error_message_name))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(et_Email, lay_Email, getString(R.string.error_message_email))){
            return;
        }
        if(!inputValidation.isInputEditTextEmail(et_Email, lay_Email, getString(R.string.error_message_email))){
            return;
        }
        if(!inputValidation.isInputEditTextFilled(et_Pass, lay_Pass, getString(R.string.error_message_password))){
            return;
        }
        if(!inputValidation.isInputEditTextMatches(et_Pass, et_ConPass, lay_ConPass, getString(R.string.error_password_not_match))){
            return;
        }

        if(!databaseHelper.checkUser(et_Email.getText().toString().trim())){
            user.setName(et_Name.getText().toString().trim());
            user.setEmail(et_Email.getText().toString().trim());
            user.setPass(et_Pass.getText().toString().trim());
            databaseHelper.addUser(user);

            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEdittext();
        } else {
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEdittext() {
        et_Name.setText(null);
        et_Email.setText(null);
        et_Pass.setText(null);
        et_ConPass.setText(null);
    }
}

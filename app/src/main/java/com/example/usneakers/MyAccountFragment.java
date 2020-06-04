package com.example.usneakers;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

public class MyAccountFragment extends Fragment {

    /*initial views*/
    private View view;
    private DatabaseHelper databaseHelper;
    private User user;
    private TextView userEmailtv;
    private TextView userNametv;

    private TextInputLayout lay_newName;
    private EditText et_newName;

    private Button upDate;
    private Button logOut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_account, container, false);

        /*initial  database*/
        databaseHelper = new DatabaseHelper(view.getContext());

        /*receive user email after they sign in */
        Bundle extras = getActivity().getIntent().getExtras();
        assert extras != null;
        final String userEmail = extras.getString("userEmail");

        /*bind view and display user email and name which retrieved from db*/
        userEmailtv = view.findViewById(R.id.userEmailtv);
        user = new User();
        user = databaseHelper.getUser(userEmail);
        userNametv=view.findViewById(R.id.userNametv);

        userEmailtv.setText("(" + userEmail + ")");
        userNametv.setText("Hello, " + user.getName());

        initTextandButtonviews();

        /*update button passes new user name to database helper*/
        upDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = et_newName.getText().toString().trim();

                if(value.isEmpty()){
                    lay_newName.setError(getString(R.string.error_message_name));
                }else {
                    lay_newName.setErrorEnabled(false);

                    user = databaseHelper.upDateUserName(et_newName.getText().toString().trim(), userEmail);
                    et_newName.setText(null);
                    Toast.makeText(getContext(),getString(R.string.success_update_user_name), Toast.LENGTH_LONG).show();
                }
            }
        });

        /*logout button returns back to sign in acticity*/
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(getActivity(), SigninActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initTextandButtonviews(){
        lay_newName = view.findViewById(R.id.up_name_lay);
        et_newName = view.findViewById(R.id.up_name_et);

        upDate = view.findViewById(R.id.update);
        logOut = view.findViewById(R.id.logout);
    }




}

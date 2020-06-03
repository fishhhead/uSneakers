package com.example.usneakers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyAccountFragment extends Fragment {

    View view;
    DatabaseHelper databaseHelper;
    User user;
    TextView userEmailtv;
    TextView userNametv;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_account, container, false);


        databaseHelper = new DatabaseHelper(view.getContext());

        Bundle extras = getActivity().getIntent().getExtras();
        assert extras != null;
        String userEmail = extras.getString("userEmail");
        userEmailtv = view.findViewById(R.id.userEmailtv);
        userEmailtv.setText("Hello, " + userEmail);
        System.out.println(userEmail);


        user = new User();
        user = databaseHelper.getUser(userEmail);
        userNametv=view.findViewById(R.id.userNametv);
        userNametv.setText(user.getName());

        System.out.println(user.getName());


        return view;
    }
}

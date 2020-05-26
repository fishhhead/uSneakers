package com.example.usneakers.verification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.usneakers.R;

public class VerificationFragment extends Fragment  {
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.fragment_verification, container, false);

        googleMapFragment supportMapFragment = new googleMapFragment();
        FragmentTransaction fm = getChildFragmentManager().beginTransaction();
        fm.replace(R.id.map_fragment, supportMapFragment).commit();

        return view;
    }
}

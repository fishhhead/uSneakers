package com.example.usneakers.catalogue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.usneakers.R;


public class catalogueCouponFragment extends Fragment {

    public catalogueCouponFragment() {
        // Required empty public constructor
    }
    TextView SneakerName;
    ImageView SneakerPic;
    View view;

    String resultName;
    int resultImg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_catalogue_coupon, container, false);

        Bundle bundle = this.getArguments();

        if(bundle != null) {
            resultImg = bundle.getInt("bundleKeyImage");
            resultName = bundle.getString("bundleKeyItemName");
        }
        else
        {
            Toast.makeText(getContext(), resultImg + resultName, Toast.LENGTH_LONG).show();
        }

        SneakerName = view.findViewById(R.id.SneakerName);
        SneakerName.setText(resultName);
        SneakerPic = view.findViewById(R.id.SneakerPic);
        SneakerPic.setImageResource(resultImg);

        return view;
    }
}

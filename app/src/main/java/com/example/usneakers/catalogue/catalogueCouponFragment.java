package com.example.usneakers.catalogue;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.usneakers.R;

import java.util.Random;


public class catalogueCouponFragment extends Fragment {

    public catalogueCouponFragment() {
        // Required empty public constructor
    }
    TextView SneakerName;
    ImageView SneakerPic;
    View view;
    String resultName;
    int resultImg;

    Button button;
    TextView coupon;
    ImageButton imageButtonBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_catalogue_coupon, container, false);

        final Bundle bundle = this.getArguments();

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

        final Random myRandom = new Random();

        button = view.findViewById(R.id.couponButton);
        coupon = view.findViewById(R.id.coupon);



        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                    coupon.setText(String.valueOf(myRandom.nextInt(1000000000)));
                    Toast.makeText(getContext(),"You can only generate one time coupon", Toast.LENGTH_LONG).show();
                    button.setEnabled(false);
            }
        });

        imageButtonBack = view.findViewById(R.id.Back);
        imageButtonBack.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Fragment fragment = new CatalogueFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }


}

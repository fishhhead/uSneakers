package com.example.usneakers.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.usneakers.MyAccountFragment;
import com.example.usneakers.R;
import com.example.usneakers.catalogue.CatalogueFragment;
import com.example.usneakers.verification.VerificationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        String a = i.getStringExtra("userEmail");

        Bundle bundle = new Bundle();
        MyAccountFragment fragment = new MyAccountFragment();
        bundle.putString("userEmail", a);
        fragment.setArguments(bundle);


        //create bottom navigation view
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //set up Home fragment as the main page
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_catalogue:
                            selectedFragment = new CatalogueFragment();
                            break;
                        case R.id.nav_verification:
                            selectedFragment = new VerificationFragment();
                            break;
                        case R.id.nav_account:
                            selectedFragment = new MyAccountFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

                    return true;
                }
    };
}

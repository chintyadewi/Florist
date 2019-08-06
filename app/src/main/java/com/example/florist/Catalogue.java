package com.example.florist;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.florist.generator.ServiceGenerator;
import com.example.florist.services.FlowerService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Catalogue extends AppCompatActivity {

    TextView txt;
    private FlowerService flowerService;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.navigation_basket:
                    loadFragment(new BasketFragment());
                    return true;
                case R.id.navigation_account:
                    loadFragment(new AccountFragment());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        flowerService= ServiceGenerator.createService(FlowerService.class);
        loadFragment(new HomeFragment());
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }
}

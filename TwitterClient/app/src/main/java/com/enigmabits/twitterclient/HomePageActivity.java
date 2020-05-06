package com.enigmabits.twitterclient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePageActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
   TextView username;
   String userid;
   Fragment fragment;
   FragmentManager manager;

   FragmentTransaction transaction;

   BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();
     //   username = findViewById(R.id.username);

        bottomNavigationView = findViewById(R.id.btmnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        fragment = new HomeFragment();
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        transaction.add(R.id.fragmentcontainer,fragment);

        transaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


        switch (menuItem.getItemId())
        {
            case R.id.homeoption:
            {
                fragment = new HomeFragment();
                manager = getSupportFragmentManager();
                transaction = manager.beginTransaction();

                transaction.replace(R.id.fragmentcontainer,fragment);

                transaction.commit();

                break;
            }

            case  R.id.searchoption:
            {
                fragment = new SearchFragment();
                manager = getSupportFragmentManager();
                transaction = manager.beginTransaction();

                transaction.replace(R.id.fragmentcontainer,fragment);

                transaction.commit();

                break;

            }
            case  R.id.mytweetoption:
            {
                fragment = new UserTimeLineFragment();
                manager = getSupportFragmentManager();
                transaction = manager.beginTransaction();

                transaction.replace(R.id.fragmentcontainer,fragment);

                transaction.commit();

                break;

            }

            case R.id.sendoption:
            {
                fragment = new ComposeFragment();
                manager = getSupportFragmentManager();
                transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentcontainer,fragment);
                transaction.commit();

                break;
            }
        }


        return true;
    }


}

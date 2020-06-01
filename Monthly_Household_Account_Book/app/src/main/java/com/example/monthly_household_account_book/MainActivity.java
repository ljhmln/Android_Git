package com.example.monthly_household_account_book;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Money moneyPage;
    private Chart chartPage;
    private Saving savingPage;
    private Calendar calendarPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("달마다 가계부");

        bottomNavigationView = findViewById(R.id.bottomNavigationView4);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.money:
                        setFrag(0);
                        break;
                    case R.id.chart:
                        setFrag(1);
                        break;
                    case R.id.saving:
                        setFrag(2);
                        break;
                    case R.id.calendar:
                        setFrag(3);
                        break;
                }

                return true;
            }
        });

        moneyPage = new Money();
        chartPage = new Chart();
        savingPage = new Saving();
        calendarPage = new Calendar();
        setFrag(0);
    }

    private void setFrag(int n){

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (n){
            case 0:
                fragmentTransaction.replace(R.id.main_frame, moneyPage);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentTransaction.replace(R.id.main_frame, chartPage);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentTransaction.replace(R.id.main_frame, savingPage);
                fragmentTransaction.commit();
                break;
            case 3:
                fragmentTransaction.replace(R.id.main_frame, calendarPage);
                fragmentTransaction.commit();
                break;
        }
    }
}

package com.example.topnavigation_20200517;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView; // 탑 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private frag1 frag1;
    private frag2 frag2;
    private frag3 frag3;
    private frag1Thread frag1Thread;
    TextView Advertising;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       Advertising = findViewById(R.id.advertising);



        navigationView = findViewById(R.id.topNavi);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                        switch (menuItem.getItemId()){
                            case R.id.action_sun:
                                frag1Thread thread = new frag1Thread();
                                thread.start();
                                setFrag(0);
                                break;
                            case R.id.action_map:
                                setFrag(1);
                                break;
                    case R.id.action_web:
                        setFrag(2);
                        break;
                }
                return true;
            }
        });
        frag1 = new frag1();
        frag2 = new frag2();
        frag3 = new frag3();

        setFrag(0);



    }
        //프래그 교체되는 실행문
    private void setFrag(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n){
            case 0:
                ft.replace(R.id.frame, frag1);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.frame, frag2);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.frame, frag3);
                ft.commit();
                break;

        }

    }


}

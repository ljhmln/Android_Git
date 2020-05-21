package com.example.topnavigation_20200517;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class MainActivity extends AppCompatActivity {

    private BackPressCloseHandler backPressCloseHandler;


    private BottomNavigationView navigationView; // 탑 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private frag1 frag1;
    private frag2 frag2;
    private frag3 frag3;

    TextView Advertising;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("드론날다 :3");

       Advertising = findViewById(R.id.advertising);
       backPressCloseHandler = new BackPressCloseHandler(this);



        navigationView = findViewById(R.id.topNavi);
        navigationView.setItemIconTintList(null);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                        switch (menuItem.getItemId()){
                            case R.id.action_sun:
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


    @Override
    public void onBackPressed() {
       backPressCloseHandler.onBackPressed();
    }
}

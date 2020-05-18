package com.example.topnavigation_20200517;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class frag1 extends Fragment  {

    private View view;
    String result;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragsun,container,false);

         TextView  Advertising = view.findViewById(R.id.advertising);
        frag1Thread thread = new frag1Thread();
        thread.start();




         Advertising.setText("샬랄랄라");



        return view;
    }



    public class frag1Thread extends Thread {



        String apiUrl = "https://spaceweather.rra.go.kr/api/kindex";

        TextView Advertising ;

        private Handler handler;

//    public frag1Thread(TextView tt){
//        this.Advertising = tt;
//    }

        @Override
        public void run() {

            try {
                URL url = new URL(apiUrl);

                BufferedReader br;

                br =  new BufferedReader(new InputStreamReader(url.openStream()));
                result = "";
                String line;
                while ((line = br.readLine())!= null){
                    result += line;
                }
                    System.out.println(result);









            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

}


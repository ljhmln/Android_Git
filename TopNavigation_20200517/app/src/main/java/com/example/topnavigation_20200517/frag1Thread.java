package com.example.topnavigation_20200517;

import android.os.Handler;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class frag1Thread extends Thread {



    String apiUrl = "http://spaceweather.rra.go.kr/api/kindex";
    private String result;

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

package com.example.topnavigation_20200517;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;


public class frag1 extends Fragment {

   public int kind = 0;

    public String nx = "57";
    public String ny = "74";



    Date date  = new Date();

//    SimpleDateFormat getDate;
//    SimpleDateFormat getHours;

    SimpleDateFormat getDate = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat getHours = new SimpleDateFormat("HH");
    SimpleDateFormat getMinute = new SimpleDateFormat("mm");

//    public String nowDate;
//    public String nowTime;

    public String nowDate = getDate.format(date);
    public String nowTime = getHours.format(date);


//    nowDate = getDate.format(new Date());
//    nowTime = getHours.format(new Date());

    private View view;

    TextView Advertising;
    TextView currentP;



//    String weatherUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getUltraSrtFcst?"
//            +"serviceKey=X7eiuVFqij%2BqvTtwYaOUx4SfsrsSjGIDU8Mjgf0AB%2Bdd0sYqpbhrZK5LEqSc7ufTDFrGu8tm8j5iHkd2z3M%2FFg%3D%3D"
//            +"&pageNo=1&numOfRows=240&dataType=JSON"
//            +"&base_date="+nowDate+"&base_time="+nowTime+"&nx="+nx+"&ny="+ny+"&";



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragsun, container, false);

        Advertising = view.findViewById(R.id.advertising);
        currentP = view.findViewById(R.id.currentP);
        frag1Thread thread = new frag1Thread();
        thread.start();



        return view;
    }
//06시30분 발표(30분 단위)
//- 매시각 45분 이후 호출
//    6-44 5-30/6시 45
//    분 이상  >6시 30분













    //자기장 api
        class frag1Thread extends Thread {


//        String apiUrl = "https://spaceweather.rra.go.kr/api/kindex";

//        String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getUltraSrtFcst?"
//                +"serviceKey=X7eiuVFqij%2BqvTtwYaOUx4SfsrsSjGIDU8Mjgf0AB%2Bdd0sYqpbhrZK5LEqSc7ufTDFrGu8tm8j5iHkd2z3M%2FFg%3D%3D"
//                +"&pageNo=1&numOfRows=240&dataType=JSON"
//                +"&base_date="+nowDate+"&base_time="+nowTime+"&nx="+nx+"&ny="+ny+"&";
                    @Override
                    public void run() {
//                        SimpleDateFormat getDate = new SimpleDateFormat("yyyyMMdd");
//                        SimpleDateFormat getHours = new SimpleDateFormat("HHmm");
//
//                        nowDate = getDate.format(new Date());
//                        nowTime = getHours.format(new Date());

                        System.out.println(nowDate);
                        System.out.println(nowTime);
//
//                        try {
//                    URL url = new URL(apiUrl);
//                    BufferedReader br;
//
//                    br =  new BufferedReader(new InputStreamReader(url.openStream()));
//                    String line;
//                    while ((line = br.readLine())!= null){
//                        result += line;
//                    }
                    getJson(1);
                    getJson(2);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }


            }
        }





    //자기장 파싱
    private String getJson(int kind){

        String value = "";

            if(kind == 1){
                try{
                    String result = "";
                    String apiUrl = "https://spaceweather.rra.go.kr/api/kindex";
                    URL url = new URL(apiUrl);
                    BufferedReader br;

                    br =  new BufferedReader(new InputStreamReader(url.openStream()));
                    String line;
                    while ((line = br.readLine())!= null){
                        result += line;
                    }

                    JSONObject jsonObject1 = new JSONObject(result);
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("kindex");
                    int cu = jsonObject2.getInt("currentP");
                    value = String.valueOf(cu);
                    System.out.println("cu" + value);


                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    currentP.setText(value + "kp");
                }

            }else if(kind == 2){
                try {
                    String result = "";
                    String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getUltraSrtFcst?"
                            +"serviceKey=X7eiuVFqij%2BqvTtwYaOUx4SfsrsSjGIDU8Mjgf0AB%2Bdd0sYqpbhrZK5LEqSc7ufTDFrGu8tm8j5iHkd2z3M%2FFg%3D%3D"
                            +"&pageNo=1&numOfRows=240&dataType=JSON"
                            +"&base_date="+nowDate+"&base_time="+nowTime+"&nx="+nx+"&ny="+ny+"&";
                    URL url = new URL(apiUrl);
                    BufferedReader br;

                    br =  new BufferedReader(new InputStreamReader(url.openStream()));
                    String line;
                    while ((line = br.readLine())!= null){
                        result += line;
                    }

                    JSONObject jsonObject1  = new JSONObject(result);
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("response");
                    JSONObject jsonObject3 = jsonObject2.getJSONObject("body");
                    JSONObject jsonObject4 = jsonObject3.getJSONObject("items");
                    JSONArray jsonArray = jsonObject4.getJSONArray("item");

                    for(int i = 0; i<jsonArray.length(); i++){
                        JSONObject weatherJson = jsonArray.getJSONObject(i);
                        String baseDate = weatherJson.getString("baseDate"); // 발표일자
                        String baseTime = weatherJson.getString("baseTime"); //발표시각
                        String category = weatherJson.getString("category"); // 자료구분코드
                        String fcstDate = weatherJson.getString("fcstDate"); //예측일자
                        String fcstTime = weatherJson.getString("fcstTime"); //예측시간
                        String fcstValue = weatherJson.getString("fcstValue"); //예보 값
                        String nxJson = weatherJson.getString("nx");
                        String nyJson = weatherJson.getString("ny");

                        value += "baseDate : " + baseDate + "\nbaseTime : "+baseTime+
                        "\ncategory : " + category + "\nfcstDate : " + fcstDate +
                        "\nfcstTime : " + fcstTime + "\nfcstValue : " + fcstValue +
                         "\nnxJson : " + nxJson + "\nnyJson : " + nyJson;

                        System.out.println(value);

                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }finally {

                }
            }



        return value;
    }

}


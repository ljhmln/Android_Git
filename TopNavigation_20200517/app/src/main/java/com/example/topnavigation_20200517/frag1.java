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
    SimpleDateFormat getDate = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat getTime = new SimpleDateFormat("kkmm");
    SimpleDateFormat getHours = new SimpleDateFormat("kk");
    SimpleDateFormat  getMinute = new SimpleDateFormat("mm");

   public int kind = 0;

    public String nx = "57";
    public String ny = "74";
    double wind = 0;

    int[] time = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};

    Date date  = new Date();
    private String nowDate;
    private String nowTime;
    private String nowHours;
    private String nowMinute;
    private int nowHoursInt;
    private int nowMinuteInt;

    private View view;
    TextView temperaturesText;
    TextView wind_speedText;
    TextView Advertising;
    TextView currentP;
    TextView humidityText;
    private boolean pty = true;

    String skyStr;
    String wind_speed;
    String temperatures;
    String humidity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragsun, container, false);

        humidityText = view.findViewById(R.id.humidity);
        temperaturesText = view.findViewById(R.id.temperaturesText);
        wind_speedText = view.findViewById(R.id.wind_speed);
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













    //자기장 , 날씨 api 불러오기
        class frag1Thread extends Thread {








                    @Override
                    public void run() {
        nowDate = getDate.format(date);
        nowTime = getTime.format(date);

        nowHours = getHours.format(new Date());
        nowMinute = getMinute.format(new Date());

        nowHoursInt  = Integer.parseInt(nowHours);
        nowMinuteInt  = Integer.parseInt(nowMinute);
        getTime();

        getJson(1);
        getJson(2);

            }
        }

    public void getTime(){

        for(int i = 0; i<= time.length; i++){
            if(nowHoursInt<= time[i]){
                if(nowHoursInt == time[i]){
                    if(nowMinuteInt <= 45){
                        nowTime = (Integer.toString(time[i-1])) + "30";
                        getJson(2);
                        System.out.println("0 ~ 45분 : " + nowTime);
                        if(nowMinuteInt == 45) {
                            nowTime = (Integer.toString(time[i]) +"30");
                        }
                        break;
                    }else{
                    //46분~
                        nowTime = (Integer.toString(time[i])+"30");
                        getJson(2);
                        System.out.println("46분 ~ : " + nowTime);
                        break;
                     }
            }else {
                    nowTime = (Integer.toString(time[i]) + "30");
                    getJson(2);
                    System.out.println("46분 ~ : " + nowTime);
                    break;
                }
            }
        }

    }



    //자기장 파싱
    private String getJson(int kind){




        String value = "";

            if(kind == 1){ //자기장
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
                   // System.out.println("cu" + value);


                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    currentP.setText(value + "kp");
                }

            }else if(kind == 2){ //날씨

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

                    for(int i = 0; i<jsonArray.length(); i++) {
                        JSONObject weatherJson = jsonArray.getJSONObject(i);
//                        String baseDate = weatherJson.getString("baseDate"); // 발표일자
//                        String baseTime = weatherJson.getString("baseTime"); //발표시각
                        String category = weatherJson.getString("category"); // 자료구분코드
                        String fcstDate = weatherJson.getString("fcstDate"); //예측일자
                        String fcstTime = weatherJson.getString("fcstTime"); //예측시간
                        String fcstValue = weatherJson.getString("fcstValue"); //예보 값
                        String nxJson = weatherJson.getString("nx");
                        String nyJson = weatherJson.getString("ny");

                        value += //"baseDate : " + baseDate + "\nbaseTime : " + baseTime +
                                "\ncategory : " + category + "\nfcstDate : " + fcstDate +
                                "\nfcstTime : " + fcstTime + "\nfcstValue : " + fcstValue +
                                "\nnxJson : " + nxJson + "\nnyJson : " + nyJson;

                        if(category.equals("PTY")){ // 비, 눈
                            skyStr = weatherJson.getString("fcstValue");
                            if(fcstValue.equals("0")){
                                continue;
                            }else{
                                fcstValue = fcstValue;
                                category = "PTY";
                                pty = false;

                                if(skyStr.equals("1")) {
                                    skyStr = "비";
                                    System.out.println("1, 비");
                                }else if(skyStr.equals("2")){
                                    skyStr = "비/눈";
                                    System.out.println("2, 비/눈");
                                }else if(skyStr.equals("3")){
                                    skyStr = "눈";
                                    System.out.println("3, 눈");
                                }else if(skyStr.equals("4")){
                                    skyStr = "소나기";
                                    System.out.println("4, 소나기");
                                }
                                continue;
                            }
                        }
                        if(category.equals("SKY")&&pty){ //하늘상태
                            skyStr = weatherJson.getString("fcstValue");
                            category = "SKY";
                            pty = true;

                            if(skyStr.equals("1")){
                                skyStr = "맑음";
                                System.out.println("1, 맑음");
                            }else if(skyStr.equals("3")){
                                skyStr = "구름 많음";
                                System.out.println("3, 구름 많음");
                            }else if(skyStr.equals("4")){
                                skyStr = "흐림";
                                System.out.println("4, 흐림");
                            }
                            continue;
                        }
                            if(category.equals("T1H")){ //기온
                            temperatures = weatherJson.getString("fcstValue");
                            break;
                            }

                            if(category.equals("REH")){ //습도
                            humidity = weatherJson.getString("fcstValue");
                            System.out.println("humidity"+ humidity);
                            break;
                            }

                            if(category.equals("WSD")){ //바람 상태
                                wind_speed = weatherJson.getString("fcstValue");
                                wind = Double.valueOf(wind_speed);
                                System.out.println(wind);

//                                if(wind < 4.0){
//                                    wind_speed = "보통 (바람이 약하다)";
//                                    System.out.println("보통 (바람이 약하다)");
//                                }else if(wind >= 4.0 && wind <9.0) {
//                                    wind_speed = "약간 강 (바람이 약간 강하다)";
//                                    System.out.println("약간 강 (바람이 약간 강하다)");
//                                }else if(wind >= 9.0 && wind < 14.0){
//                                    wind_speed = "강 (바람이 강하다)";
//                                    System.out.println("강 (바람이 강하다)");
//                                }else if(wind >= 14.0){
//                                    wind_speed = "매우 강 (바람이 매우 강하다)";
//                                    System.out.println("매우 강 (바람이 매우 강하다)");
//                                }
                                continue;
                            }
//                        System.out.println(value);
                        System.out.println(wind);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }finally {

                    temperaturesText.setText(temperatures + "˚  |  " + skyStr);
                    wind_speedText.setText(wind_speed);
                    humidityText.setText(humidity);
                }
            }



        return value;
    }

    public String getDate() {
        String strWeather = "";

//        getDate = new SimpleDateFormat("yyyyMMdd");
//        getHours = new SimpleDateFormat("kkmm");
//        getMinute = new SimpleDateFormat("mm");
//
//        nowDate = getDate.format(new Date());
//        nowTime = getHours.format(new Date());
//        nowHours = Integer.parseInt()




        return strWeather;
    }
}


package com.junakiakter.weather_app;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestClass extends AsyncTask<String,Void,String> {
    String result="";
    URL url;
    HttpURLConnection httpURLConnection=null;

    @Override
    protected String doInBackground(String... strings) {

        try {
            url=new URL(strings[0]);
            httpURLConnection= (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
            int  data=inputStreamReader.read();

            while(data !=-1){
                char current= (char) data;
                result +=current;
                data=inputStreamReader.read();

            }
            return  result;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONObject obj= new JSONObject(result);
            JSONObject Databject =new JSONObject(obj.getString("main"));
            Double temperature=Double.parseDouble(Databject.getString("temp"));

            int tempIng=(int) (temperature*1.8-459.67);
            String placename= obj.getString("name");

            MainActivity.place.setText( tempIng);
            MainActivity.temparature.setText(placename);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

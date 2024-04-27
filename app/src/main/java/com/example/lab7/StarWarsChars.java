package com.example.lab7;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class StarWarsChars extends AsyncTask<String, String, String> {
    ArrayList<String[]> fieldList;

    MainActivity.Adapter adapter;

    public StarWarsChars(MainActivity.Adapter adapter){
        this.adapter = adapter;
    }


    @Override
    protected String doInBackground(String... strings) {
        try{
            URL url = new URL("https://swapi-node.vercel.app/api/people");
            URLConnection connection = url.openConnection();
            InputStream input = connection.getInputStream();
            String stream = getStreamIntoString(input);

            Log.i("Stream content", stream); // okay! We got the InputStream into a String
            JSONObject json = new JSONObject(stream);
            fieldList = getJsonFields(json);
            updateList();

        }catch (IOException |JSONException e){
            Log.d("Do in Background Error", e.getMessage());
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        adapter.swFields.add(values[0]);
        adapter.filedList.add(new String[]{values[0], values[1], values[2]});
        adapter.notifyDataSetChanged();
    }

    private void updateList(){
        for(String[] sublist : fieldList){
            String name = sublist[0];
            String height = sublist[1];
            String mass = sublist[2];
            publishProgress(name, height, mass);
        }
    }

    private String getStreamIntoString(InputStream input) throws IOException{
        StringBuilder builder = new StringBuilder();
        int data;
        while((data = input.read()) != -1){
            builder.append((char)data);
        }
        return builder.toString();
    }

    private ArrayList<String[]> getJsonFields(JSONObject json) throws JSONException{
        JSONArray jArray = json.getJSONArray("results");
        ArrayList<String[]> fieldList = new ArrayList<>();

        for(int i = 0; i<jArray.length(); i++){
            json = jArray.getJSONObject(i);
            JSONObject jObj = json.getJSONObject("fields");

            String name = jObj.getString("name");
            String height = jObj.getString("height");
            String mass = jObj.getString("mass");

            fieldList.add(new String[]{name, height, mass});
        }
        return fieldList;
    }
}

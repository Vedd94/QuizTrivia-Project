package com.example.triviaapp.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.triviaapp.controller.AppController;
import com.example.triviaapp.model.Questions;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    String url = "https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements-data.json";
    ArrayList<Questions> questionsArrayList = new ArrayList<>();

    public List<Questions> Internet(final KuchBhii callback){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        Questions questions = new Questions(response.getJSONArray(i).get(0).toString(),response.getJSONArray(i).getBoolean(1));

                        questionsArrayList.add(questions);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if(callback != null) callback.processFinished(questionsArrayList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Hii", "onErrorResponse: Error in loadingg");
            }
        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        return questionsArrayList;
    }
}

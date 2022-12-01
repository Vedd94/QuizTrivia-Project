package com.example.triviaapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.triviaapp.controller.AppController;
import com.example.triviaapp.data.KuchBhii;
import com.example.triviaapp.data.Repository;
import com.example.triviaapp.databinding.ActivityMainBinding;
import com.example.triviaapp.model.Questions;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

   List<Questions> questionsList;
   private ActivityMainBinding binding;
   private int counter = 1;
   private int score = 0;
   private int CA = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

       questionsList = new Repository().Internet(new KuchBhii() {
            @Override
            public void processFinished(ArrayList<Questions> questionsArrayList) {
                binding.correctAnswers.setText("Correct Answers: "+CA+"/"+questionsList.size());
                binding.ScoreCalculator.setText("Your Score: " + score);
                binding.QuestionCounter.setText("Question: " + counter+"/"+questionsList.size());
                binding.QuestionTxt.setText(questionsArrayList.get(counter).getAnswer().toString());
                Log.d("TAG", "onCreate: " + questionsArrayList);
            }
        });

       binding.dsfsdf.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               CheckAnswer(true);
               IncrementCounter();
           }
       });

       binding.button2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               CheckAnswer(false);
               IncrementCounter();
           }
       });

       binding.NEXTQButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               counter = (counter + 1) % questionsList.size();
               IncrementCounter();
               binding.correctAnswers.setText("Correct Answers: "+CA+"/"+questionsList.size());
               binding.QuestionCounter.setText("Question: " + counter+"/"+questionsList.size());
               binding.ScoreCalculator.setText("Your Score: "+ score);
               Toast.makeText(MainActivity.this, "On to the next one...", Toast.LENGTH_SHORT).show();
           }
       });

    }

    private void CheckAnswer(boolean userInput) {
        boolean ss = questionsList.get(counter).isAnswerTrue();
        if(userInput == ss){
            ani2();
            CA = CA + 1;
            score = score + 100;
            Toast.makeText(this, "Very Well done....", Toast.LENGTH_SHORT).show();
        }else{
            score = 0;
            ani();
            Toast.makeText(this, "Nope, you are wrong...", Toast.LENGTH_SHORT).show();
        }
    }

    private void IncrementCounter() {
        binding.QuestionTxt.setText(questionsList.get(counter).getAnswer().toString());
    }

    private void ani2(){
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setRepeatCount(1);
        alphaAnimation.setRepeatMode(Animation.REVERSE);

        binding.cardView.setAnimation(alphaAnimation);

        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.QuestionTxt.setTextColor(Color.GREEN);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.QuestionTxt.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void ani(){
        Animation shake = AnimationUtils.loadAnimation(this,R.anim.set_animation);
        binding.cardView.setAnimation(shake);

        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                binding.QuestionTxt.setTextColor(Color.RED);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.QuestionTxt.setTextColor(Color.WHITE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


}
package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import static com.example.braintrainer.R.id.result_text;

public class MainActivity extends AppCompatActivity {
//declaring the components i require
TextView questions;
Button b1;
Button b2;
Button b3; //answer butons
Button b4;
Button start1; //go button
ArrayList<Integer>answers= new ArrayList<>(); //to store answers
int correct_pos;
TextView result ;
int score=0;
int numberofquestion=0;
TextView score_textview;
TextView timer;
Button play_again_button_2;
ConstraintLayout gamelayout; //layer after the go layer
Boolean clickability; // to stop clicking og button after timer runs out

    @SuppressLint("MissingInflatedId")
    @Override
    // creating elements on creating of process
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questions=findViewById(R.id.Questions);
        b1=findViewById(R.id.button0);
        b2=findViewById(R.id.button1);
        b3=findViewById(R.id.button2);
        b4=findViewById(R.id.button3);
        start1=findViewById(R.id.start);
        result= findViewById(R.id.result_text);
        score_textview=findViewById(R.id.Score);
        timer=findViewById(R.id.Timer);
        play_again_button_2=findViewById(R.id.play_again);

        start1.setVisibility(View.VISIBLE);
        clickability=true;
        gamelayout=findViewById(R.id.Gamelayout);
        gamelayout.setVisibility(View.INVISIBLE);




    }
    //start playing the game, making the go button invisible and running the timerr
    public void play_again_1(View view)
    {
        clickability=true;
        gamelayout.setVisibility(View.VISIBLE);
        play_again_button_2.setVisibility(View.INVISIBLE);
        score=0;
        numberofquestion=0;
        score_textview.setText(Integer.toString(score)+"/"+Integer.toString(numberofquestion));
        new_question();
        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l/1000)+ "s");
            }

            @Override
            public void onFinish() {
                result.setText("done");
                play_again_button_2.setVisibility(View.VISIBLE);
                clickability=false;
            }

        }.start();

    }
    //choosing the answer, setting the asnwers and choosing one
    public void choose_answer(View view)
    {
        if(clickability) {
            if (Integer.toString(correct_pos).equals(view.getTag().toString())) {
                result.setText("you won");
                Log.i("Correct:", "You got it");
                score++;

            } else {
                result.setText("wrong");
                Log.i("wrong:", "better luck newxt time");
            }
            numberofquestion++;
            score_textview.setText(Integer.toString(score) + "/" + Integer.toString(numberofquestion));
            new_question();
        }


    }

//onclicking the go button make the button invisible and play
    public void start(View view)
    {

        start1.setVisibility(View.INVISIBLE);
        play_again_1(b4);
    }
    //generate new question and assign random answers to three buttons and 1 correct answer
    public void new_question()
    {
        Random random= new Random();
        int a=random.nextInt(21);
        int b=random.nextInt(21);
        questions.setText(Integer.toString(a)+" + "+Integer.toString(b));
        correct_pos=random.nextInt(4);
        answers.clear();
        for (int i = 0; i < 4; i++) {
            if(i!=correct_pos)
            {
                int wrongans=random.nextInt(41);
                while(wrongans==a+b)
                {
                    wrongans=random.nextInt(41);
                }
                answers.add(wrongans);
            }
            else{
                answers.add(a+b);
            }
        }
        b1.setText(Integer.toString(answers.get(0)));
        b2.setText(Integer.toString(answers.get(1)));
        b3.setText(Integer.toString(answers.get(2)));
        b4.setText(Integer.toString(answers.get(3)));

    }

}
package com.kunasainath.quiz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int index = 0, score = 0;
    ProgressBar mProgressBar;
    TextView question, mDisplay;
    String result;
    Button mTrue, mFalse;
    private CricketQuiz[] collection = {
            new CricketQuiz(R.string.q1,false),
            new CricketQuiz(R.string.q2,true),
            new CricketQuiz(R.string.q3,true),
            new CricketQuiz(R.string.q4,false),
            new CricketQuiz(R.string.q5,true),
            new CricketQuiz(R.string.q6,true),
            new CricketQuiz(R.string.q7,true),
            new CricketQuiz(R.string.q8,true),
            new CricketQuiz(R.string.q9,false),
            new CricketQuiz(R.string.q10,true)
    };
    final int INCREMENT_BY = 100 / collection.length; //100 is full capacity of progressbar.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = findViewById(R.id.progress);
        mTrue = findViewById(R.id.yes);
        mFalse = findViewById(R.id.no);
        question = findViewById(R.id.question);
        mDisplay = findViewById(R.id.rem);
        mDisplay.setText("Your current score is: "+ score +"");


        question.setText(collection[index].getQuestion());

        mTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluate(true);
                updateQuestion();
                mDisplay.setText("Your current score is: "+ score);
                mProgressBar.incrementProgressBy(INCREMENT_BY);
            }
        });

        mFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluate(false);
                updateQuestion();
                mDisplay.setText("Your current score is: " + score);
                mProgressBar.incrementProgressBy(INCREMENT_BY);
            }
        });
    }

    public void updateQuestion(){
        index = (index+1)%collection.length;
        if(index == 0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setCancelable(false);
            alert.setTitle("Quiz is over");
            alert.setMessage("Your total score: " + score);
            alert.setPositiveButton("Click to exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alert.show();
        }
        question.setText(collection[index].getQuestion());
    }

    public void evaluate(boolean userAnswer){

        if(userAnswer == collection[index].isAnswer()){
            Toast.makeText(this, R.string.correctToast, Toast.LENGTH_SHORT).show();
            score++;
        }else{
            Toast.makeText(this, R.string.wrongToast, Toast.LENGTH_SHORT).show();
        }
    }
}
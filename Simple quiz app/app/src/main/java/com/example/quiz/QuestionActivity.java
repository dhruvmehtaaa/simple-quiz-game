package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView lblQuestion;
    RadioButton optionA;
    RadioButton optionB;
    RadioButton optionC;
    RadioButton optionD;
    Button confirm;
    String rightAnswer;
    String Answer;
    List<Question> questions;
    int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        confirm = findViewById(R.id.confirm);
        lblQuestion = findViewById(R.id.lblPergunta);
        optionA = findViewById(R.id.opcaoA);
        optionB = findViewById(R.id.opcaoB);
        optionC = findViewById(R.id.opcaoC);
        optionD = findViewById(R.id.opcaoD);
        score = 0;
        radioGroup = findViewById(R.id.radioGroup);

        questions = new ArrayList<Question>(){
            {
                add(new Question("What is Euler's number?", "C", "3.1415", "1.7189","2.7182", "5.985"));
                add(new Question("Who said the following sentence: \"Love Your Neighbor As Yourself\"?", "A", "Jesus", "Hitler","Mussolini", "Stalin"));
                add(new Question("Who is the Prime Minister of India?", "D", "Rahul Gandhi", "Arvind Kejriwal","Ram Nath Kovind", "Narendra Modi"));
                add(new Question("Novak Djokovic is a famous player associated with the game of", "B", "Chess", "Lawn Tennis", "Football", "Hockey"));
                add(new Question("What is a Ukulele?", "A", "Musical Instrument", "Food", "Business", "soccer team"));
                add(new Question("In technology, what is A.I?", "D", "Software", "Operating System", "Compiler", "Artificial Intelligence"));
                add(new Question("How much is 8 bits worth?", "C", "1 Bit", "16 Bytes", "1 Byte", "1 Mega Byte"));
                add(new Question("What is Bitcoin?", "B", "Government currency", "Cryptocurrency", "Video Game", "Datamining Software"));
                add(new Question("Who founded Ethereum?", "B", "Satoshi", "Vitalik Buterin", "Arthur Hayes", "Sam Bankman-Fried"));
                add(new Question("Who was the first programmer?", "D", "Steve Jobs", "Linus Torvalds", "Alan Turing", "Ada Lovelace"));
            }
        };

        loadQuestion();
    }


    @Override
    protected void onRestart(){
        super.onRestart();
        loadQuestion();
    }


    private void loadQuestion(){
        if(questions.size() > 0) {
            Question q = questions.remove(0);
            lblQuestion.setText(q.getQuestion());
            List<String> answers = q.getAnswers();

            optionA.setText(answers.get(0));
            optionB.setText(answers.get(1));
            optionC.setText(answers.get(2));
            optionD.setText(answers.get(3));

            rightAnswer = q.getRightAnswer();
        } else {
            Intent intent = new Intent(this, ShowScoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    public void loadAnswer(View view) {
        int op = radioGroup.getCheckedRadioButtonId();

        switch (op){
            case R.id.opcaoA:
                Answer="A";
                break;

            case R.id.opcaoB:
                Answer="B";
                break;

            case R.id.opcaoC:
                Answer="C";
                break;

            case R.id.opcaoD:
                Answer="D";
                break;

            default:
                return;

        }

        radioGroup.clearCheck();

        this.startActivity(isRightOrWrong(Answer));

    }

    private Intent isRightOrWrong(String Answer){
        Intent screen;
        if(Answer.equals(rightAnswer)) {
            this.score += 1;
            screen = new Intent(this, RightActivity.class);

        }else {
            screen = new Intent(this, WrongActivity.class);
        }

        return screen;
    }
}

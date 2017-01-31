package com.example.android.quizforjava;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private String mName;
    private String mGender;
    private String[] mQuiz;
    private String[][] mAnswer;
    private int mCurrentIndex;
    private int[] answerIndex;
    private int[] rightAnswer;
    private TextView mtxQuiz;
    private TextView mtxQuestionNumber;
    private RadioGroup mAnswerGroup;
    private RadioButton mAnswerOne;
    private RadioButton mAnswertwo;
    private RadioButton mAnswerthree;
    private RadioButton mAnswerfour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);
        intitial();
    }

    public void onAnswerChoose(View view) {
        RadioButton radioButton = (RadioButton) view;
        boolean statue = radioButton.isChecked();
        switch (view.getId()) {
            case R.id.rb_anwers_one:
                if (statue) {
                    answerIndex[mCurrentIndex - 1] = 1;
                }
                break;
            case R.id.rb_anwers_two:
                if (statue) {
                    answerIndex[mCurrentIndex - 1] = 2;
                }
                break;
            case R.id.rb_anwers_three:
                if (statue) {
                    answerIndex[mCurrentIndex - 1] = 3;
                }
                break;
            case R.id.rb_anwers_four:
                if (statue) {
                    answerIndex[mCurrentIndex - 1] = 4;
                }
                break;
        }
    }

    public void btn_next(View view) {
        if (mCurrentIndex < 5) {
            mCurrentIndex++;
            setQuizAndAnswer(mCurrentIndex);
            mAnswerGroup.clearCheck();
        } else {
            Toast.makeText(getBaseContext(), "No more Quiz.", Toast.LENGTH_SHORT).show();
        }
    }

    public void btn_submit(View view) {
        int point = 0;
        for (int i = 0; i < answerIndex.length; i++) {
            if (answerIndex[i] == rightAnswer[i]) {
                point++;
            }
        }
        Toast.makeText(getBaseContext(), evaluation(mName, point), Toast.LENGTH_LONG).show();
    }

    public void btn_again(View view) {
        finish();
    }

    public interface NewStart {
        public void beginNewQuiz();
    }

    private String evaluation(String name, int point) {
        String evaMsg = "Hi " + name + ", you get ";
        if (point == 0 || point == 1) {
            evaMsg += point + " right answer. ";
        } else {
            evaMsg += point + " right answers. ";
        }
        ;

        if (point < 4) {
            evaMsg += "You need practice more.";
        } else {
            evaMsg += "Very good.";
        }
        return evaMsg;
    }

    private void intitial() {
        Intent intent = getIntent();
        if (intent != null) {
            mName = intent.getStringExtra(MainActivity.EXTRA_NAME);
            mGender = intent.getStringExtra(MainActivity.EXTRA_GENDER);
        }
        mAnswerGroup = (RadioGroup) findViewById(R.id.answer_group);
        mCurrentIndex = 1;
        answerIndex = new int[]{0, 0, 0, 0, 0};
        rightAnswer = new int[]{1, 2, 2, 2, 2};
        setData();
        initialWidget();
        setQuizAndAnswer(mCurrentIndex);
    }

    private void initialWidget() {
        mtxQuiz = (TextView) findViewById(R.id.tv_question_text);
        mtxQuestionNumber = (TextView) findViewById(R.id.tv_question_number);
        mAnswerOne = (RadioButton) findViewById(R.id.rb_anwers_one);
        mAnswertwo = (RadioButton) findViewById(R.id.rb_anwers_two);
        mAnswerthree = (RadioButton) findViewById(R.id.rb_anwers_three);
        mAnswerfour = (RadioButton) findViewById(R.id.rb_anwers_four);
    }

    private void setData() {
        mQuiz = new String[]{getString(R.string.quiz_one),
                getString(R.string.quiz_two),
                getString(R.string.quiz_three),
                getString(R.string.quiz_four),
                getString(R.string.quiz_five)};
        Resources resources = getResources();
        mAnswer = new String[][]{
                resources.getStringArray(R.array.answers_for_quiz_one),
                resources.getStringArray(R.array.answers_for_quiz_two),
                resources.getStringArray(R.array.answers_for_quiz_three),
                resources.getStringArray(R.array.answers_for_quiz_four),
                resources.getStringArray(R.array.answers_for_quiz_five)
        };
    }

    private void setQuizAndAnswer(int currentIndex) {
        int indexForArray = currentIndex - 1;
        mtxQuiz.setText(mQuiz[indexForArray]);
        mtxQuestionNumber.setText("Question " + mCurrentIndex);
        mAnswerOne.setText(mAnswer[indexForArray][0]);
        mAnswertwo.setText(mAnswer[indexForArray][1]);
        mAnswerthree.setText(mAnswer[indexForArray][2]);
        mAnswerfour.setText(mAnswer[indexForArray][3]);
    }
}

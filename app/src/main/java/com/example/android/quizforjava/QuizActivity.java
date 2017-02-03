package com.example.android.quizforjava;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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
    private CheckBox mCbAnswerone;
    private CheckBox mCbAnswertwo;
    private CheckBox mCbAnswerthree;
    private CheckBox mCbAnswerfour;
    private LinearLayout mCheckBoxGroup;
    private EditText mEtAnswer;
    private final String SAVENAME="name";
    private final String SAVEANSWER="answer";
    private final String SAVEINDEX="index";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);
        initial();
        if (savedInstanceState!=null){
            mName=savedInstanceState.getString(SAVENAME);
            answerIndex=savedInstanceState.getIntArray(SAVEANSWER);
            mCurrentIndex=savedInstanceState.getInt(SAVEINDEX);
        }
        setQuizAndAnswer(mCurrentIndex);
        mEtAnswer.addTextChangedListener(new TextWatcher() {
            String s=null;
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                s=charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (s.equals(getString(R.string.string_ans_for_quiz_seven))){
                    answerIndex[6]=1;
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVENAME,mName);
        outState.putIntArray(SAVEANSWER,answerIndex);
        outState.putInt(SAVEINDEX,mCurrentIndex);
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
        if (mCurrentIndex < 7) {
            mCurrentIndex++;
            setQuizAndAnswer(mCurrentIndex);
            mAnswerGroup.clearCheck();
        } else {
            StaticMethods.showToast(getString(R.string.no_more_quiz),getBaseContext());
        }
    }

    public void btn_submit(View view) {
        int point = 0;
        for (int i = 0; i < answerIndex.length; i++) {
            if (answerIndex[i] == rightAnswer[i]) {
                point++;
            }
        }
        StaticMethods.showToast(StaticMethods.evaluation(mName,point),getBaseContext());
    }

    public void btn_again(View view) {
        finish();
    }

    private void initial() {
        Intent intent = getIntent();
        if (intent != null) {
            mName = intent.getStringExtra(MainActivity.EXTRA_NAME);
            mGender = intent.getStringExtra(MainActivity.EXTRA_GENDER);
        }
        mCurrentIndex = 1;
        answerIndex = new int[]{0, 0, 0, 0, 0,0,0};
        rightAnswer = new int[]{1, 2, 2, 2, 2,1,1};
        setData();
        initialWidget();
    }

    private void initialWidget() {
        mCheckBoxGroup= (LinearLayout) findViewById(R.id.linear_layout_checkbox);
        mAnswerGroup = (RadioGroup) findViewById(R.id.answer_group);
        mtxQuiz = (TextView) findViewById(R.id.tv_question_text);
        mtxQuestionNumber = (TextView) findViewById(R.id.tv_question_number);
        mAnswerOne = (RadioButton) findViewById(R.id.rb_anwers_one);
        mAnswertwo = (RadioButton) findViewById(R.id.rb_anwers_two);
        mAnswerthree = (RadioButton) findViewById(R.id.rb_anwers_three);
        mAnswerfour = (RadioButton) findViewById(R.id.rb_anwers_four);
        mCbAnswerone= (CheckBox) findViewById(R.id.cb_answer_one);
        mCbAnswertwo= (CheckBox) findViewById(R.id.cb_answer_two);
        mCbAnswerthree= (CheckBox) findViewById(R.id.cb_answer_three);
        mCbAnswerfour= (CheckBox) findViewById(R.id.cb_answer_four);
        mEtAnswer= (EditText) findViewById(R.id.et_answer);
    }

    private void setData() {
        mQuiz = new String[]{
                getString(R.string.quiz_one),
                getString(R.string.quiz_two),
                getString(R.string.quiz_three),
                getString(R.string.quiz_four),
                getString(R.string.quiz_five),
                getString(R.string.quiz_six),
                getString(R.string.quiz_seven)};
        Resources resources = getResources();
        mAnswer = new String[][]{
                resources.getStringArray(R.array.answers_for_quiz_one),
                resources.getStringArray(R.array.answers_for_quiz_two),
                resources.getStringArray(R.array.answers_for_quiz_three),
                resources.getStringArray(R.array.answers_for_quiz_four),
                resources.getStringArray(R.array.answers_for_quiz_five),
                resources.getStringArray(R.array.answers_for_quiz_six)
        };
    }

    private void setQuizAndAnswer(int currentIndex) {
        int indexForArray = currentIndex - 1;
        mtxQuiz.setText(mQuiz[indexForArray]);
        mtxQuestionNumber.setText(getString(R.string.no_more_quiz) + mCurrentIndex);
        setOptions(indexForArray);
    }
    private void setOptions(int indexForArray){
        if(indexForArray<5){
            mAnswerGroup.setVisibility(View.VISIBLE);
            mCheckBoxGroup.setVisibility(View.GONE);
            mEtAnswer.setVisibility(View.GONE);
            mAnswerOne.setText(mAnswer[indexForArray][0]);
            mAnswertwo.setText(mAnswer[indexForArray][1]);
            mAnswerthree.setText(mAnswer[indexForArray][2]);
            mAnswerfour.setText(mAnswer[indexForArray][3]);
        }else if(indexForArray==5){
            mAnswerGroup.setVisibility(View.GONE);
            mCheckBoxGroup.setVisibility(View.VISIBLE);
            mEtAnswer.setVisibility(View.GONE);
            mCbAnswerone.setText((mAnswer[indexForArray][0]));
            mCbAnswertwo.setText((mAnswer[indexForArray][1]));
            mCbAnswerthree.setText((mAnswer[indexForArray][2]));
            mCbAnswerfour.setText((mAnswer[indexForArray][3]));
        }else if (indexForArray==6){
            mAnswerGroup.setVisibility(View.GONE);
            mCheckBoxGroup.setVisibility(View.GONE);
            mEtAnswer.setVisibility(View.VISIBLE);
        }
    }

    public void cbOnAnswerChoose(View view) {
        if (mCbAnswerone.isChecked()||mCbAnswertwo.isChecked()||mCbAnswerthree.isChecked()||!mCbAnswerfour.isChecked()){
            answerIndex[5]=1;
        }
    }
}

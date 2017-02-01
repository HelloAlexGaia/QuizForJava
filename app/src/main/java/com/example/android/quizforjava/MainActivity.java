package com.example.android.quizforjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_NAME = "name_extra";
    public static final String EXTRA_GENDER = "gender_extra";
    private String mName = null;
    private String mGender = null;
    private EditText metName;
    private RadioGroup mRbGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initial();
    }

    public void btn_start(View view) {
        if (mName != null && mGender != null) {
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra(EXTRA_NAME, mName);
            intent.putExtra(EXTRA_GENDER, mGender);
            startActivity(intent);
        } else {
            Toast.makeText(getBaseContext(), getString(R.string.enter_information), Toast.LENGTH_SHORT).show();
        }
    }

    private void initial() {
        metName = (EditText) findViewById(R.id.et_name);
        metName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mName = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mRbGender = (RadioGroup) findViewById(R.id.rb_gender_group);
    }

    public void clear(){
        metName.setText("");
        mRbGender.clearCheck();
    }

    @Override
    protected void onPause() {
        super.onPause();
        clear();
        mName=null;
        mGender=null;
    }

    public void rb_gender(View view) {
        RadioButton radioButton = (RadioButton) view;
        boolean statue = radioButton.isChecked();
        switch (view.getId()) {
            case R.id.rb_male:
                if (statue) {
                    mGender = getString(R.string.rb_male);
                }
                break;
            case R.id.rb_female:
                if (statue) {
                    mGender = getString(R.string.rb_male);
                }
                break;
        }

    }

}

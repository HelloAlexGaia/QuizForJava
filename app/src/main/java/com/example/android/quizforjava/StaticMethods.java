package com.example.android.quizforjava;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 张俊秋 on 2017/2/3.
 */

public class StaticMethods {
    private static Toast mToast=null;
    public static String evaluation(String name, int point) {
        String evaMsg = "Hi " + name + ", you get ";
        if (point == 0 || point == 1) {
            evaMsg += point + " right answer. ";
        } else {
            evaMsg += point + " right answers. ";
        }
        ;

        if (point < 4) {
            evaMsg += "You need to practice more.";
        } else {
            evaMsg += "Very good.";
        }
        return evaMsg;
    }
    public static void showToast(String s, Context context){
        if(mToast==null){
            mToast=Toast.makeText(context,s,Toast.LENGTH_SHORT);
        }else{
            mToast.setText(s);
        }
        mToast.show();
    }
}

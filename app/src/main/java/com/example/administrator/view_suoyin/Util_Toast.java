package com.example.administrator.view_suoyin;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 张祺钒
 * on2017/8/14.
 */

public class Util_Toast {
    private static Toast toast;

    public static void showToast(Context context ,String msg){
        if (toast ==null){
            toast=Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.show();
    }

}

package com.bewary.Utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SharedPreferenceHelper {

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void store(Context context, String key, Object value) throws NoSuchMethodException{
        SharedPreferences prefs = initialize(context);
        SharedPreferences.Editor editor = prefs.edit();

        String name = value.getClass().getName();

        Method e = editor.getClass().getMethod("put" + name, value.getClass());
        try {
            e.invoke(editor, key, value);
            editor.apply();
        } catch (IllegalAccessException | InvocationTargetException e1) {
            // won't get this....
        }

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static Object get(Context context, String key, Object datatype) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        SharedPreferences prefs = initialize(context);

        Method e = prefs.getClass().getMethod("get" + datatype.getClass().getName(), datatype.getClass());

        return e.invoke(prefs, key);
    }

    public static SharedPreferences initialize(Context context){
        return context.getSharedPreferences("com.bewary", Context.MODE_PRIVATE);
    }
}

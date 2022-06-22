package com.example.perpusonlinefinal;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private static final String APP_SETTINGS = "APP_SETTINGS";

    private static final String IS_LOGGED_IN = "IS_LOGGED_IN";
    private static final String USER_ID = "USER_ID";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_SETTINGS, Context.MODE_PRIVATE);
    }

    public static boolean isLoggedIn(Context context) {
        return getSharedPreferences(context).getBoolean(IS_LOGGED_IN , false);
    }

    public static int getUserId(Context context) {
        return getSharedPreferences(context).getInt(USER_ID , -1);
    }

    public static void setLoggedInUserData(Context context, int userId) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(IS_LOGGED_IN , true);
        editor.putInt(USER_ID , userId);
        editor.commit();
    }

    public static void setUserLogout(Context context) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(IS_LOGGED_IN , false);
        editor.putInt(USER_ID , -1);
        editor.commit();
    }
}

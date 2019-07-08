package ru.ftc.android.shifttemple.features.login.data;

import android.content.Context;
import android.content.SharedPreferences;

public final class SessionDataSourceImpl implements SessionDataSource {

    private final static String SESSION_PREFERENCES_NAME = "SESSION_PREFERENCES_NAME";
    private final static String SESSION_ID_KEY = "SESSION_ID_KEY";
    private final static String USER_ID = "USER_ID";
    private final static String USER_NAME = "USER_NAME";
    private final static String USER_AGE = "USER_AGE";
    private final static String USER_CITY = "USER_CITY";
    private final static String USER_PIC_URL = "USER_PIC_URL";
    private final static String USER_PHONE = "USER_PHONE";
    private final static String USER_BALANCE = "USER_BALANCE";
    private final static String USER_STATUS = "USER_STATUS";
    private final static String USER_DESCRIPTION = "USER_DESCRIPTION";

    private SharedPreferences sharedPreferences;

    public SessionDataSourceImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(SESSION_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public String getSessionId() {
        return sharedPreferences.getString(SESSION_ID_KEY, "");
    }

    @Override
    public Integer getUserId(){
        return sharedPreferences.getInt(USER_ID, -1);
    }

    @Override
    public String getUserName(){
        return sharedPreferences.getString(USER_NAME, "");
    }

    @Override
    public Integer getUserAge(){
        return sharedPreferences.getInt(USER_AGE, -1);
    }

    @Override
    public String getUserCity(){
        return sharedPreferences.getString(USER_CITY, "");
    }

    @Override
    public String getUserPicUrl(){
        return sharedPreferences.getString(USER_PIC_URL, "");
    }

    @Override
    public String getUserPhone(){
        return sharedPreferences.getString(USER_PHONE, "");
    }

    @Override
    public Integer getUserBalance(){
        return sharedPreferences.getInt(USER_BALANCE, -1);
    }

    @Override
    public Boolean getUserStatus(){
        return sharedPreferences.getBoolean(USER_STATUS, false);
    }

    @Override
    public String getUserDescription(){
        return sharedPreferences.getString(USER_DESCRIPTION, "");
    }

    @Override
    public void setSessionId(String sessionId) {
        sharedPreferences.edit().putString(SESSION_ID_KEY, sessionId).apply();
    }

    @Override
    public void setUserId(Integer id) {
        sharedPreferences.edit().putInt(USER_ID, id).apply();
    }

    @Override
    public void setUserName(String name) {
        sharedPreferences.edit().putString(USER_NAME, name).apply();
    }

    @Override
    public void setUserAge(Integer age){
        sharedPreferences.edit().putInt(USER_AGE, age).apply();
    }

    @Override
    public void setUserCity(String city){
        sharedPreferences.edit().putString(USER_CITY, city).apply();
    }

    @Override
    public void setUserPicUrl(String picUrl){
        sharedPreferences.edit().putString(USER_PIC_URL, picUrl).apply();
    }

    @Override
    public void setUserPhone(String phone){
        sharedPreferences.edit().putString(USER_PHONE, phone).apply();
    }

    @Override
    public void setUserBalance(Integer balance){
        sharedPreferences.edit().putInt(USER_BALANCE, balance).apply();
    }

    @Override
    public void setUserStatus(Boolean status){
        sharedPreferences.edit().putBoolean(USER_STATUS, status).apply();
    }

    @Override
    public void setUserDescription(String description){
        sharedPreferences.edit().putString(USER_DESCRIPTION, description).apply();
    }
}

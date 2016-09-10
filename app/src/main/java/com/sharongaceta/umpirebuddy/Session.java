package com.sharongaceta.umpirebuddy;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

public class Session extends AppCompatActivity {

    private SharedPreferences prefs;

    public Session(){

    }

    public Session(Context cntx){
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setStrike(int strike){
        prefs.edit().putInt("Strike", strike).commit();
    }

    public int getStrike(){
        int storeStrikeCount = prefs.getInt("Strike", 1);
        return storeStrikeCount;
    }

    public void setBall(int ball){
        prefs.edit().putInt("Ball", ball).commit();
    }

    public int getBall(){
        int storeBallCount = prefs.getInt("Ball", 1);
        return storeBallCount;
    }


}

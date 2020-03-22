package site.ishaalim.capungpedia.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;

public class themesPref {

    SharedPreferences mySharedPref;

    public themesPref(Context context) {
        mySharedPref = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
    }

    public Boolean loadNotification(){
        Boolean state = mySharedPref.getBoolean("notification", true);
        return state;
    }

    public void setNightModeState(Boolean state) {
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("NightMode",state);
        editor.commit();
    }
    public void setCurrentModeState(Boolean state) {
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("CurrentMode",state);
        editor.commit();
    }
    public Boolean loadNightModeState (){
        Boolean state = mySharedPref.getBoolean("NightMode",false);
        return  state;
    }

    public Boolean loadCurrentModeState (){
        Boolean state = mySharedPref.getBoolean("CurrentMode",false);
        return  state;
    }
}

package site.ishaalim.capungpedia.SharedPref;

import android.content.Context;
import android.content.SharedPreferences;

public class usersPref {
    SharedPreferences usersPref;

    public usersPref(Context context){
        usersPref = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE);
    }

    public void setUsersLoaded(Boolean state){
        SharedPreferences.Editor editor = usersPref.edit();
        editor.putBoolean("loaded", state);
        editor.commit();
    }

    public Boolean usersLoaded(){
        Boolean state = usersPref.getBoolean("loaded", false);
        return state;
    }

    public void setUserProfile(String nama, String organisasi, String email, String nomer){
        SharedPreferences.Editor editor = usersPref.edit();
        editor.putString("nama", nama);
        editor.putString("organisasi", organisasi);
        editor.putString("email", email);
        editor.putString("nomer", nomer);
        editor.commit();
    }

    public String getUserNama(){
        String nama =  usersPref.getString("nama", "-");
        return  nama;
    }

    public String getUserOrganisasi(){
        String organisasi =  usersPref.getString("organisasi", "-");
        return  organisasi;
    }

    public String getUserEmail(){
        String email =  usersPref.getString("email", "-");
        return  email;
    }

    public String getUserNomer(){
        String nomer =  usersPref.getString("nomer", "-");
        return  nomer;
    }
}

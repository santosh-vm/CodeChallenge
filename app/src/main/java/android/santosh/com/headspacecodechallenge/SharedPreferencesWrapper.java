package android.santosh.com.headspacecodechallenge;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Santosh on 8/13/17.
 */

public class SharedPreferencesWrapper {
    private Context context;
    private SharedPreferences preferences;

    private static String TABLE_DATA = "table_data";

    public SharedPreferencesWrapper(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences(context.getPackageName() + "app_prefs", Context.MODE_PRIVATE);
    }



    private void saveStringValue(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    private String getStringValue(String key) {
        return preferences.getString(key, null);
    }


}

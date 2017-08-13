package android.santosh.com.headspacecodechallenge;

import android.app.Application;
import android.os.Handler;

/**
 * Created by Santosh on 8/13/17.
 */

public class HeadSpaceApplication extends Application {
    private HeadSpaceAPI headSpaceAPI;

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferencesWrapper sharedPreferencesWrapper = new SharedPreferencesWrapper(this);
        HeadSpaceController headSpaceController = new HeadSpaceController(new Handler(),sharedPreferencesWrapper);
        headSpaceAPI = new HeadSpaceAPI(sharedPreferencesWrapper,headSpaceController);
    }

    public HeadSpaceAPI getHeadSpaceAPI() {
        return headSpaceAPI;
    }
}

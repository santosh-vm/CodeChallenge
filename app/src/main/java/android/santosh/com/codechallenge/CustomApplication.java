package android.santosh.com.codechallenge;

import android.app.Application;
import android.os.Handler;

/**
 * Created by Santosh on 8/13/17.
 */

public class CustomApplication extends Application {
    private ApplicationAPI applicationAPI;

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferencesWrapper sharedPreferencesWrapper = new SharedPreferencesWrapper(this);
        ApplicationController applicationController = new ApplicationController(new Handler(),sharedPreferencesWrapper);
        applicationAPI = new ApplicationAPI(sharedPreferencesWrapper, applicationController);
    }

    public ApplicationAPI getApplicationAPI() {
        return applicationAPI;
    }
}

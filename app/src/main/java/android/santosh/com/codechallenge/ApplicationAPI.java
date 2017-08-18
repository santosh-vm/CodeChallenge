package android.santosh.com.codechallenge;

/**
 * Created by Santosh on 8/13/17.
 */

public class ApplicationAPI {
    private SharedPreferencesWrapper sharedPreferencesWrapper;
    private ApplicationController applicationController;

    public ApplicationAPI(SharedPreferencesWrapper sharedPreferencesWrapper, ApplicationController applicationController){
        this.sharedPreferencesWrapper = sharedPreferencesWrapper;
        this.applicationController = applicationController;
    }

    public ApplicationController getApplicationController() {
        return applicationController;
    }

    public SharedPreferencesWrapper getSharedPreferencesWrapper() {
        return sharedPreferencesWrapper;
    }
}

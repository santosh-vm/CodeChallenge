package android.santosh.com.headspacecodechallenge;

/**
 * Created by Santosh on 8/13/17.
 */

public class HeadSpaceAPI {
    private SharedPreferencesWrapper sharedPreferencesWrapper;
    private HeadSpaceController headSpaceController;

    public HeadSpaceAPI(SharedPreferencesWrapper sharedPreferencesWrapper, HeadSpaceController headSpaceController){
        this.sharedPreferencesWrapper = sharedPreferencesWrapper;
        this.headSpaceController = headSpaceController;
    }

    public HeadSpaceController getHeadSpaceController() {
        return headSpaceController;
    }

    public SharedPreferencesWrapper getSharedPreferencesWrapper() {
        return sharedPreferencesWrapper;
    }
}

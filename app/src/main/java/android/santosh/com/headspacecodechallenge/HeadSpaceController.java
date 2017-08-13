package android.santosh.com.headspacecodechallenge;

import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Santosh on 8/13/17.
 */

public class HeadSpaceController {
    private static String TAG = HeadSpaceController.class.getSimpleName();

    private Handler uiHandler;
    private SharedPreferencesWrapper sharedPreferencesWrapper;
    private Gson gson;
    private ExecutorService executorService;

    public HeadSpaceController(Handler uiHandler, SharedPreferencesWrapper sharedPreferencesWrapper){
        this.executorService = Executors.newSingleThreadExecutor();
        this.uiHandler = uiHandler;
        this.gson = new GsonBuilder().create();
        this.sharedPreferencesWrapper = sharedPreferencesWrapper;
    }
}

package android.santosh.com.headspacecodechallenge.activity;

import android.os.Bundle;
import android.santosh.com.headspacecodechallenge.HeadSpaceAPI;
import android.santosh.com.headspacecodechallenge.HeadSpaceApplication;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Santosh on 8/13/17.
 */

public class BaseActivity extends AppCompatActivity {
    protected HeadSpaceAPI headSpaceAPI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        headSpaceAPI = ((HeadSpaceApplication) getApplication()).getHeadSpaceAPI();
    }
}

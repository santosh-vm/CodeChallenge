package android.santosh.com.codechallenge.activity;

import android.os.Bundle;
import android.santosh.com.codechallenge.ApplicationAPI;
import android.santosh.com.codechallenge.CustomApplication;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Santosh on 8/13/17.
 */

public class BaseActivity extends AppCompatActivity {
    protected ApplicationAPI applicationAPI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationAPI = ((CustomApplication) getApplication()).getApplicationAPI();
    }
}

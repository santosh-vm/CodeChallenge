package android.santosh.com.codechallenge.fragment;


import android.os.Bundle;
import android.santosh.com.codechallenge.ApplicationAPI;
import android.santosh.com.codechallenge.CustomApplication;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Santosh on 8/13/17.
 */

public class BaseFragment extends Fragment {
    protected ApplicationAPI applicationAPI;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        applicationAPI = ((CustomApplication) getActivity().getApplication()).getApplicationAPI();
    }
}

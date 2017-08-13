package android.santosh.com.headspacecodechallenge.fragment;


import android.os.Bundle;
import android.santosh.com.headspacecodechallenge.HeadSpaceAPI;
import android.santosh.com.headspacecodechallenge.HeadSpaceApplication;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Created by Santosh on 8/13/17.
 */

public class BaseFragment extends Fragment {
    protected HeadSpaceAPI headSpaceAPI;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        headSpaceAPI = ((HeadSpaceApplication) getActivity().getApplication()).getHeadSpaceAPI();
    }
}

package app.davecstillo.com.cafebar;



import android.support.v4.app.Fragment;

import app.davecstillo.com.cafebar.BaseActivity;

public class BaseFragment extends Fragment {

    public BaseActivity getBaseActivity() {
        return (BaseActivity) this.getActivity();
    }


}
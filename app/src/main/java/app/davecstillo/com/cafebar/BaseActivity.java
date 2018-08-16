package app.davecstillo.com.cafebar;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import app.davecstillo.com.cafebar.Content.ProductContent;
import app.davecstillo.com.cafebar.Content.foodInfo;
import app.davecstillo.com.cafebar.R;

public class BaseActivity extends AppCompatActivity implements ProductItemFragment.OnListFragmentInteractionListener, ProductFragment.OnListFragmentInteractionListener{


    public void changeFragment(BaseFragment f){
        changeFragment(f,true);
    }

    public void changeFragment(BaseFragment f, boolean backstack){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction().replace(R.id.fragmentContenido,f);
        if(backstack)
            trans.addToBackStack(null);
        else
            manager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);

        trans.commit();

    }

    @Override
    public void onListFragmentInteraction(ProductContent.ProdListItem item) {

    }

    @Override
    public void onListFragmentInteraction(foodItem item) {

    }
}

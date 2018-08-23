package app.davecstillo.com.cafebar;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import app.davecstillo.com.cafebar.Content.ProductContent;
import app.davecstillo.com.cafebar.Content.cuentaInfo;
import app.davecstillo.com.cafebar.Content.foodInfo;
import app.davecstillo.com.cafebar.R;
import app.davecstillo.com.cafebar.dummy.DummyContent;

public class BaseActivity extends AppCompatActivity implements
        ProductItemFragment.OnListFragmentInteractionListener,
        foodClassFragment.OnListFragmentInteractionListener,
        foodListDialogFragment.OnListFragmentInteractionListener,
        pedidosFragment.OnListFragmentInteractionListener {


    public FragmentManager manager = getSupportFragmentManager();
    public void changeFragment(BaseFragment f){
        changeFragment(f,true);
    }

    public void changeFragment(BaseFragment f, boolean backstack){
        FragmentTransaction trans = manager.beginTransaction().replace(R.id.fragmentContenido,f);
        if(backstack)
            trans.addToBackStack(null);
        else
            manager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);

        trans.commit();

    }

    public FragmentManager getManager() {
        return manager;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }


    @Override
    public void onListFragmentInteraction(ProductContent.ProdListItem item) {
        foodListDialogFragment foodDialog = new foodListDialogFragment();
        foodDialog.setDialogName(item.nombre);
     //   FragmentTransaction trans = manager.beginTransaction().add(foodDialog,item.nombre);
        foodDialog.show(manager,item.nombre);
       // trans.commit();

    }


    @Override
    public void onListFragmentInteraction(foodInfo.foodItem item, DialogFragment dialog) {

        Log.d("foodItem", item.name);
        Toast.makeText(getApplicationContext(),"foodItem: "+item.name, Toast.LENGTH_LONG).show();
        cuentaInfo.addItem(cuentaInfo.createCuentaItem(item.id,item.name));
        dialog.dismiss();
    }


    @Override
    public void onListFragmentInteraction(cuentaInfo.cuentaItem item) {

    }
}

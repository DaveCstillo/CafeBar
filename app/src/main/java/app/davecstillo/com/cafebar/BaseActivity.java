package app.davecstillo.com.cafebar;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import app.davecstillo.com.cafebar.Content.ProductContent;
import app.davecstillo.com.cafebar.Content.cuentaInfo;
import app.davecstillo.com.cafebar.Content.extrasContent;
import app.davecstillo.com.cafebar.Content.foodInfo;
import app.davecstillo.com.cafebar.Content.mesas;
import app.davecstillo.com.cafebar.R;
import app.davecstillo.com.cafebar.dummy.DummyContent;

public class BaseActivity extends AppCompatActivity implements
        ProductItemFragment.OnListFragmentInteractionListener,
        foodClassFragment.OnListFragmentInteractionListener,
        foodListDialogFragment.OnListFragmentInteractionListener,
        pedidosFragment.OnListFragmentInteractionListener,
        extrasFragment.OnListFragmentInteractionListener,
        MesasFragment.OnListFragmentInteractionListener{


    public boolean traido = false;
    foodListDialogFragment foodDialog;
    Ordenes ordenes;


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

    public void setOrdenes(Ordenes ordenes) {
        this.ordenes = ordenes;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void onListFragmentInteraction(ProductContent.ProdListItem item) {
        foodDialog = new foodListDialogFragment();
        foodDialog.setDialogName(item.nombre);
        //   FragmentTransaction trans = manager.beginTransaction().add(foodDialog,item.nombre);

        foodDialog.show(manager,item.nombre);

    }

    @Override
    public void onListFragmentInteraction(foodInfo.foodItem item, DialogFragment dialog) {

        Log.d("foodItem", item.name);
        Toast.makeText(getApplicationContext(),"foodItem: "+item.name, Toast.LENGTH_LONG).show();
        ordenes.info.addItem(ordenes.info.createCuentaItem(item.id,item.name,1, item.precio));
        dialog.dismiss();
        ordenes.restorePedidos();
        ordenes.toggleCategories();
    }


    @Override
    public void onListFragmentInteraction(cuentaInfo.cuentaItem item) {


    }


    @Override
    public void onListFragmentInteraction(extrasContent.extraItem item) {
        
    }

    @Override
    public void onListFragmentInteraction(mesas.mesaItem item, View view) {
//        cuentas f = new cuentas();
//
//        view.setOnClickListener(v->{
//            Toast.makeText(getApplicationContext(),"que onda!!",Toast.LENGTH_SHORT).show();
//            changeFragment(f);
//        });



    }
}


package app.davecstillo.com.cafebar;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.JsonElement;

import java.util.Random;

import app.davecstillo.com.cafebar.Content.cuentaInfo;


/**
 * A simple {@link Fragment} subclass.
 */
public class Ordenes extends BaseFragment {


    boolean VariasCuentas;
    int cuantasCuentas, cuantasPendientes, cuentaActual;
    Button addThing, continuar;
    View f,p;
    int noMesa;
    Random rand = new Random();
    int noCuenta;
    cuentaInfo info;
    boolean prodVisible = false;
    protected enum state {PENDING, FINISHED};
    public state Estado;


    public Ordenes() {
        // Required empty public constructor
    }

    public void setVariasCuentas(boolean varias){
        VariasCuentas = varias;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_ordenes, container, false);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        getBaseActivity().setSupportActionBar(toolbar);
        toolbar.setTitle("Que va a querer?");
        continuar = view.findViewById(R.id.contBtn);
        addThing = view.findViewById(R.id.addthing);
        p = view.findViewById(R.id.pedidosList);
        f = view.findViewById(R.id.prodItem);
        f.setVisibility(View.GONE);
        info = new cuentaInfo();
        info.clearList();
        restorePedidos();

        noCuenta = rand.nextInt(1000);
        Log.d("Numero de Cuenta", String.valueOf(noCuenta));

        getBaseActivity().setOrdenes(this);


        if(VariasCuentas){
            Estado = state.PENDING;
        }else{
            Estado = state.FINISHED;
        }

        addThing.setOnClickListener(view1 -> {
          toggleCategories();
        });

        continuar.setOnClickListener((v)->{
            callList();
            setNextBtnText();
        });


        setNextBtnText();
        return view;
    }

    public void callList(){
        String path = "setCuentaInfo.php";
        if(VariasCuentas) {

            new BackgroundTask<JsonElement>(() -> httpHandler.instance.sendJson(path, info.ITEMS, noCuenta, noMesa, cuentaActual), (json, exception) -> {

                if (exception != null) {
                    exception.printStackTrace();
                }
                if (json != null) {
                    Log.d("LOCOO", "Ha funcionao");
                    Log.d("Json", json.toString());
                    revisarCuenta();
                    appearChange();
                }

            }).execute();


        }else {
            new BackgroundTask<JsonElement>(() -> httpHandler.instance.sendJson(path, info.ITEMS, noCuenta, noMesa), (json, exception) -> {

                if (exception != null) {
                    exception.printStackTrace();
                }
                if (json != null) {
                    Log.d("LOCOO", "Ha funcionao");
                    Log.d("Json", json.toString());
                }


            }).execute();
        }

        if(Estado == state.PENDING){

        }else{

        }
    }

    public void revisarCuenta(){
        revisiondeCuentaFragment f = new revisiondeCuentaFragment(noCuenta,noMesa);
        //TODO: Arreglar esto, que aparezca la confirmacion antes de enviar los datos
        //getBaseActivity().changeFragment(f);
        f.show(getBaseActivity().getManager(),"Revision");
    }

    public void appearChange(){
        Ordenes ordenNueva = new Ordenes();
        ordenNueva.setVariasCuentas(true);
        ordenNueva.setCuantasCuentas(cuantasCuentas, cuentaActual+1);
        ordenNueva.setNoMesa(noMesa);
        getBaseActivity().changeFragment(ordenNueva);

    }

    public void toggleCategories(){
        if(!prodVisible) {
            f.setVisibility(View.VISIBLE);
//               Toast.makeText(getContext(),"Alv  :v",Toast.LENGTH_SHORT).show();
            prodVisible = true;
            addThing.setVisibility(View.GONE);
//            addThing.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_shopping_basket, 0, 0, 0);
        }else {
            f.setVisibility(View.GONE);
//               Toast.makeText(getContext(),"Alv  v:",Toast.LENGTH_SHORT).show();
            prodVisible = false;
            addThing.setVisibility(View.VISIBLE);
//            addThing.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_plus, 0, 0, 0);

        }
    }


    public void setNextBtnText(){
         if(!VariasCuentas){
            Estado = state.FINISHED;
             continuar.setText("Continuar");
        }else {
            if(cuantasPendientes>1) {
                Estado = state.PENDING;
                continuar.setText("Siguiente");
            }else {
                Estado = state.FINISHED;
                continuar.setText("Continuar");
            }
         }

    }


    public void restorePedidos(){

        try {
            p.setVisibility(View.GONE);
            Thread.sleep(200);
            p.setVisibility(View.VISIBLE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

    public void setCuantasCuentas(int cuantasCuentas, int actual) {
        this.cuantasCuentas = cuantasCuentas;
        this.cuentaActual = actual;
        this.cuantasPendientes = cuantasCuentas - actual;
    }


    public int getNoMesa() {
        return noMesa;
    }

    public void setNoMesa(int noMesa) {
        this.noMesa = noMesa;
    }
}

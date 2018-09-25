package app.davecstillo.com.cafebar;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import app.davecstillo.com.cafebar.Content.ProductContent;


/**
 * A simple {@link Fragment} subclass.
 */
public class cuentas extends BaseFragment {


    Button una, varias;
    ProductContent pContent;
    Ordenes f;
    ProgressDialog progressBar;
    int noMesa;

    public cuentas() {
        // Required empty public constructor
    }
    @SuppressLint("ValidFragment")
    public cuentas(int noMesa){
        this.noMesa = noMesa;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cuentas, container, false);

        una = view.findViewById(R.id.Cunasola);
        varias = view.findViewById(R.id.Cseparadas);
        pContent = new ProductContent();
        progressBar = new ProgressDialog(getContext());

        progressBar.setIndeterminate(true);
        progressBar.setMessage("Cargando...");
        progressBar.setCancelable(false);

        f  = new Ordenes();
        f.setNoMesa(noMesa);



        una.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f.setVariasCuentas(false);
                f.setCuantasCuentas(1,1);
                callList("getcategories.php", f);

            }
        });
        varias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f.setVariasCuentas(true);
                callList("getcategories.php", f, progressBar, pContent);

            }
        });



        return view;
    }

    public void callList(String path, Ordenes fragment, ProgressDialog progress, ProductContent pContent){

        CuantasCuentas cuantasCuentas = new CuantasCuentas();
        cuantasCuentas.setBaseActivity(getBaseActivity());
        cuantasCuentas.setFragment(fragment);
        cuantasCuentas.setPath(path);
        cuantasCuentas.setpContent(pContent);
        cuantasCuentas.setProgress(progress);
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(getContext().INPUT_METHOD_SERVICE);
        cuantasCuentas.setIMM(imm);
        cuantasCuentas.show(getBaseActivity().getManager(),"Cuantas Cuentas");


        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
        }

    }


    public void callList(String path, BaseFragment fragment){
        progressBar.show();
        pContent.clearList();
        new BackgroundTask<JsonElement>(()-> httpHandler.instance.getJson(path), (json,exception)->{
            if(exception!=null){

            }
            if(json!=null){
                //JsonObject object = json.getAsJsonObject();

                for(JsonElement res : json.getAsJsonObject().get("Categorias").getAsJsonArray()){
                    int ID;
                    String name;


                    if(res.getAsJsonObject().get("Result")!=null){

                    }else {
                        ID = res.getAsJsonObject().get("ID").getAsInt();
                        name = res.getAsJsonObject().get("Nombre").getAsString();
                        createCategorie(ID,name);
                    }
                }
                progressBar.dismiss();
                getBaseActivity().changeFragment(fragment);

            }

        }).execute();

    }

    public void createCategorie(int ID, String name){

        pContent.addItem(pContent.createProdList(ID,name));
    }

}

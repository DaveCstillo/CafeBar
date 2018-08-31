package app.davecstillo.com.cafebar;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public cuentas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cuentas, container, false);

        una = view.findViewById(R.id.Cunasola);
        varias = view.findViewById(R.id.Cseparadas);
        f  = new Ordenes();
        pContent = new ProductContent();
        progressBar = new ProgressDialog(getContext());

        progressBar.setIndeterminate(true);
        progressBar.setMessage("Cargando...");
        progressBar.setCancelable(false);



        una.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f.setVariasCuentas(false);
                callList("getcategories.php", f);

            }
        });
        varias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f.setVariasCuentas(true);
                callList("getcategories.php", f);

            }
        });



        return view;
    }


    public void callList(String path, BaseFragment fragment){
        progressBar.show();
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
                getBaseActivity().changeFragment(f);

            }

        }).execute();

    }

    public void createCategorie(int ID, String name){

        pContent.addItem(pContent.createProdList(ID,name));
    }

}

package app.davecstillo.com.cafebar;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.JsonElement;

import app.davecstillo.com.cafebar.Content.ProductContent;
import app.davecstillo.com.cafebar.ServerConnection.BackgroundTask;
import app.davecstillo.com.cafebar.ServerConnection.httpHandler;


/**
 * A simple {@link Fragment} subclass.
 */
public class CuantasCuentas extends DialogFragment {


    protected int cuantas = 0;
    Editable textoCuantas;
    Ordenes fragment;
    String path;
    ProgressDialog progressDialog;
    ProductContent pContent;
    BaseActivity baseActivity;

    public CuantasCuentas(){
        // Required empty public constructor
    }

    EditText editable;
    Button ok, cancel;

    InputMethodManager imm;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cuantas_cuentas, container, false);

        editable = view.findViewById(R.id.noCuentaTxt);
        ok = view.findViewById(R.id.noCuentaOk);
        cancel = view.findViewById(R.id.noCuentaNo);

        getDialog().setTitle("¿Cuantas Cuentas?");


        ok.setOnClickListener((v) -> {
           textoCuantas = editable.getText();
           cuantas = Integer.parseInt(textoCuantas.toString());

           fragment.setCuantasCuentas(cuantas,0);
            Log.d("Cuantas", "seran: " + String.valueOf(cuantas));

            llamando();

        });

        cancel.setOnClickListener((v)->{
            this.dismiss();
        });

        return view;
    }

    public void setIMM(InputMethodManager imm){
        this.imm = imm;
    }


    public void hideKeyboard() {
        imm.toggleSoftInput(0,InputMethodManager.HIDE_IMPLICIT_ONLY);
    }


    public void setFragment(Ordenes fragment) {
        this.fragment = fragment;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setpContent(ProductContent pContent) {
        this.pContent = pContent;
    }

    public void setProgress(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    public void setBaseActivity(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    public void llamando(){
        this.dismiss();
        progressDialog.show();
        pContent.clearList();
        new BackgroundTask<JsonElement>(()-> httpHandler.instance.getJson(path), (json, exception)->{
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
                progressDialog.dismiss();
                hideKeyboard();

                baseActivity.changeFragment(fragment);

            }

        }).execute();
    }

    public void createCategorie(int ID, String name){

        pContent.addItem(pContent.createProdList(ID,name));
    }
}

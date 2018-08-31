package app.davecstillo.com.cafebar;

import android.support.v4.app.DialogFragment;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import app.davecstillo.com.cafebar.Content.foodInfo;
import app.davecstillo.com.cafebar.Content.foodInfo.foodItem;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     foodListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 * <p>You activity (or fragment) needs to implement {@link foodListDialogFragment.OnListFragmentInteractionListener}.</p>
 */
public class foodListDialogFragment extends DialogFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_ITEM_COUNT = "item_count";
    private OnListFragmentInteractionListener mListener;
    private String dialogName;


    // TODO: Customize parameters
    public static foodListDialogFragment newInstance(int itemCount) {
        final foodListDialogFragment fragment = new foodListDialogFragment();
        final Bundle args = new Bundle();
        args.putInt(ARG_ITEM_COUNT, itemCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_list_dialog, container, false);

        RecyclerView recyclerView = (RecyclerView) view;
        //recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(),2));
        foodInfo.clearList();
        pedirComida(dialogName, recyclerView);
        getDialog().setTitle("Cargando...");

        return view;
    }



    public void pedirComida(String nombreTabla, RecyclerView recyclerView){
        nombreTabla = nombreTabla.replace(" ", "_");
        final String tablaName = nombreTabla;
        StringBuilder path = new StringBuilder("getproduct.php?tabla=");
        path.append(nombreTabla);

        String finalurl = path.toString();

        Log.d("URL", finalurl);

        new BackgroundTask<JsonElement>(()-> httpHandler.instance.getJson(finalurl), (json, exception)->{


            if(exception!=null){
                Log.d("Exception",exception.getMessage());
            }
            if(json!=null){
                JsonObject object = json.getAsJsonObject();
                JsonArray array = object.getAsJsonArray(tablaName);



                for(JsonElement res : json.getAsJsonObject().get(tablaName).getAsJsonArray()){
                    int Id, Precio;
                    String Nombre, Imagen;


                    if(res.getAsJsonObject().get("Result")!=null){

                    }else{
                        Id = res.getAsJsonObject().get("ID").getAsInt();
                        Nombre = res.getAsJsonObject().get("Nombre").getAsString();
                        Imagen = res.getAsJsonObject().get("Imagen").getAsString();
                        Precio = res.getAsJsonObject().get("Precio").getAsInt();

                        createNewfoodItem(Id, Nombre, Imagen, Precio,tablaName, recyclerView);


                    }



                }
            }


        }).execute();

    }


    public void createNewfoodItem(int Id, String Nombre, String Imagen, int Precio,String categoria,RecyclerView recyclerView){
        foodInfo.addItem(foodInfo.createFoodInfo(Id,Nombre,Imagen,Precio,categoria));

        recyclerView.setAdapter(new foodListRecyclerView(foodInfo.ITEMS,mListener,this));
        getDialog().setTitle(dialogName);

    }






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        final Fragment parent = getParentFragment();
//        if (parent != null) {
//            mListener = (Listener) parent;
//        } else {
//            mListener = (Listener) context;
//        }
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(foodInfo.foodItem item, DialogFragment dialog);
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    public void setDialogName(String name){
        this.dialogName = name;
    }


}

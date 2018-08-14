package app.davecstillo.com.cafebar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Ordenes extends BaseFragment {


    boolean VariasCuentas;
    Button addThing;
    View f;

    Productos productos;
    boolean prodVisible = false;

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


        addThing = view.findViewById(R.id.addthing);
        f = view.findViewById(R.id.prdFragment);
        f.setVisibility(View.GONE);


        if(VariasCuentas){
            Toast.makeText(getContext(),"Se van a pedir cuentas separadas",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getContext(),"Se va a pedir en una sola cuenta",Toast.LENGTH_LONG).show();
        }

        addThing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(!prodVisible) {
                   f.setVisibility(View.VISIBLE);
                   Toast.makeText(getContext(),"Alv  :v",Toast.LENGTH_LONG).show();
                   prodVisible = true;
               }else {
                   f.setVisibility(View.GONE);
                   Toast.makeText(getContext(),"Alv  v:",Toast.LENGTH_LONG).show();
                   prodVisible = false;
               }
            }
        });


        return view;
    }




}

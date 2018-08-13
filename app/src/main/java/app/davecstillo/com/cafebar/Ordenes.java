package app.davecstillo.com.cafebar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Ordenes extends BaseFragment {


    boolean VariasCuentas;
    TextView texto;

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

        texto = view.findViewById(R.id.texto1);

        if(VariasCuentas){
            texto.setText("Se van a pedir cuentas separadas");
        }else{
            texto.setText("Se va a pedir en una sola cuenta");
        }

        return view;
    }




}

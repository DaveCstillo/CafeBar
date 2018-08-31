package app.davecstillo.com.cafebar;


import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Ordenes extends BaseFragment {


    boolean VariasCuentas;
    Button addThing, continuar;
    View f,p;

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
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        getBaseActivity().setSupportActionBar(toolbar);
        toolbar.setTitle("Que va a querer?");
        continuar = view.findViewById(R.id.contBtn);

        addThing = view.findViewById(R.id.addthing);
        p = view.findViewById(R.id.pedidosList);
        f = view.findViewById(R.id.prodItem);
        f.setVisibility(View.GONE);

        getBaseActivity().setOrdenes(this);


        if(VariasCuentas){
//            Toast.makeText(getContext(),"Se van a pedir cuentas separadas",Toast.LENGTH_LONG).show();
        }else{
//            Toast.makeText(getContext(),"Se va a pedir en una sola cuenta",Toast.LENGTH_LONG).show();
        }

        addThing.setOnClickListener(view1 -> {
          toggleCategories();
        });

        continuar.setOnClickListener((v)-> Toast.makeText(getContext(),"Continuando",Toast.LENGTH_LONG).show());


        return view;
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


    public void restorePedidos(){

        try {
            p.setVisibility(View.GONE);
            Thread.sleep(200);
            p.setVisibility(View.VISIBLE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }

}

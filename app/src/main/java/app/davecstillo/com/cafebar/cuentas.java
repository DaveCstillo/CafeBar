package app.davecstillo.com.cafebar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class cuentas extends BaseFragment {


    Button una, varias;

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

        una.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ordenes f = new Ordenes();
                f.setVariasCuentas(false);
                getBaseActivity().changeFragment(f);
            }
        });
        varias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ordenes f = new Ordenes();
                f.setVariasCuentas(true);
                getBaseActivity().changeFragment(f);
            }
        });



        return view;
    }


}

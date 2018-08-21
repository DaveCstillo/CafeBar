package app.davecstillo.com.cafebar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TabHost;


/**
 * A simple {@link Fragment} subclass.
 */
public class products extends Fragment {


    TabHost tabHost;
    LinearLayout tab1, tab2, tab3;

    public products() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_products, container, false);


        tab1 = view.findViewById(R.id.tab1);
        tab2 = view.findViewById(R.id.tab2);
        tab3 = view.findViewById(R.id.tab3);

        tabHost = view.findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("ENTRADAS");
        spec.setContent(R.id.tab1);
        spec.setIndicator("ENTRADAS");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("COMIDA");
        spec.setContent(R.id.tab2);
        spec.setIndicator("COMIDA");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("BEBIDAS FRIAS");
        spec.setContent(R.id.tab3);
        spec.setIndicator("BEBIDAS FRIAS");
        tabHost.addTab(spec);



        return view;
    }

}

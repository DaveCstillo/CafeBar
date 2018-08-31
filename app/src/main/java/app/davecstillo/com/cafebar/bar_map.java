package app.davecstillo.com.cafebar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class bar_map extends BaseFragment {


    ImageView mesa;


    public bar_map() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bar_map, container, false);

//        mesa = view.findViewById(R.id.mesa);
//
//        mesa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                BaseFragment f = new cuentas();
//
//                getBaseActivity().changeFragment(f);
//            }
//        });

        return view;
    }




}

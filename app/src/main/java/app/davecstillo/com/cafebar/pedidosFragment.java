package app.davecstillo.com.cafebar;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import app.davecstillo.com.cafebar.Content.cuentaInfo;
import app.davecstillo.com.cafebar.Content.cuentaInfo.cuentaItem;

public class pedidosFragment extends BaseFragment {

    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    ImageButton extraProd;
    Boolean eVisible = false;


    public pedidosFragment() {
        //have an empty constructor for calls
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_productitem_list, container, false);


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
                recyclerView.setLayoutManager(new LinearLayoutManager(context));

            pedidosRecyclerViewAdapter pedidos;
                pedidos = new pedidosRecyclerViewAdapter(cuentaInfo.ITEMS, mListener);
                pedidos.notifyDataSetChanged();



            recyclerView.setAdapter(pedidos);

        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    public interface OnListFragmentInteractionListener{
        void onListFragmentInteraction(cuentaItem item);
    }
}

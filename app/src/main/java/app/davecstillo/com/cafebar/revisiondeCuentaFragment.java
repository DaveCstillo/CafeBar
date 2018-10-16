package app.davecstillo.com.cafebar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonElement;

import app.davecstillo.com.cafebar.Content.revisionCuentaContent;
import app.davecstillo.com.cafebar.Content.revisionCuentaContent.cuentaContent;
import app.davecstillo.com.cafebar.ServerConnection.BackgroundTask;
import app.davecstillo.com.cafebar.ServerConnection.httpHandler;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class revisiondeCuentaFragment extends DialogFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private int noCuenta, noMesa;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public revisiondeCuentaFragment() {
    }


    @SuppressLint("ValidFragment")
    public revisiondeCuentaFragment(int noCuenta, int noMesa){
        this.noCuenta = noCuenta;
        this.noMesa = noMesa;
    }
    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static revisiondeCuentaFragment newInstance(int columnCount) {
        revisiondeCuentaFragment fragment = new revisiondeCuentaFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_revisiondecuenta_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            getDialog().setTitle("Numero de cuenta: " + noCuenta);
            revisionCuentaContent.clearList();
            queHay(recyclerView);
        }
        return view;
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public void queHay(RecyclerView recyclerView){
        StringBuilder url = new StringBuilder("getCuentas.php?noMesa=");
        url.append(noMesa);
        String path = url.toString();
        new BackgroundTask<JsonElement>(()-> httpHandler.instance.getJson(path), (json, ex)->{
            if(ex!=null){

            }
            if(json!=null){
                for(JsonElement res : json.getAsJsonObject().get("Orden").getAsJsonArray()) {
                    Log.d("Res",res.toString());



                    onInfoFetched(recyclerView);
                }

                }


        }).execute();


    }



    public void onInfoFetched(RecyclerView recyclerView){
        recyclerView.setAdapter(new revisiondeCuentaRecyclerViewAdapter(revisionCuentaContent.ITEMS, mListener));
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(cuentaContent item);
    }
}

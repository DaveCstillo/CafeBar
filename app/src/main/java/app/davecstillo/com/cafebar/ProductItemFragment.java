package app.davecstillo.com.cafebar;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonElement;

import app.davecstillo.com.cafebar.Content.ProductContent;
import app.davecstillo.com.cafebar.Content.foodInfo;
import app.davecstillo.com.cafebar.dummy.DummyContent;
import app.davecstillo.com.cafebar.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ProductItemFragment extends BaseFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;


    private ProductContent listProd;
    private Productos contentProd;
    private foodInfo fInfo;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProductItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ProductItemFragment newInstance(int columnCount) {
        ProductItemFragment fragment = new ProductItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        listProd = new ProductContent();
        fInfo = new foodInfo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_productitem_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            //callList();

            addFood();

            listProd.addItem(listProd.createProdList(0,"Comida",fInfo));
            listProd.addItem(listProd.createProdList(1,"Bebidas Frias",fInfo));
            listProd.addItem(listProd.createProdList(2,"Bebidas Calientes",fInfo));


            recyclerView.setAdapter(new ProductItemRecyclerViewAdapter(listProd.ITEMS, mListener));
        }
        return view;
    }


    public void addFood(){
        fInfo.addItem(fInfo.createFoodInfo(1,"Comida 1",R.drawable.comida_1));
        fInfo.addItem(fInfo.createFoodInfo(2,"Comida 2",R.drawable.comida_2));
        fInfo.addItem(fInfo.createFoodInfo(3,"Comida 3",R.drawable.comida_3));
        fInfo.addItem(fInfo.createFoodInfo(4,"Comida 4",R.drawable.comida_4));
        fInfo.addItem(fInfo.createFoodInfo(5,"Comida 5",R.drawable.comida_5));
        fInfo.addItem(fInfo.createFoodInfo(6,"Comida 6",R.drawable.comida_6));
    }

    public void callList(){
        new BackgroundTask<JsonElement>(()->httpHandler.instance.getJson("LOL"), (json,exception)->{

            if(exception==null){

            }else{

            }
        }).execute();
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
        void onListFragmentInteraction(ProductContent.ProdListItem item);
    }
}

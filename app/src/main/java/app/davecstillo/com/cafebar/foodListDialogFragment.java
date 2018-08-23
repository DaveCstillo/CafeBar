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



        foodInfo.addItem(foodInfo.createFoodInfo(1,"Absolut Melon Mojito",R.drawable.absolut_melon_mojito));
        foodInfo.addItem(foodInfo.createFoodInfo(2,"Adios MotherFucker",R.drawable.adios_motherfcker));
        foodInfo.addItem(foodInfo.createFoodInfo(3,"Blue Magic",R.drawable.blue_magic));
        foodInfo.addItem(foodInfo.createFoodInfo(4,"Alexander",R.drawable.alexander));
        foodInfo.addItem(foodInfo.createFoodInfo(5,"Agave Kiss",R.drawable.agave_kiss));
        foodInfo.addItem(foodInfo.createFoodInfo(6,"The Incredible Hulk",R.drawable.avengers_incredible_hulk));
        foodInfo.addItem(foodInfo.createFoodInfo(7,"Blue Frog Cocktail",R.drawable.blue_frog_cocktail));
        foodInfo.addItem(foodInfo.createFoodInfo(8,"AkuAku",R.drawable.akuaku));
        foodInfo.addItem(foodInfo.createFoodInfo(9,"Alabama Bushwacker",R.drawable.alabama_bushwacker));
        foodInfo.addItem(foodInfo.createFoodInfo(10,"Amaro Spike",R.drawable.amaro_spike));
        foodInfo.addItem(foodInfo.createFoodInfo(11,"Area 51 Shot",R.drawable.area_51_shot));
        foodInfo.addItem(foodInfo.createFoodInfo(12,"Aurora Borealis",R.drawable.aurora_borealis));
        foodInfo.addItem(foodInfo.createFoodInfo(13,"Bacon Shot Glasses",R.drawable.bacon_shot_glasses));
        foodInfo.addItem(foodInfo.createFoodInfo(14,"Berry Mojito",R.drawable.berry_mojito));
        foodInfo.addItem(foodInfo.createFoodInfo(15,"Birth Bath",R.drawable.bird_bath));
        foodInfo.addItem(foodInfo.createFoodInfo(16,"BlackBerry Mango Margarita",R.drawable.blackberry_mango_margarita));
        foodInfo.addItem(foodInfo.createFoodInfo(17,"BlowJob Shots",R.drawable.blowjob_shots));
        foodInfo.addItem(foodInfo.createFoodInfo(18,"Blue Jamaican Long Island",R.drawable.blue_jamaican_long_island));
        foodInfo.addItem(foodInfo.createFoodInfo(19,"Blue Lemonade",R.drawable.blue_lemonade));
        foodInfo.addItem(foodInfo.createFoodInfo(20,"BlueBerry Mojito",R.drawable.blueberry_mojito));
        foodInfo.addItem(foodInfo.createFoodInfo(21,"Hooker Shots",R.drawable.hooker_shots_2));
        foodInfo.addItem(foodInfo.createFoodInfo(22,"Thousand Knife Vs Bacardi",R.drawable.thousand_knife_vs_bacardi_151));
        foodInfo.addItem(foodInfo.createFoodInfo(23,"Way to die #151",R.drawable.way_to_die_no_151));



        recyclerView.setAdapter(new foodListRecyclerView(foodInfo.ITEMS, mListener, this));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(dialogName);
        builder.setView(view);
        builder.create();
        return view;
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

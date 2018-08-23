package app.davecstillo.com.cafebar;

import android.support.v4.app.DialogFragment;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.davecstillo.com.cafebar.Content.foodInfo;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     foodListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 * <p>You activity (or fragment) needs to implement {@link foodListDialogFragment.Listener}.</p>
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

        final RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        foodInfo food = new foodInfo();

        food.addItem(food.createFoodInfo(0,"Mojito",R.drawable.absolut_melon_mojito));
        food.addItem(food.createFoodInfo(1,"Adios MotherFucker",R.drawable.adios_motherfcker));
        food.addItem(food.createFoodInfo(2,"Blue Magic",R.drawable.blue_magic));
        food.addItem(food.createFoodInfo(3,"Alexander",R.drawable.alexander));



        recyclerView.setAdapter(new foodAdapter(food.ITEMS));


        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_food_list_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(dialogName).setView(v);

        return builder.create();
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
        void onListFragmentInteraction(foodInfo.foodItem item);
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    public void setDialogName(String name){
        this.dialogName = name;
    }

    public interface Listener {
        void onfoodClicked(int position);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        final TextView text;
        public foodInfo.foodItem mItem;

        ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            // TODO: Customize the item layout
            super(inflater.inflate(R.layout.fragment_food_list_dialog_item, parent, false));
            text = itemView.findViewById(R.id.text);

        }

    }

    private class foodAdapter extends RecyclerView.Adapter<ViewHolder> {

        private final List<foodInfo.foodItem> mValues;

        foodAdapter(List<foodInfo.foodItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.text.setText(mValues.get(position).name);

            holder.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onListFragmentInteraction(holder.mItem);
                        Log.d("onfoodClick","Se ha presionado "+holder.text.getText()+" ");
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

    }

}

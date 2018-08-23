package app.davecstillo.com.cafebar;

import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.davecstillo.com.cafebar.Content.foodInfo.foodItem;
import app.davecstillo.com.cafebar.foodListDialogFragment.OnListFragmentInteractionListener;

public class foodListRecyclerView extends RecyclerView.Adapter<foodListRecyclerView.ViewHolder>{


    private final List<foodItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final DialogFragment dialogFragment;

    public foodListRecyclerView(List<foodItem> items, OnListFragmentInteractionListener listener,DialogFragment dialog){
        mValues = items;
        mListener = listener;
        dialogFragment = dialog;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_food_list_dialog_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(foodListRecyclerView.ViewHolder holder, int position) {

        holder.mItem = mValues.get(position);
        holder.text.setText(mValues.get(position).name);
        holder.imagen.setImageResource(mValues.get(position).imgID);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onListFragmentInteraction(holder.mItem, dialogFragment);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        final TextView text;
        final ImageView imagen;
        public foodItem mItem;

        ViewHolder(View view) {
            // TODO: Customize the item layout
            super(view);
            mView = view;
            text = view.findViewById(R.id.exText);
            imagen = view.findViewById(R.id.imgFoodView);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }



}

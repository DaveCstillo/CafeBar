package app.davecstillo.com.cafebar;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.davecstillo.com.cafebar.Content.ProductContent;
import app.davecstillo.com.cafebar.foodClassFragment.OnListFragmentInteractionListener;
import app.davecstillo.com.cafebar.Content.ProductContent.ProdListItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ProdListItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyfoodClassRecyclerViewAdapter extends RecyclerView.Adapter<MyfoodClassRecyclerViewAdapter.ViewHolder> {

    private final List<ProdListItem> mValues;
    private final OnListFragmentInteractionListener mListener;


    public MyfoodClassRecyclerViewAdapter(List<ProdListItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_foodclass, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mBtnView.setText(mValues.get(position).nombre);

        holder.mBtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });

    }




    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mBtnView;
        public ProductContent.ProdListItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mBtnView = view.findViewById(R.id.btnFood);
        }



        @Override
        public String toString() {
            return super.toString() + mBtnView.getText();
        }
    }
}

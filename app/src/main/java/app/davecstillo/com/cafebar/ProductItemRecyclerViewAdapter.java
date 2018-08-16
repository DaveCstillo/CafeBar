package app.davecstillo.com.cafebar;

import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import app.davecstillo.com.cafebar.ProductItemFragment.OnListFragmentInteractionListener;
import app.davecstillo.com.cafebar.Content.ProductContent.ProdListItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ProdListItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ProductItemRecyclerViewAdapter extends RecyclerView.Adapter<ProductItemRecyclerViewAdapter.ViewHolder> {

    private final List<ProdListItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ProductItemRecyclerViewAdapter(List<ProdListItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_productitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mButtonView.setText(mValues.get(position).nombre);

        holder.mView.setOnClickListener(new View.OnClickListener() {
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
        public final Button mButtonView;
        public final View mContentView;
        public ProdListItem mItem;
        private boolean contentVisibility = false;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mButtonView = view.findViewById(R.id.btnprodlist);
            mContentView = view.findViewById(R.id.prdFragment);
            mContentView.setVisibility(View.GONE);
            mButtonView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(contentVisibility){
                        mContentView.setVisibility(View.GONE);
                        contentVisibility = false;
                    }else{
                        mContentView.setVisibility(View.VISIBLE);
                        contentVisibility = true;
                    }

                }
            });
        }

        @Override
        public String toString() {
            return "";
        }
    }
}
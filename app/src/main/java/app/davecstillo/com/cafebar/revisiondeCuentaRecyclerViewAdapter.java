package app.davecstillo.com.cafebar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import app.davecstillo.com.cafebar.revisiondeCuentaFragment.OnListFragmentInteractionListener;
import app.davecstillo.com.cafebar.Content.revisionCuentaContent;
import app.davecstillo.com.cafebar.Content.revisionCuentaContent.cuentaContent;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link cuentaContent} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class revisiondeCuentaRecyclerViewAdapter extends RecyclerView.Adapter<revisiondeCuentaRecyclerViewAdapter.ViewHolder> {

    private final List<cuentaContent> mValues;
    private final OnListFragmentInteractionListener mListener;

    public revisiondeCuentaRecyclerViewAdapter(List<cuentaContent> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_revisiondecuenta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //TODO: Arreglar esto tambien
        holder.mItem = mValues.get(position);
        holder.mPlatilloTxt.setText(mValues.get(position).platillo);
        holder.mCantidadTxt.setText(mValues.get(position).cantidad);
        holder.mExtrasTxt.setText(mValues.get(position).extras);

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
        public final TextView mPlatilloTxt, mCantidadTxt, mExtrasTxt;
        public cuentaContent mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mPlatilloTxt = view.findViewById(R.id.PlatilloTxt);
            mCantidadTxt = view.findViewById(R.id.CantidadTxt);
            mExtrasTxt = view.findViewById(R.id.ExtrasTxt);
        }

        @Override
        public String toString() {
            return mPlatilloTxt.getText() + " " + mCantidadTxt.getText()  + " " + mExtrasTxt.getText();
        }
    }
}

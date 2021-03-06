package app.davecstillo.com.cafebar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import app.davecstillo.com.cafebar.MesasFragment.OnListFragmentInteractionListener;
import app.davecstillo.com.cafebar.Content.mesas.mesaItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link mesaItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MesasRecyclerViewAdapter extends RecyclerView.Adapter<MesasRecyclerViewAdapter.ViewHolder> {

    private final List<mesaItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final BaseActivity activity;

    public MesasRecyclerViewAdapter(List<mesaItem> items, OnListFragmentInteractionListener listener, BaseActivity activity) {
        mValues = items;
        mListener = listener;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_mesas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.NoMesa.setText(String.valueOf(mValues.get(position).id));
        holder.NoMesa.setOnClickListener(v->{
            cuentas f = new cuentas(mValues.get(position).id);
            activity.changeFragment(f);

                }
        );

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem, holder.NoMesa);
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
        public final ImageView mesa;
        public final TextView NoMesa;
        public mesaItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mesa = view.findViewById(R.id.mesa);
            NoMesa = view.findViewById(R.id.mesaNo);
        }

        @Override
        public String toString() {
            return String.valueOf(mItem.id);
        }
    }
}

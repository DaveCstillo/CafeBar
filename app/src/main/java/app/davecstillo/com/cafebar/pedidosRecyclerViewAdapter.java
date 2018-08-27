package app.davecstillo.com.cafebar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import app.davecstillo.com.cafebar.Content.cuentaInfo;
import app.davecstillo.com.cafebar.Content.cuentaInfo.cuentaItem;
import app.davecstillo.com.cafebar.pedidosFragment.OnListFragmentInteractionListener;

public class pedidosRecyclerViewAdapter extends RecyclerView.Adapter<pedidosRecyclerViewAdapter.ViewHolder> {

    private final List<cuentaItem> mValues;
    private final OnListFragmentInteractionListener mListener;


    public pedidosRecyclerViewAdapter(List<cuentaItem> mValues, OnListFragmentInteractionListener mListener) {
        this.mValues = mValues;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pedidos, parent, false);
        notifyDataSetChanged();
        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(pedidosRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.nombre.setText(mValues.get(position).pedido);
            holder.cantidad.setText(holder.mItem.getCantidad());
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            });

            holder.menos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(holder.mItem.cantidad>=2) {
                        holder.mItem.removeOne();
                        holder.cantidad.setText(holder.mItem.getCantidad());
                    }else {
                        cuentaInfo.clearItem(mValues.get(position));
                        mValues.remove(holder.mItem);
                    }
                }
            });
            holder.mas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.mItem.addOne();
                    holder.cantidad.setText(holder.mItem.getCantidad());
                }
            });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public final View mView;
        public final ImageButton menos, mas;
        public final TextView nombre, cantidad;
        public cuentaItem mItem;


        public ViewHolder(View view) {
            super(view);
            this.mView = view;
            this.menos = view.findViewById(R.id.menosProd);
            this.mas = view.findViewById(R.id.masProd);
            this.nombre = view.findViewById(R.id.nombreProd);
            this.cantidad = view.findViewById(R.id.cantProd);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}

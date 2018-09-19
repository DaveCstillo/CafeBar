package app.davecstillo.com.cafebar;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.davecstillo.com.cafebar.Content.cuentaInfo;
import app.davecstillo.com.cafebar.Content.cuentaInfo.cuentaItem;
import app.davecstillo.com.cafebar.pedidosFragment.OnListFragmentInteractionListener;

public class pedidosRecyclerViewAdapter extends RecyclerView.Adapter<pedidosRecyclerViewAdapter.ViewHolder> {

    private final List<cuentaItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private boolean eVisible = false;


    public pedidosRecyclerViewAdapter(List<cuentaItem> mValues, OnListFragmentInteractionListener mListener) {
        this.mValues = mValues;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pedidos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(pedidosRecyclerViewAdapter.ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.nombre.setText(mValues.get(position).pedido);
            holder.cantidad.setText(holder.mItem.getCantidad());
            holder.precio.setText("Q." +String.valueOf(holder.mItem.precio));
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            });
            holder.extraLayout.setVisibility(View.GONE);

            holder.extraExpand.setOnClickListener(v->{

                if (eVisible){
                    eVisible=false;
                    holder.extraLayout.setVisibility(View.GONE);
                    holder.extraExpand.setImageResource(R.drawable.ic_expand_more);
                    Toast.makeText(holder.mView.getContext(),"Fueraaaa!",Toast.LENGTH_SHORT).show();
                }else{
                    eVisible=true;
                    holder.extraLayout.setVisibility(View.VISIBLE);
                    holder.extraExpand.setImageResource(R.drawable.ic_expand_less);
                    Toast.makeText(holder.mView.getContext(),"Dentrooo!",Toast.LENGTH_SHORT).show();

                }


            });

            holder.extraTxt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    mValues.get(position).setExtras(String.valueOf(charSequence));
                }

                @Override
                public void afterTextChanged(Editable editable) {

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
        public final ImageButton menos, mas, extraExpand;
        public final TextView nombre, cantidad, precio, extraTxt;
        public final LinearLayout extraLayout;
        public String extras;
        public cuentaItem mItem;


        public ViewHolder(View view) {
            super(view);
            this.mView = view;
            this.menos = view.findViewById(R.id.menosProd);
            this.mas = view.findViewById(R.id.masProd);
            this.nombre = view.findViewById(R.id.nombreProd);
            this.cantidad = view.findViewById(R.id.cantProd);
            this.extraExpand = view.findViewById(R.id.extraProd);
            this.extraTxt = view.findViewById(R.id.extraTxt);
            this.extraLayout = view.findViewById(R.id.ListaExtras);
            precio = view.findViewById(R.id.precioProd);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}

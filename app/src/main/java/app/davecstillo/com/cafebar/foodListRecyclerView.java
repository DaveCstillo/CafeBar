package app.davecstillo.com.cafebar;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

        ponerImagen(mValues.get(position).imgID, holder.imagen, position);
        holder.mItem = mValues.get(position);
        holder.text.setText(mValues.get(position).name);
       // holder.precio.setText("Q. "+ mValues.get(position).precio);
        holder.imagen.setImageBitmap(mValues.get(position).getImagen());


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onListFragmentInteraction(holder.mItem, dialogFragment);
            }
        });

    }


    public void ponerImagen(String imgName, ImageView view, int position){
        Images.newApi(mValues.get(position).categoria);
        String imgurl = imgName;
        ImageView Imagen = view;

        new BackgroundTask<Bitmap>(() -> Images.get(imgurl), (b,e)->{

            if(e!=null){

            }
            if(b!=null){
                Log.d("Imagen", "Puesta");
                //view.setImageBitmap(b);
                Imagen.setImageBitmap(b);
                //view.setImageBitmap(mValues.get(position).getImagen());
            }


        }).execute();


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

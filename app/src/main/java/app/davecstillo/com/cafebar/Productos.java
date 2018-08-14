package app.davecstillo.com.cafebar;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Productos extends BaseFragment {


    public Productos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_productos, container, false);

        GridView gridView = view.findViewById(R.id.productosgrid);
        gridView.setAdapter(new DataAdapter(getContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(), "Item" + i, Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }


    public class DataAdapter extends BaseAdapter {

        private Context mContext;

        public DataAdapter(Context c) {
            this.mContext = c;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            View view;
            if (convertView == null) {
                view = View.inflate(getContext(), R.layout.grid_content,null);
            } else {
                view =  convertView;

            }
            imageView = view.findViewById(R.id.productImage);
            imageView.setImageResource(mItemIds[position]);
            TextView textView = view.findViewById(R.id.productText);
            textView.setText(mItemName[position]);

            return view;
        }


        public void fillViewWithIndex(){

        }


        @Override
        public int getCount() {
            return mItemIds.length;
        }


        private Integer[] mItemIds = {
                R.drawable.comida_1,R.drawable.comida_2,R.drawable.comida_3,R.drawable.comida_4,R.drawable.comida_5,
                R.drawable.comida_6,R.drawable.comida_7,R.drawable.comida_8,R.drawable.comida_9,R.drawable.comida_10,
                R.drawable.comida_11,R.drawable.comida_12,R.drawable.comida_13,R.drawable.comida_14
    };
        private String[] mItemName = {
                "Comida 1","Comida 2","Comida 3","Comida 4","Comida 5",
                "Comida 6","Comida 7","Comida 8","Comida 9","Comida 10",
                "Comida 11","Comida 12","Comida 13","Comida 14"
        };


}

}

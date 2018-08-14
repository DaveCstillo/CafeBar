package app.davecstillo.com.cafebar;


import android.content.Context;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Productos extends Fragment {


    public Productos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_productos, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.productosgrid);
        gridView.setAdapter(new DataAdapter(getContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),"Item"+i,Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }


    public class DataAdapter extends BaseAdapter{

        private Context mContext;

        public DataAdapter(Context c){
            this.mContext = c;
        }

        public Object getItem(int position){
            return null;
        }

        public long getItemId(int position){
            return 0;
        }

        public View getView(int position,View convertView,ViewGroup paretn){
            TextView textView;
            if(convertView==null){
                textView = new TextView(mContext);
                textView.setText(mItemIds[position]);
            }else{
                textView = (TextView) convertView;

            }

            return textView;
        }


        @Override
        public int getCount() {
            return 0;
        }


        private Integer[] mItemIds = {
            
        };


    }
}

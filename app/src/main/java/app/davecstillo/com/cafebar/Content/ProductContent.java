package app.davecstillo.com.cafebar.Content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductContent {


    public static final List<ProdListItem> ITEMS = new ArrayList<ProdListItem>();

    public static final Map<String, ProdListItem> ITEM_MAP = new HashMap<String, ProdListItem>();


    static{
        //Add automatic Items
    }


    public void addItem(ProdListItem list){
        ITEMS.add(list);
        ITEM_MAP.put(String.valueOf(list.id), list);
    }

    public ProdListItem createProdList( int position, String nombre){
        return new ProdListItem(position, nombre);
    }





    //Products List Class
    public static class ProdListItem{
        public final int id;
        public final String nombre;

        public ProdListItem(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;

        }

        @Override
        public String toString() {
            return nombre;
        }




    }



}

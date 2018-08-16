package app.davecstillo.com.cafebar.Content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class foodInfo {

    public static final List<foodItem> ITEMS = new ArrayList<foodItem>();
    public static final Map<String, foodItem> ITEM_MAP = new HashMap<String, foodItem>();

    static{

    }

    public void addItem(foodItem item){
        ITEMS.add(item);
        ITEM_MAP.put(String.valueOf(item.id), item);
    }

    public foodItem createFoodInfo( int position, String nombre, int imgId){
        return new foodItem(position, nombre,imgId);
    }



    public static class foodItem{
        public final int id;
        public final String name;
        public final int imgID;


        public foodItem(int id, String name, int imgId) {
            this.id = id;
            this.name = name;
            this.imgID = imgId;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}

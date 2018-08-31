package app.davecstillo.com.cafebar.Content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class extrasContent {

    public static final List<extraItem> ITEMS = new ArrayList<extraItem>();
    public static final Map<String, extraItem> ITEM_MAP = new HashMap<String, extraItem>();



    static {

    }

    public static void addItem(extraItem item){
        ITEMS.add(item);
        ITEM_MAP.put(String.valueOf(item.id), item);
    }

    public static extraItem createExtraItem(int id){
        return new extraItem(id);
    }

    public static void clearList(){
        ITEMS.clear();
        ITEM_MAP.clear();
    }


    public static class extraItem{
        public final int id;
        public int precio;
        public String name;
        public boolean add_remove;


        public extraItem(int id) {
            this.id = id;
        }

        public boolean addORremove() {
            return add_remove;
        }

        public void toAddORremove(boolean add_remove) {
            this.add_remove = add_remove;
        }

        @Override
        public String toString() {
            if(name!="")
                return name;
            else
                return "NotSet";
        }
    }


}

package app.davecstillo.com.cafebar.Content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mesas {

    public static final List<mesaItem> ITEMS = new ArrayList<mesaItem>();
    public static final Map<String, mesaItem> ITEM_MAP = new HashMap<String, mesaItem>();

    static {
        for(int i = 1; i<=10;i++ ){
            addItem(crearMesa(i));
        }
    }

    public static void addItem(mesaItem item){
        ITEMS.add(item);
        ITEM_MAP.put(String.valueOf(item.id),item);
    }

    public static mesaItem crearMesa(int id){
        return new mesaItem(id);
    }

    public static class mesaItem{
        public final int id;


        public mesaItem(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return String.valueOf(id);
        }
    }
}

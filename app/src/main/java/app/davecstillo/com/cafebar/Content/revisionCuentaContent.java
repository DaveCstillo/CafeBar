package app.davecstillo.com.cafebar.Content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class revisionCuentaContent {

    public static List<cuentaContent> ITEMS = new ArrayList<cuentaContent>();
    public static Map<String, cuentaContent> ITEM_MAP = new HashMap<String, cuentaContent>();


    static {

    }

    public void addItem(cuentaContent item){
        ITEMS.add(item);
        ITEM_MAP.put(String.valueOf(item.id),item);
    }

    public cuentaContent createCuentaContent(int id, String platillo, int cantidad, String extras){

        return new cuentaContent(id,platillo, cantidad, extras);
    }

    public static void clearList(){
        ITEMS.clear();
        ITEM_MAP.clear();
    }


    public static class cuentaContent{
        public final int id, cantidad;
        public final String platillo, extras;

        public cuentaContent(int id, String platillo, int cantidad, String extras) {
            this.id = id;
            this.cantidad = cantidad;
            this.platillo = platillo;
            this.extras = extras;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

}

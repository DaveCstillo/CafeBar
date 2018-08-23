package app.davecstillo.com.cafebar.Content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cuentaInfo {

    public static final List<cuentaItem> ITEMS = new ArrayList<cuentaItem>();
    public static final Map<String, cuentaItem> ITEM_MAP = new HashMap<String, cuentaItem>();



    static {

    }

    public static void addItem(cuentaItem item){
        ITEMS.add(item);
        ITEM_MAP.put(String.valueOf(item.id),item);
    }

    public static cuentaItem createCuentaItem(int id, String pedido){
        return new cuentaItem(id, pedido);
    }


    public static class cuentaItem{
        public final int id;
        public final String pedido;
        public int cantidad;
        public String extras;


        public cuentaItem(int id, String pedido) {
            this.id = id;
            this.pedido = pedido;
        }

        public void setExtras(String extras){
            this.extras = extras;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public int getCantidad() {
            return cantidad;
        }

        public String getExtras() {
            return extras;
        }

        public String getPedido() {
            return pedido;
        }

        @Override
        public String toString() {
            return "Platillo: " + pedido +".  " + "Cantidad: " + String.valueOf(cantidad)+". ";
        }


    }
}

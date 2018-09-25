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

    public void addItem(cuentaItem item){
        ITEMS.add(item);
        ITEM_MAP.put(String.valueOf(item.id),item);
    }

    public cuentaItem createCuentaItem(int id, String pedido, int cantidad, int precio){
        return new cuentaItem(id, pedido, cantidad,precio);
    }

    public static void clearItem(cuentaItem item){
        ITEMS.remove(item);
        ITEM_MAP.remove(item);
    }

    public void clearList(){
        ITEMS.clear();
        ITEM_MAP.clear();
    }

    public static class cuentaItem{
        public final int id, precio;
        public final String pedido;
        public int cantidad;
        public String extras = "";


        public cuentaItem(int id, String pedido, int cantidad, int precio) {
            this.id = id;
            this.pedido = pedido;
            this.cantidad = cantidad;
            this.precio = precio;

        }

        public void addOne(){
            cantidad=cantidad+1;
        }
        public void removeOne(){
            cantidad=cantidad-1;
        }

        public int getPrecio() {
            return precio;
        }

        public void setExtras(String extras){
            this.extras = extras;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public String getCantidad() {
            return String.valueOf(cantidad);
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

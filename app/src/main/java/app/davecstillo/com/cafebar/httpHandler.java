package app.davecstillo.com.cafebar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.JsonReader;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import app.davecstillo.com.cafebar.Content.cuentaInfo;

public class httpHandler {

    public String user;

    private String url;
    JsonParser parser;

    public static httpHandler instance;

    public httpHandler(String url){
        this.url = url;
        parser = new JsonParser();
    }

//Esto es lo que tengo para leer el json que envia el servidor
    public JsonElement getJson(String path) throws Exception
    {
        Log.i("path", path);
        StringBuffer chaine = new StringBuffer("");
        URL url = new URL(this.url + path);
        Log.i("URL", String.valueOf(url));
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestProperty("User-Agent", "");
        connection.setReadTimeout(10000 /*Milliseconds*/);
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        while ((line = rd.readLine()) != null) {
            chaine.append(line);
        }
        Log.i("Json", chaine.toString());
        Log.i("Json2", String.valueOf(parser.parse(chaine.toString())));
        return parser.parse(chaine.toString());
    }

    public JsonElement sendJson(String path, List<cuentaInfo.cuentaItem> item, int noCuenta, int noMesa){
        OutputStream os = null;
        InputStream is = null;
        JsonReader reader = null;
        String message = "", jsonFinal = "";
        JSONObject jsonObject = new JSONObject();
        JSONObject jObject1 = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONArray jArray1 = new JSONArray();
        HttpURLConnection connection = null;
            StringBuilder chaine = new StringBuilder("");
        Log.i("path sendJSON", path);
        try {
            jsonObject.put("NoMesa",noMesa);
            for(int i=0;i<item.size();i++){
                jObject1.put("NoCuenta",String.valueOf(noCuenta));
                message = toJSON(item.get(i),i);
                try{
                    jsonArray.put(i,message);
                    //jsonFinal = jsonArray.toString();
                    Log.d("JsonArray",jsonArray.toString());
                    //Log.d("JsonFinal",jsonFinal);
                } catch (JSONException e){
                    e.printStackTrace();
                }
                jObject1.put("Listado",jsonArray);
            }
            jArray1.put(0,jObject1);
            jsonObject.put("Orden",jArray1);
            jsonFinal = jsonObject.toString();

            Log.d("JSONEnviar", jsonFinal);
            URL url = new URL(this.url + path);
            Log.i("URL", String.valueOf(url));
            connection = (HttpURLConnection)url.openConnection();
            //connection.setReadTimeout(10000 /*Milliseconds*/);
            //connection.setReadTimeout(15000 /*Milliseconds*/);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setFixedLengthStreamingMode(jsonFinal.getBytes().length);

            connection.setRequestProperty("Content-Type","application/json;charset=utf-8");
            connection.setRequestProperty("X-Requested-With", "XMLHttpRequest");

            connection.connect();

            os = new BufferedOutputStream(connection.getOutputStream());


            os.write(jsonFinal.getBytes());
            os.flush();


            //is = new BufferedInputStream(connection.getInputStream());
            //BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                //is = connection.getInputStream();
//            reader = new JsonReader(rd);
//            String line = "";
//            while ((line = rd.readLine()) != null) {
//                chaine.append(line);
//            }


        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }finally {
            try{
                os.close();
                //is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            connection.disconnect();
        }

            //reader.setLenient(true);
        Log.d("Response:",chaine.toString());

            return parser.parse(chaine.toString());


    }
    public JsonElement sendJson(String path, List<cuentaInfo.cuentaItem> item, int noCuenta, int noMesa, int cuentaActual){
        OutputStream os = null;
        InputStream is = null;
        JsonReader reader = null;
        String message = "", jsonFinal = "";
        JSONObject jsonObject = new JSONObject();
        JSONObject jObject1 = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONArray jArray1 = new JSONArray();
        HttpURLConnection connection = null;
        StringBuilder chaine = new StringBuilder("");
        Log.i("path sendJSON", path);
        try {
            jsonObject.put("NoMesa",noMesa);
            for(int i=0;i<item.size();i++){
                jObject1.put("NoCuenta",String.valueOf(noCuenta));
                message = toJSON(item.get(i),i);
                try{
                    jsonArray.put(i,message);
                    //jsonFinal = jsonArray.toString();
                    Log.d("JsonArray",jsonArray.toString());
                    //Log.d("JsonFinal",jsonFinal);
                } catch (JSONException e){
                    e.printStackTrace();
                }
                jObject1.put("Listado",jsonArray);
            }
            jArray1.put(cuentaActual,jObject1);
            jsonObject.put("Orden",jArray1);
            jsonFinal = jsonObject.toString();

            Log.d("JSONEnviar", jsonFinal);
            URL url = new URL(this.url + path);
            Log.i("URL", String.valueOf(url));
            connection = (HttpURLConnection)url.openConnection();
            //connection.setReadTimeout(10000 /*Milliseconds*/);
            //connection.setReadTimeout(15000 /*Milliseconds*/);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setFixedLengthStreamingMode(jsonFinal.getBytes().length);

            connection.setRequestProperty("Content-Type","application/json;charset=utf-8");
            connection.setRequestProperty("X-Requested-With", "XMLHttpRequest");

            connection.connect();

            os = new BufferedOutputStream(connection.getOutputStream());


            os.write(jsonFinal.getBytes());
            os.flush();


            //is = new BufferedInputStream(connection.getInputStream());
            //BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            //is = connection.getInputStream();
//            reader = new JsonReader(rd);
//            String line = "";
//            while ((line = rd.readLine()) != null) {
//                chaine.append(line);
//            }


        }catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }finally {
            try{
                os.close();
                //is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
            connection.disconnect();
        }

        //reader.setLenient(true);
        Log.d("Response:",chaine.toString());

        return parser.parse(chaine.toString());


    }

    public static String toJSON(cuentaInfo.cuentaItem item, int i){
            JSONObject jsonObject = new JSONObject();
            JSONObject jsonAdd = new JSONObject();
            JSONArray jsonArray = new JSONArray();
        try {
            //Here we convert Java Object to JSON
                Log.d("ITEM", item.toString());


                jsonObject.put("Pedido",item.getPedido());
                jsonObject.put("Precio",item.getPrecio());
                jsonObject.put("Cantidad",item.getCantidad());
                jsonObject.put("Extras", item.getExtras());


                jsonAdd.put(String.valueOf(i),jsonObject);
                Log.d("JsonAdd1",jsonAdd.toString());


            Log.d("JsonAdd2",jsonAdd.toString());

            return jsonAdd.toString();


        }catch(JSONException ex){
            ex.printStackTrace();
        }
        return null;

    }

    public Bitmap getImage(String name) throws Exception
    {
        return getImage(name, true);
    }

    public Bitmap getImage(String path, boolean useBaseUrl) throws Exception
    {
        String address = useBaseUrl ? this.url + path : path;
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setDoInput(true);
        connection.connect();
        InputStream input = connection.getInputStream();
        Bitmap myBitmap = BitmapFactory.decodeStream(input);
        return myBitmap;
    }

}

package app.davecstillo.com.cafebar;

import android.graphics.Bitmap;

import java.util.HashMap;

public class Images
{
    public static httpHandler imagesApi;
    public static HashMap<String, Bitmap> images = new HashMap<>();

    public static void init()
    {
        Images.imagesApi = new httpHandler("");
    }

    public static Bitmap get(String name) throws Exception
    {
        if (images.containsKey(name))
            return images.get(name);

        Bitmap b = imagesApi.getImage(name);
        images.put(name, b);
        return b;
    }
}


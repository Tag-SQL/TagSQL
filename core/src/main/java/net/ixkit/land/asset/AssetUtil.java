package net.ixkit.land.asset;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import net.ixkit.land.runtime.Tracer;

public class AssetUtil {

    public static String load(Class<?> contextClass, String shortFileName){

        String buf = null;
        try {

            String fileName = shortFileName;
            URL url =  contextClass.getResource(fileName);
            buf = Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  buf;
    }

    public static String load(String fileName){

        String buf = null;
        try {
            URL url =  Thread.currentThread().getContextClassLoader().getResource(fileName);

            buf = Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            Tracer.e("AssetUtil","failed load",fileName);
            e.printStackTrace();
        }
        return  buf;
    }

    private static String loadSELF(String fileName){

        try {
            return Resources.toString(Resources.getResource(fileName), Charsets.UTF_8);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    public static String getFileAbsolutePath(String shortPath) {
        AssetUtil helper = new AssetUtil();
        URL res = helper.getClass().getClassLoader().getResource(shortPath);
        File file;
        try {
            if (null == res){
                return  null;
            }
            file = Paths.get(res.toURI()).toFile();
            String absolutePath = file.getAbsolutePath();
            return absolutePath;
        } catch (Exception e) {

            e.printStackTrace();
        }

        return shortPath;

    }
}

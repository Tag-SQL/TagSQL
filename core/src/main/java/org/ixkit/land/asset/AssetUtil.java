package org.ixkit.land.asset;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.apache.commons.io.IOUtils;
import org.ixkit.land.runtime.Tracer;

public class AssetUtil {

    public static String loadBaseClass(Class<?> contextClass, String shortFileName){

        String buf = null;
        try {

            String fileName = shortFileName;
            InputStream ins =  contextClass.getResourceAsStream(fileName);
            if (null == ins){
                Tracer.warning("AssetUtil","Failed getResource", fileName);
                return null;
            }
            buf =   IOUtils.toString(ins, Charsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  buf;
    }

    public static String load(String fileName){

        String buf = null;
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream ins =  classLoader.getResourceAsStream(fileName);
            if (null == ins){
                Tracer.warning(AssetUtil.class,"Failed load",classLoader, fileName);
                return "";
            }

           // buf = Resources.toString(ins, Charsets.UTF_8);
            buf =   IOUtils.toString(ins, Charsets.UTF_8.name());
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

    private static String getFileAbsolutePath(String shortPath) {
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

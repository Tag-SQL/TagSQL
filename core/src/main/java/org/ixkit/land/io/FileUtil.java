package org.ixkit.land.io;

import org.ixkit.land.utils.StringUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileUtil {

    public static String load(String filePath)
    {
        File file = new File(filePath);

        String content = null;
        try {
            content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

         return  content;
    }

    public static String getExtension(String filename) {

        return FilenameUtils.getExtension(filename);

    }

    public static String getWithoutExtension(String filename) {

        return FilenameUtils.removeExtension(filename);

    }
    // a.b.c.list.sql ==> a/b/c/list.sql
    public static String formatDotPath(String filePath){
        if (StringUtil.isEmpty(filePath)) return filePath;
        String extension = FileUtil.getExtension(filePath);
        String buf = FileUtil.getWithoutExtension(filePath);
        String result = buf.replace(".", File.separator);
        return result + "." + extension;
    }
}

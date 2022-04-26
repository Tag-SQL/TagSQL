package org.ixkit.land.io;
import java.io.FileInputStream;
import java.util.Scanner;

import org.ixkit.land.lang.Listener;

/**
 *
 * @Author: RobinZ iRobinZhang@hotmail.com
 * @Date: 01/07/2021 14:21
 * @Version
 * @Purpose:
 */



public class StringStream {

    public static String read(String buf, Listener onReadLine) {
        StringBuffer sb = new StringBuffer();
        FileInputStream fis = null;
        Scanner sc = null;
        try {

            sc = new Scanner(buf); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
                String lineString = sc.nextLine();

                if(null!= onReadLine) {
                    lineString =(String) onReadLine.onEvent( lineString);
                }
                sb.append(lineString);

                if (sc.hasNext()) {
                    sb.append("\n");
                }

            }
            sc.close(); // closes the scanner
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(fis, sc);
        }
        return sb.toString();
    }

    public static void readFile(String filePath, Listener onReadLine) {
        StringBuffer sb = new StringBuffer();
        FileInputStream fis = null;
        Scanner sc = null;
        try {
            // the file to be opened for reading
            fis = new FileInputStream(filePath);
            sc = new Scanner(fis,"UTF-8"); // file to be scanned
            // returns true if there is another line to read
            while (sc.hasNextLine()) {
                String lineString = sc.nextLine();
                if(null!= onReadLine) {
                    lineString = (String)onReadLine.onEvent( lineString);
                }
                sb.append(lineString);

            }
            sc.close(); // closes the scanner
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(fis, sc);
        }
    }

    private static void close(FileInputStream fis, Scanner sc) {
        try {
            if (null != fis) {
                fis.close();
            }
            if (null != sc) {
                sc.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

}

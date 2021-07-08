/**
 * Copyright © 2020-2021 The tagsql Authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.ixkit.land.lang;

import org.apache.commons.lang3.StringUtils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project: tagsql
 * Package: net.ixkit.land.lang
 * @Type: Strings.java
 * @Author: RobinZ iRobinZhang@yahoo.com
 * @Date: 01/07/2021 14:29
 * @Version
 *
 * @Purpose:
 */


public class Strings {

    public static String trim(String value) {
        return safe(value).trim();
    }

    public static String safe(String value) {
        if (null == value) {
            return "";
        }
        return value;
    }
    public static String asComma(List list){
        if (1>0){
            return StringUtils.join( list,",");
        }
        if (null == list || list.isEmpty()){
            return null;
        }
        StringBuilder buf = new StringBuilder();
        for(Object o : list){
            buf.append(o);
            buf.append(",");
        }
        return buf.toString();
    }

    public static String prettyText(String value){
        if(StringUtils.isEmpty(value)){
            return value;
        }

        String result = value.replace("\n", "").replace("\r", "").replace("\t", "");
        return result;

    }

    public static String[] readAbleString(String value){
        if(StringUtils.isEmpty(value)){
            return null;
        }
        String[] linesArr =  value.split("[\\r?\\n|\\r]+"); // value.split("\\r?\\n|\\r");
        //System.out.println(Arrays.toString(linesArr));
        return linesArr;
        //return Arrays.toString(linesArr);

    }


    public static double toDouble(String value) {
        if (StringUtils.isEmpty(value)) {
            return 0;
        }
        try {
            return Double.parseDouble(value);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static long toLong(String value) {
        if (StringUtils.isEmpty(value)) {
            return 0;
        }
        try {
            return Long.parseLong(value);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static int toInt(String value) {
        if (StringUtils.isEmpty(value)) {
            return 0;
        }
        try {
            return Integer.parseInt(value);
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }


    public static String toString(Object value) {
        if (null == value) {
            return null;
        }
        String result = null;
        do {
            //@step

            if (value instanceof String) {
                result =(String)value;
            }
            //@step TODO
            result = value.toString();
        }while(false);

        return result;
    }

    public static String toCommaString(List list) {
        if (null == list || list.size()<=0) {
            return "";
        }

        return toCsv(list);

    }

    public static List<String> commaStr2List(String commaSeparatedStr)
    {
        String[] commaSeparatedArr = commaSeparatedStr.split("\\s*,\\s*");
        List<String> result = new ArrayList<String>(Arrays.asList(commaSeparatedArr));
        return result;
    }

    public static String toCsv(final List<?> list) {
        if (null == list || list.size()<=0) {
            return "";
        }
        return toString(list, ',');
    }

    public static String toString(final List<?> list, char delimiter) {
        final StringBuilder b = new StringBuilder();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                b.append(list.get(i).toString());
                if (i != list.size() - 1) {
                    b.append(delimiter);
                }
            }
        }
        return b.toString();
    }






    public static String replaceLastWith(String target, String regex, String replacement) {
        if (StringUtils.isEmpty(target)) {
            return target;
        }
        if (!target.endsWith(regex)) {
            return target;
        }
        if (null == replacement) {
            return target;
        }
        return target.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);

    }
    /**
     * <br>替换字符串里最后一次出现的 子字符串
     * @param string 原生字符串
     * @param match 匹配的字符串
     * @param replace 要替换的字符串
     * @return

     */
    private static String replaceLast(String string, String match, String replace) {
        if (StringUtils.isEmpty(string) || null == replace ) {
            //参数不合法，原样返回
            return string;
        }

        StringBuilder sBuilder = new StringBuilder(string);
        int lastIndexOf = sBuilder.lastIndexOf(match);
        if (-1 == lastIndexOf) {
            return string;
        }

        return sBuilder.replace(lastIndexOf, lastIndexOf + match.length(), replace).toString();
    }


    public static boolean equal (Object from, Object to) {
        if (null == from && null == to) {
            return true;
        }
        if (null == from ) {
            return false;
        }
        if (null == to) {
            return false;
        }
        return from.equals(to);
    }



}

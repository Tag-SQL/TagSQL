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
package org.ixkit.sql;


import org.ixkit.land.lang.KeyValuePair;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @Class:Parameters
 * @Author: RobinZ iRobinZhang@hotmail.com
 * @Date: 02/07/2021
 * @Version:0.1.0
 * @Purpose:Parameters
 */
public class Parameters {

    private HashMap map;

    public int size() {
        if (null == map) return 0;
        return map.size();
    }



    public Parameters set(String key, Object value) {
        if (null == map) {
            map = new HashMap();
        }
        if (null == key ) {
            return this;
        }
        map.put(key, value);
        return this;
    }

    public String fill(String buf) {
        if (null == map) {
            return buf;
        }
        Set set = map.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            Object value = map.get(key);
            buf = replace(buf, key, value);
        }
        return buf;
    }

    private static String asAtKey(String key){
        return "@{" + key + "}";
    }

    public static String replace( String buf, String simpleKey, Object value) {
        do {
            String key = asAtKey( simpleKey);

            if (null == value) {
                buf = buf.replace( key, "null");
                break;
            }

            if (value instanceof String) {
                buf = buf.replace(key, "'" + value + "'");
                break;
            }
            if (value instanceof Number) {
                buf = buf.replace(key,   value.toString() );
                break;
            }
            buf = buf.replace(key,   value.toString() );
        } while (false);

        return buf;

    }


    /**
     *  
     * @param buf  :
     * @param keyValues :
     * @return
     *
     * @example fill( ' id = {a} AND name = {b} ',  'a', 999, 'b', 'robin' ) => ' id = 999 AND name = 'robin' '
     */
    public static String as(String buf, Object ... keyValues) {
        String result = buf;
        if (StringUtils.isEmpty(result)) {
            return result;
        }
        Parameters parameters = new Parameters();
        int size = keyValues.length;
        if (size<=0) {
            return buf;
        }
        if (size % 2 != 0) {

        }
        int j = 0 ;
        for (int i = 0; i < size; i++) {
            j = i + 1;
            Object key = keyValues[i];
            Object value = j < size ? keyValues[j]: null;
            if (null == key  ){
                // ignore it
            }else {
                // key should be string, so then pick it directly
                parameters.set((String)key, value);
            }
            i = i + 1;

        }

        return parameters.fill(result);

    }


    public static Parameters keyValues2Parameters(Object ... keyValues) {

        if (null == keyValues || keyValues.length <=0) {
            return null;
        }

        Parameters parameters = new Parameters();
        int size = keyValues.length;
        if (size<=0) {
            return null;
        }
        if (size % 2 != 0) {
            
        }
        int j = 0 ;
        for (int i = 0; i < size; i++) {
            j = i + 1;
            Object key = keyValues[i];
            Object value = j < size ? keyValues[j]: null;
            if (null == key  ){
                // ignore it
            }else {
                // key should be string, so then pick it directly
                parameters.set((String)key, value);
            }
            i = i + 1;

        }
        return parameters;

    }


    public static List<KeyValuePair> asKeyValueList(Parameters parameters){

        if (null == parameters || parameters.map == null) {
            return null;
        }
        Map map = parameters.map;
        List result = new LinkedList();
        Set set = parameters.map.keySet();
        for (Iterator iterator = set.iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            Object value = map.get(key);
            KeyValuePair item = new KeyValuePair();
            item.setName(key);
            item.setValue(value);
            result.add(item);
        }

        return result;

    }
}

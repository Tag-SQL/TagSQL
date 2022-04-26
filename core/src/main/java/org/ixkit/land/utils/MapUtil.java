/**
 * Copyright © 2020-2021 The sqljs Authors
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
package org.ixkit.land.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @class:MapUtil
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 16/07/2021 
 * @version:0.1.0
 * @purpose:
 */
public class MapUtil {

    public static Object[] map2Array(Map map){
        if (null == map || map.size()<=0){
            return null;
        }
        Object[] r = new Object[map.size()*2];
        Set entries = map.keySet();
        Iterator iterator = entries.iterator();

        int i = 0;
        while(iterator.hasNext()){

            Object key =   iterator.next();

            r[i] = key;
            r[i+1] = map.get(key);

            i = i + 2;
        }
        return  r;
    }

    // 'key','value', 'number',111 ==> map
    public static HashMap keyValues2Map(Object ... keyValues) {
        HashMap result = new HashMap();
        if (null == keyValues) return  result;
        int size = keyValues.length;
        if (size<=0) return  result;


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
                result.put(key,value);
            }
            i = i + 1;

        }
        return result;

    }

}

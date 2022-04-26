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
package org.ixkit.land.json;

import org.json.JSONArray;

import java.util.Arrays;

/**
 * @class:Strings
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 07/07/2021 
 * @version:0.1.0
 * @purpose:
 */
public class Strings {
    public static String array2Json(Object[] arr) {
        return new JSONArray(Arrays.asList(arr)).toString();
    }

    public static String asJsonString(Object obj) {
        String return_value = "";
        if (obj.getClass().equals(String.class))
            return_value = "'" + (String) obj + "'";
        else if (obj.getClass().equals(Integer.class))
            return_value = Integer.toString(((int) obj));
        else if (obj.getClass().equals(Object[].class))
            return_value = array2Json((Object[]) obj);

        return return_value;
    }
}

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
package org.ixkit.land.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @class:StringUtil
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 02/07/2021 
 * @version:0.1.0
 * @purpose:StringUtil
 */
public class StringUtil {

    public static boolean isEmpty(String value){
        return StringUtils.isEmpty(value);
    }
}

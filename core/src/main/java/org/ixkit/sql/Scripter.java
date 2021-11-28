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

import org.ixkit.land.runtime.Tracer;
import org.ixkit.land.utils.StringUtil;


/**
 * @class:Scripter
 * @author: RobinZ iRobinZhang@yahoo.com
 * @date: 02/07/2021 
 * @version:0.1.0
 * @purpose:Scripter
 */
public enum  Scripter {

    where("${WHERE}"),
    orderBy("${ORDER BY}"),

    ;

    private String token;


    Scripter(String token) {
        this.token = token;
    }


    public String as(String template, String value) {

        if (StringUtil.isEmpty(template)) {
            return "";
        }
        if (null == value) {
            value = "";
        }
        String result = template.replace(token, value);

        return result;
    }

    public String fillParameters(Parameters parameters, String buf ) {

        if (null == parameters) {
            return buf;
        }
        buf = parameters.fill(buf);
        Tracer.debug(this,"toSql?", buf);
        return buf;
    }


}

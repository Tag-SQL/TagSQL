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
package net.ixkit.tagsql.core;

import org.apache.commons.lang3.StringUtils;

/**
 * @class:Syntax
 * @author: RobinZ iRobinZhang@yahoo.com
 * @date: 02/07/2021 
 * @version:0.1.0
 * @purpose:Syntax
 */
public class Syntax {

    public enum Keyword {
        SQLComment("-- ","SQLComment" ),

        ScriptToken ("-- $","ScriptToken" ),

        Line( "@Line", "Line"),

        ;



        private String token;
        private String name;



        Keyword(String token, String name) {
            this.token = token;
            this.name = name;
        }

        public String getToken() {
            return this.token;
        }

        public String as(String buf, String asWith) {
            if (StringUtils.isEmpty(buf)) {
                return buf;
            }
            return buf.replace(this.token, "\""+ asWith + "\"");
        }
    }
}

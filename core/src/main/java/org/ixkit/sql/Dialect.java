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
package org.ixkit.sql;

/**
 * @class:Dialect
 * @author: RobinZ iRobinZhang@yahoo.com
 * @date: 05/07/2021 
 * @version:0.1.0
 * @purpose:Dialect
 */
public enum Dialect {
    Basic("basic"),
    H2("h2"),
    MySQL("mysql"),
    Oracle("oracle"),
    ;
    private String space;

    Dialect(String space){
        this.space = space;
    }
    public String getSpace(){
        return this.space;
    }
}

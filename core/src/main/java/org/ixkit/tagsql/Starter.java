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
package org.ixkit.tagsql;

import org.ixkit.land.asset.AssetUtil;

/**
 * @class:Starter
 * @author: RobinZ iRobinZhang@yahoo.com
 * @date: 19/07/2021 
 * @version:0.1.0
 * @purpose:
 */
public  class Starter   {
    static{
        System.out.println("-----------------------------------");
        String buf = AssetUtil.load("tagsql_logo.txt");
        System.out.println(buf);
        System.out.println("-----------------------------------");
        System.out.println(":: TagSQL 🚀 ::          " + getVersion()) ;

    }
    public static void  go(){

    }
    public static String getVersion(){
        String v = "0.1.0";
        return "v("+ v + ")";
    }

}

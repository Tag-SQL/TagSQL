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
package net.ixkit.tagsql.js;

import net.ixkit.land.asset.AssetUtil;
import net.ixkit.land.io.FileUtil;

import java.io.File;

/**
 *
 * @Type: ScriptLib.java
 * @Author: RobinZ iRobinZhang@yahoo.com
 * @Date: 01/07/2021 14:47
 * @Version
 * @Purpose:
 */
public enum ScriptLib {

    lib(  "lib", "js" + File.separator + "lib.js"),
    ;

    private String name;
    private String path;

    ScriptLib(  String name, String path) {

        this.name = name;
        this.path = path;
    }

    public String getPath(){
        String fromPath = "sql"+ File.separator + this.path;
        return fromPath;

    }

    public String getFullPath() {
        return AssetUtil.getFileAbsolutePath(getPath());
    }

    public String getContent(){
        String fromPath = getPath();
        String template  = FileUtil.load(fromPath);
        return template;
    }
}

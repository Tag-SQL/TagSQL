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
package org.ixkit.tagsql.demo.sql;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.ixkit.land.asset.AssetUtil;
import org.ixkit.sql.Dialect;

import java.io.File;
import java.net.URL;

/**
 * @class:DemoTagSql
 * @author: RobinZ iRobinZhang@yahoo.com
 * @date: 02/07/2021
 * @version:0.1.0
 * @purpose:
 */
public enum ManualProvider {

    list("list", "list.sql");

    private String name;
    private String fileName;

    ManualProvider(String name, String fileName) {
        this.name = name;
        this.fileName = fileName;
    }

    private String getDialectSpace() {
        return Dialect.Basic.getSpace();
    }

    private String getSpace() {
        return getSpace(false);
    }

    private String getSpace(boolean outsideSelf) {
        if (!outsideSelf) {
            return this.getDialectSpace() + File.separator;
        }
        // resources/tagsql/[module name]/sql/
        String result = "tagsql" + File.separator + "demo" + File.separator + "sql" + File.separator + getSpace();
        return result;
    }

    private String getPath(String fileName, boolean outsideSelf) {
        return getSpace(outsideSelf) + fileName;
    }


    private String getLocalSql() {

        String result = null;
        try {
            String path = getPath(this.fileName, false);

            URL url = this.getClass().getResource(path);
            result = Resources.toString(url, Charsets.UTF_8);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String getTagSql() {
        if (1 > 0) {
            return getLocalSql();
        }

        String fullPath = getPath(this.fileName, true);
        String buf = null;
        try {
            buf = AssetUtil.load(fullPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return buf;
    }


}

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
package net.ixkit.tagsql.core;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Class:RuntimeOption
 * @Author: RobinZ iRobinZhang@yahoo.com
 * @Date: 01/07/2021 14:59
 * @Version:0.1.0
 * @Purpose:Runtime Option
 */
public class RuntimeOption {


    private boolean parameterRepeat = false;

    private List<String> refScriptFiles = new ArrayList();

    public void putRefScripts(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return;
        }
        refScriptFiles.add(filePath);
    }

    public String[] getRefScripts() {
        return (String[]) refScriptFiles.toArray(new String[0]);
    }


}

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
package org.ixkit.tagsql.js;


import org.ixkit.tagsql.core.RuntimeOption;

/**
 *
 * @Type: JavaScriptorFactory.java
 * @Author: RobinZ iRobinZhang@yahoo.com
 * @Date: 01/07/2021 14:51
 * @Version
 * @Purpose:
 */
public class JavaScriptorFactory {

    public static JavaScriptor getEngineer() {
        RuntimeOption options = new RuntimeOption();
      // options.putRefScripts(ScriptLib.lib.getPath());
        options.putRefScripts(ScriptLib.lib.getContent());
        return getEngineer(options);
    }

    public static JavaScriptor getEngineer(RuntimeOption options) {
        String[] scripts = options.getRefScripts();
      ///  System.setProperty("nashorn.args","--no-deprecation-warning");
        return new JavaScriptor(scripts, null);
    }
}

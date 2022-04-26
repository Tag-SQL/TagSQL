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
package org.ixkit.asset;

import org.ixkit.asset.properties.meta.aop.PropertyLoader;
import org.ixkit.asset.properties.meta.PropertySource;
import org.ixkit.asset.properties.meta.PropertySource.Property;
import org.ixkit.asset.properties.meta.aop.PropertiesProcessor;

/**
 * @class:SQLProvider
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 09/07/2021 
 * @version:0.1.0
 * @purpose:
 */

@PropertySource(source="config/provider.properties", target=SQLProvider.class)
public class SQLProvider implements PropertiesProcessor {

    @Property(key="tagSQL.basePackage")
    public String basePackage;

    @Property(key="tagSQL.mode")
    public String mode;



    public void processProperties() {
        @SuppressWarnings("unused")
        PropertyLoader pl = new PropertyLoader(this);
    }
}
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
package net.ixkit.tagsql.springboot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @class:Options
 * @author: RobinZ iRobinZhang@yahoo.com
 * @date: 19/07/2021 
 * @version:0.1.0
 * @purpose:
 */

@ConfigurationProperties(prefix = "tag-sql", ignoreInvalidFields=true)
public class Options {
    private String basePackage;
    private String mode;

    public String getBasePackage() {
        return basePackage;
    }

    public Options setBasePackage(String basePackage) {
        this.basePackage = basePackage;
        return  this;
    }

    public String getMode() {
        return mode;
    }

    public Options setMode(String mode) {
        this.mode = mode;
        return this;
    }
}
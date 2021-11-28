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
package org.ixkit.tagsql.springboot.factory;

import org.ixkit.land.runtime.Tracer;
import org.ixkit.tagsql.meta.aop.Interceptor;

import java.lang.reflect.Field;

/**
 * @class:SQLSecurityInterceptor
 * @author: RobinZ iRobinZhang@yahoo.com
 * @date: 17/07/2021 
 * @version:0.1.0
 * @purpose:
 */
public class SQLSecurityInterceptor implements Interceptor {
    @Override
    public boolean onBeforeLoad(Field[] fields, Object[] args) {
        Tracer.debug(this,"onBeforeLoad",fields);
        return false;
    }

    @Override
    public boolean onAfterLoad(Field[] fields, Object[] args) {
        Tracer.debug(this,"onAfterLoad",fields  );

        return false;
    }
}

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
package net.ixkit.land.reflect;

import net.ixkit.land.lang.Listener;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @class:MethodUtil
 * @author: RobinZ iRobinZhang@yahoo.com
 * @date: 16/07/2021 
 * @version:0.1.0
 * @purpose:
 */
public class MethodUtil {


    public static Object[] extractArguments(Method method,  Listener callback) {
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        if (parameterAnnotations == null || parameterAnnotations.length == 0) {
            return null;
        }
        Object[] result = new Object[parameterAnnotations.length];
        int i = 0;
        for (Annotation[] parameterAnnotation : parameterAnnotations) {
            for (Annotation annotation : parameterAnnotation) {
                if (null == callback){
                    result[i++] =  annotation;
                    continue;
                }
                //
                Object value = callback.onEvent(annotation);
                result[i++]= value;

            }
        }
        return result;
    }
}

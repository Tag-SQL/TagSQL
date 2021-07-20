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
package net.ixkit.tagsql.meta.aop;

import net.ixkit.land.asset.AssetUtil;
import net.ixkit.land.io.FileUtil;
import net.ixkit.land.runtime.Tracer;
import net.ixkit.land.utils.MapUtil;
import net.ixkit.tagsql.Starter;
import net.ixkit.tagsql.core.TagSQLEngine;
import net.ixkit.tagsql.meta.TagSQL;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @class:Builder
 * @author: RobinZ iRobinZhang@yahoo.com
 * @date: 15/07/2021
 * @version:0.1.0
 * @purpose:
 */
public class Builder implements InvocationHandler {

    static {
        Starter.go();
    }

    private Interceptor interceptor;

    public Interceptor getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(Interceptor interceptor) {
        this.interceptor = interceptor;
    }


    //    private Object targetObject;
//
//    public Builder(Object targetObject)
//    {
//        this.targetObject = targetObject;
//    }
    private Class<?> contextClass;

    public Class<?> getContextClass() {
        return contextClass;
    }

    public void setContextClass(Class<?> contextClass) {
        this.contextClass = contextClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        if (!method.isAnnotationPresent(TagSQL.class)) {
            return method.invoke(proxy, args);
        }

        //@TODO
        Object[] args2 = null != args && args.length == 1 ? (Object[]) args[0] : args;

        Map<String, Object> argumentMap = arg2Map(method, args2);
        onBeforeLoad(method, args);
        String buf = loadTagSQL(method);
        onAfterLoad(method, args, buf);

        String sql = toPureSQL(buf, argumentMap);

        return sql;
    }

    private Map<String, Object> arg2Map(Method method, Object[] args) {

        return MapUtil.keyValues2Map(args);
    }


    private String loadTagSQL(Method method) throws Exception {
        if (!method.isAnnotationPresent(TagSQL.class)) {
            return null;
        }
        TagSQL tagSQL = method.getAnnotation(TagSQL.class);

        return readAssetFile(tagSQL, method);
    }


    private String readAssetFile(TagSQL tagSQL, Method method) throws Exception {
        String result = null;
        String templateSql = tagSQL.value();
        if (StringUtils.isNotEmpty(templateSql)) {
            result = templateSql;
            return result;
        }

        String filePath = tagSQL.file();
        filePath = FileUtil.formatDotPath(filePath);
        if (StringUtils.isEmpty(filePath)) {
            throw new Exception("Invalidate file path");
        }
        if (tagSQL.inResources()) {
            result = AssetUtil.load(filePath);
        } else {
            result = AssetUtil.loadBaseClass(this.contextClass, filePath);
        }

        return result;
    }

    private void onBeforeLoad(Method method, Object[] args) {
        if (interceptor == null) {
            return;
        }
        Object obj = null;
        Field[] fields = null;
        if (args != null && args.length == 1) {
            String methodName = method.getName();
            obj = args[0];
            fields = obj.getClass().getDeclaredFields();
        }
        interceptor.onBeforeLoad(fields, args);
    }

    private void onAfterLoad(Method method, Object[] args, String tagSQL) {
        if (interceptor == null) {
            return;
        }
        Object obj = null;
        Field[] fields = null;
        if (args != null && args.length == 1) {
            String methodName = method.getName();
            obj = args[0];
            fields = obj.getClass().getDeclaredFields();
        }
        interceptor.onBeforeLoad(fields, args);
    }

    private String toPureSQL(String templateSql, Map<String, Object> parameters) throws Exception {

        Tracer.debug(this, "toPureSQL?", parameters);
        Object[] args = MapUtil.map2Array(parameters);

        String sql = TagSQLEngine.invoke(templateSql).fill(args).getSQL();

        return sql;
    }
}

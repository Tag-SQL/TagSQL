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

import org.ixkit.tagsql.meta.aop.Builder;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * @class:TagSQLFactory
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 16/07/2021 
 * @version:0.1.0
 * @purpose:
 */

public class TagSQLFactory<T> implements FactoryBean<T> {

    private Class<T> providerInterface;

    private Builder proxy;

    @Override
    public T getObject() throws Exception {
        return newInstance();
    }

    @Override
    public Class<?> getObjectType() {
        return providerInterface;
    }

    public Builder getProxy() {
        return proxy;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @SuppressWarnings("unchecked")
    private T newInstance() {
        proxy.setContextClass(this.providerInterface);
      return (T) Proxy.newProxyInstance(providerInterface.getClassLoader(), new Class[]{providerInterface}, proxy);


//
//        final Object myObject = /*initialize the proxy target*/;
//        final Object proxy = Proxy.newProxyInstance(
//                providerInterface.getClassLoader(),
//                new Class[] { /*your interface(s)*/ },
//                new InvocationTargetHandler() {
//                    public Object invoke(Object proxy, Method method, Object[] args) {
//                        return method.invoke(myObject, args);
//                    }
//                });

    }

    public void setProxy(Builder proxy) {
        this.proxy = proxy;
    }

    public void setProviderInterface(Class<T> providerInterface) {
        this.providerInterface = providerInterface;
    }
}
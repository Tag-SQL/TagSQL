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
import org.ixkit.tagsql.meta.aop.Builder;
import org.ixkit.tagsql.meta.aop.Interceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * @class:ProvidrConfig
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 16/07/2021 
 * @version:0.1.0
 * @purpose:
 */


public class ProviderConfigProcessor implements BeanDefinitionRegistryPostProcessor {
    public static String TagSQLBuilder =  "TagSQLBuilder";
    private String[] basePackage;

    private Class<? extends Annotation> annotation = Repository.class;

    private Interceptor interceptor = new SQLSecurityInterceptor();

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        registerRequestProxyHandler(registry);
        Arrays.stream(this.basePackage).forEach(x->{
            ClassPathScanner scanner = new ClassPathScanner(registry, annotation);
            Tracer.debug(this,"Start scan 🔍 [" + x +"].......");
            scanner.scan(StringUtils.tokenizeToStringArray(x, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
        });

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    private void registerRequestProxyHandler(BeanDefinitionRegistry registry) {
        GenericBeanDefinition definition = new GenericBeanDefinition();
        definition.setBeanClass(Builder.class);
        definition.getPropertyValues().add("interceptor", interceptor);
        registry.registerBeanDefinition(TagSQLBuilder, definition);
    }

    public void setAnnotation(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    public void setBasePackage(String[] basePackage) {
        this.basePackage = basePackage;
    }

    public Interceptor getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(Interceptor interceptor) {
        this.interceptor = interceptor;
    }
}
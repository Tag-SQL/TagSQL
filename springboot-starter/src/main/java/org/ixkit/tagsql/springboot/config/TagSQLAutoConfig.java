package org.ixkit.tagsql.springboot.config;

import org.ixkit.land.runtime.Tracer;
import org.ixkit.land.utils.StringUtil;
import org.ixkit.tagsql.meta.aop.Interceptor;
import org.ixkit.tagsql.springboot.config.meta.EnableTagSQLAutoConfig;
import org.ixkit.tagsql.springboot.factory.ProviderConfigProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Configuration
@ConditionalOnBean(annotation = EnableTagSQLAutoConfig.class)
@EnableConfigurationProperties(Options.class)
public class TagSQLAutoConfig {

    private static String[] toBasePackage( Options options, boolean autoScanAll){
        if(null == options ||  options.getBasePackage() == null ||  options.getBasePackage().length <=0) {
            if ( autoScanAll) {
                return new String[]{"*"};
            }else{
                return null;
            }
        }
        return options.getBasePackage();
    }
    @Bean
    @ConditionalOnMissingBean
    public static ProviderConfigProcessor provider(@Autowired ApplicationContext context, @Autowired List<Options> options) {
        boolean autoScanAll = null == options || options.size()<=1;
        ArrayList<String> arrayList = new ArrayList<String>();
       options.stream().forEach(x->{
            String[] packages = toBasePackage(x,false);
            if (null != packages){
                Arrays.stream(packages).forEach(y->{
                    arrayList.add(y);
                });
            }
        });
        Options mergedOptions = new Options();

        String arrNew[] = new String[arrayList.size()];
        for(int i = 0; i < arrNew.length; i++)
            arrNew[i] = arrayList.get(i);

        mergedOptions.setBasePackage(arrNew);

        ProviderConfigProcessor result = new ProviderConfigProcessor() ;

        result.setBasePackage(toBasePackage(mergedOptions,false));

        result.setInterceptor(new Interceptor() {
            @Override
            public boolean onBeforeLoad(Field[] fields, Object[] args) {
                Tracer.debug(this,"onBeforeLoad",fields);
                return false;
            }

            @Override
            public boolean onAfterLoad(Field[] fields, Object[] args) {
                Tracer.debug(this,"onAfterLoad",fields);

                return false;
            }
        });
        return result;
    }



}
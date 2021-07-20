package net.ixkit.tagsql.springboot.config;

import net.ixkit.land.runtime.Tracer;
import net.ixkit.land.utils.StringUtil;
import net.ixkit.tagsql.meta.aop.Interceptor;
import net.ixkit.tagsql.springboot.config.meta.EnableTagSQLAutoConfig;
import net.ixkit.tagsql.springboot.factory.ProviderConfigProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Field;

@Configuration
@ConditionalOnBean(annotation = EnableTagSQLAutoConfig.class)
@EnableConfigurationProperties(Options.class)
public class TagSQLAutoConfig {

    private static String getBasePackage( Options options){
        if(null == options || StringUtil.isEmpty(options.getBasePackage()) ) {
            return "*";
        }
        return options.getBasePackage();
    }
    @Bean
    @ConditionalOnMissingBean
    public static ProviderConfigProcessor provider(@Autowired ApplicationContext context, @Autowired Options options) {

        ProviderConfigProcessor result = new ProviderConfigProcessor() ;

        result.setBasePackage(getBasePackage(options));

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
package net.ixkit.tagsql.demo.config;

import net.ixkit.land.runtime.Tracer;

import net.ixkit.tagsql.meta.aop.Interceptor;
import net.ixkit.tagsql.springboot.config.Options;
import net.ixkit.tagsql.springboot.factory.ProviderConfigProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;

import java.lang.reflect.Field;

@Configuration
public class MyTagSQLConfig implements PriorityOrdered {

    @Bean
    public static Options options() {
        return new Options().setBasePackage("net.ixkit.tagsql.demo");
    }

    // not auto config then
    //@Bean
    public ProviderConfigProcessor provider() {
        ProviderConfigProcessor result = new ProviderConfigProcessor() ;
        result.setBasePackage("net.ixkit.tagsql.demo");
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


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
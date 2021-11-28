package org.ixkit.asset.properties.meta;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface PropertySource {
    String source();
    Class<?> target();
    
    @Target(value = ElementType.FIELD)
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface Property {
        String key();
    }
}

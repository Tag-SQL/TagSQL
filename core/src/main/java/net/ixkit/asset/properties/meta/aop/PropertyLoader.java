package net.ixkit.asset.properties.meta.aop;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;
import net.ixkit.asset.properties.meta.PropertySource;
import net.ixkit.asset.properties.meta.PropertySource.Property;

public class PropertyLoader {


    public PropertyLoader(Object obj) {
        try {
            loadProperties(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PropertyLoader(Object obj, String source) {
        try {
            loadProperties(obj, source);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadProperties(Object obj) throws InstantiationException, IllegalAccessException {
        loadProperties(obj, null);
    }
    
    public void loadProperties(Object obj, String source) throws InstantiationException, IllegalAccessException {
        PropertySource ps = obj.getClass().getAnnotation(PropertySource.class);
        if (obj.getClass().isAnnotationPresent(PropertySource.class)) {
            
            Properties props;
            Field[] fields = obj.getClass().getFields();
            if (ps.source().endsWith(".lng") && null!=source && !source.trim().equals("")) {
                props = readProperties(obj.getClass(),source);
            } else {
                props = readProperties(obj.getClass(),ps.source());
            }
            
            
            for (Field f : fields) {
                if (f.isAnnotationPresent(Property.class)) {
                    Property property = f.getAnnotation(Property.class);
                    f.set(obj, props.get(property.key()));
                }
            }
        }
    }


    protected Properties readProperties(Class clazz,String filename)   {
        Properties prop = new Properties();

        try {
                InputStream input = clazz.getClassLoader().getResourceAsStream(filename)  ;

                if (input == null) {
                    System.out.println("Sorry, unable to find " + filename);
                    return null;
                }

            prop.load(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return prop;
    }

}

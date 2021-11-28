package org.ixkit.land.reflect;


import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;


public class Reflector {

	public static boolean set(Object object, String fieldName, Object fieldValue) {
	    Class<?> clazz = object.getClass();
	    while (clazz != null) {
	        try {
	            Field field = clazz.getDeclaredField(fieldName);
	            field.setAccessible(true);
	            field.set(object, fieldValue);
	            return true;
	        } catch (NoSuchFieldException e) {
	            clazz = clazz.getSuperclass();
	        } catch (Exception e) {
	            throw new IllegalStateException(e);
	        }
	    }
	    return false;
	}
	
	public static Object get(Object object, String fieldName ) {
	    Class<?> clazz = object.getClass();
	    while (clazz != null) {
	        try {
	            Field field = clazz.getDeclaredField(fieldName);
	            return get(object, field);
	        } catch (NoSuchFieldException e) {
	            clazz = clazz.getSuperclass();
	        } catch (Exception e) {
	            throw new IllegalStateException(e);
	        }
	    }
	    return false;
	}
	
	public static boolean set(Object object, Field field, Object fieldValue) {
	    Class<?> clazz = object.getClass();
	    while (clazz != null) {
	        try {
	           
	            field.setAccessible(true);
	            field.set(object, fieldValue);
	            return true;
	        
	        } catch (Exception e) {
	            throw new IllegalStateException(e);
	        }
	    }
	    return false;
	}

	public static Object get(Object object, Field field) {
	    Class<?> clazz = object.getClass();
	    while (clazz != null) {
	        try {
	           
	            field.setAccessible(true);
	           
	            return  field.get(object);
	        
	        } catch (Exception e) {
	            throw new IllegalStateException(e);
	        }
	    }
	    return false;
	}
	

	public static List getFields(Class clazz) {
		List resultList = new LinkedList();
		for (Field f : clazz.getDeclaredFields()) {
			resultList.add(f);
		}
		return resultList;
	}
	//@@##
	public static HashMap getFieldValues(Object object) {
		HashMap map = new HashMap();
        if(null == object) {
        	return map;
        }
        Field[] fields = object.getClass().getDeclaredFields();
		final List<Field> allFields = Arrays.asList(fields);
        for (Iterator iterator = allFields.iterator(); iterator.hasNext();) {
			Field field = (Field) iterator.next();
			Object valueObject = get(object, field);
			map.put(field.getName(), valueObject);		
		}
        return map;
	}
	
}

package org.ixkit.asset.properties.meta.aop;

public interface Property {
    <P extends Property> P propertyFactory();
}
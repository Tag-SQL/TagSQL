package org.ixkit.orm.meta;


public class TableColumn {
    private String name;
    private boolean named;
    private Class<?> type;
    private boolean primary;


    public TableColumn(String name, Class<?> type) {
        this.name = name;
        this.type = type;
    }
    public TableColumn() {
    }

    public boolean isNamed() {
        return named;
    }

    public TableColumn setNamed(boolean named) {
        this.named = named;
        return this;
    }

    public boolean isPrimary() {
        return primary;
    }

    public TableColumn setPrimary(boolean primary) {
        this.primary = primary;
        return this;
    }

    public String getName() {
        return name;
    }

    public TableColumn setName(String name) {
        this.name = name;
        return this;
    }

    public Class<?> getType() {
        return type;
    }

    public TableColumn setType(Class<?> type) {
        this.type = type;
        return this;
    }
}


package net.ixkit.orm;


import net.ixkit.land.reflect.Reflector;
import net.ixkit.orm.meta.ColumnName;
import net.ixkit.orm.meta.TableColumn;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Object2SQL {


    private Class clazz;
    private List<TableColumn> fields;

    private Object2SQL bind(Class clazz) {
        this.clazz = clazz;
        this.fields = getFields(clazz);
        return this;
    }

    public static Object2SQL with(Class clazz){
        Object2SQL result = new Object2SQL();
        result.bind(clazz);

        return result;
    }

    public String toCreateSQL(){
        return this.toCrateTable(this.clazz.getName(),this.fields);
    }

    public String toInsertSQL(Object value){
        String tableName = this.clazz.getSimpleName();
        return this.toInsertSQL(tableName,this.fields,value);
    }

    private String toInsertSQL(String tableName, List<TableColumn> fields, Object valueObject) {
        String tableNameSql = String.format("INSERT INTO `%s` ", tableName);

        StringBuilder fieldBuilder = new StringBuilder();
        StringBuilder valueBuilder = new StringBuilder();
        boolean isLast = false;
        for (int i = 0; i < fields.size(); i++) {
            TableColumn field = fields.get(i);
            String columnName = asTableColumnName(field);
            fieldBuilder.append(String.format( "`%s`",columnName));
            isLast = i + 1 >= fields.size();
            if (!isLast){
                fieldBuilder.append(",");
            }
            //
            String value = getColumnValue(valueObject,field.getName());
            valueBuilder.append(value);
            if (!isLast){
                valueBuilder.append(",");
            }
        }

        return String.format("%s (%s) VALUES (%s)", tableNameSql, fieldBuilder.toString(),valueBuilder.toString());
    }

    private String getColumnValue(Object valueObject,String fieldName){
        Object value =  Reflector.get(valueObject,fieldName);

        if (null == value){
            return "null";
        }
        if (value instanceof String){
            return String.format( "\'%s\'",value.toString());
        }
        if (value instanceof Date){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = formatter.format(value);
            return String.format( "\'%s\'",dateString);
        }
        return  value.toString();
    }

    private String getClassName(String clazz) {
        Pattern pattern = Pattern.compile("(?:class)(.+)(?:\\{)");
        Matcher matcher = pattern.matcher(clazz);
        return matcher.find() ? matcher.group(1).trim() : "";
    }



    private String getPackageClass(String clazz) throws ClassNotFoundException {
        switch (clazz) {
            case "String":
                return "java.lang.String";
            case "int":
                return "java.lang.Integer";
            case "Date":
                return "java.util.Date";
            default:
                throw new ClassNotFoundException();
        }
    }

    private List<TableColumn> getFields(Class c) {
        Field[] declaredFields = c.getDeclaredFields();
        List<TableColumn> columns = new ArrayList<>();
        for (Field f : declaredFields) {
            ColumnName name = f.getDeclaredAnnotation(ColumnName.class);

            TableColumn column = new TableColumn();

            if (name != null) {
                column.setName(name.value())
                        .setNamed(true)
                        .setType(f.getType());
            } else {
                column.setName(f.getName())
                        .setType(f.getType());
            }
            columns.add(column);
        }
        return columns;
    }

    private String toCrateTable(String tableName, List<TableColumn> tableColumns) {
        String tableNameSql = String.format("CREATE TABLE `%s`", tableName);

        StringBuilder fieldSQLBuilder = new StringBuilder();
        List<String> primaries = new ArrayList<>();
        tableColumns.forEach(field -> {
            String fieldName = asTableColumnName(field);
            String fieldTypeName = typeConvert(field.getType().getName());
            if (field.isPrimary()) { // if the key is primary key, it will be added to list for the next handle.
                primaries.add(fieldName);
            }
            fieldSQLBuilder.append(
                    String.format("`%s` %s NOT NULL,",
                            fieldName,
                            fieldTypeName));
        });
        handlePrimaries(fieldSQLBuilder, primaries);

        return String.format("%s (%s);", tableNameSql, fieldSQLBuilder.toString());
    }

    private void handlePrimaries(StringBuilder fieldSQLBuilder, List<String> primaries) {
        if (primaries.size() == 0) {
            fieldSQLBuilder.append("PRIMARY KEY (`id`)");
        }
        primaries.forEach(primaryName ->
                fieldSQLBuilder.append(
                        String.format("PRIMARY KEY (`%s`)", primaryName)));
    }

    private String asTableColumnName(TableColumn field) {
        if (field.isNamed()) {
            return field.getName();
        }
        String name = field.getName();
        StringBuilder builder = new StringBuilder(name);
        for (int i = 0; i < builder.length(); i++) {
            char c = builder.charAt(i);
            if (c > 64 && c < 91) {
                char lowerC = (char) (c + 32);
                builder.replace(i, i + 1, "_" + lowerC);
            }
        }
        return builder.toString();
    }

    private String typeConvert(String origin) {
        switch (origin) {
            case "int":
                return "int";
            case "java.lang.String":
                return "varchar(255)";
            case "java.util.Date":
                return "datetime";
            default:
                return "";
        }
    }


}

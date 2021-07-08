package net.ixkit.tagsql.core;

import java.util.Iterator;
import java.util.List;

import net.ixkit.land.io.StringStream;
import net.ixkit.land.lang.KeyValuePair;
import net.ixkit.land.lang.Listener;
import net.ixkit.tagsql.js.JavaScriptorFactory;
import org.apache.commons.lang3.StringUtils;

import net.ixkit.land.runtime.Tracer;

import net.ixkit.sql.Parameters;

import net.ixkit.tagsql.js.JavaScriptor;
import net.ixkit.tagsql.core.Syntax.Keyword;

import javax.script.ScriptException;

public class TagSQLEngine {

    private boolean keepJScript = true;

    private boolean keepOriginalSqlStatement = true;

    private String buf;

    private JavaScriptor engineer;


    public static TagSQLEngine invoke(String template) {
        return getDefault().feed(template);
    }

    public static TagSQLEngine getDefault() {
        TagSQLEngine result = new TagSQLEngine();
        result.engineer = JavaScriptorFactory.getEngineer();
        return result;
    }

    private TagSQLEngine() {

    }


    public TagSQLEngine feed(String buf) {
        this.buf = buf;

        return this;
    }

    private Parameters parameters;

    // key=>value
    public TagSQLEngine fill(Object ... keyValuePairs) {
        parameters = Parameters.keyValues2Parameters(keyValuePairs);
        return this;
    }

    public boolean validate() {
        return false;
    }

    public String getSQL() {

        if (null == this.parameters || this.parameters.size()<=0) {
            return buf;
        }
        //@step
        List list = Parameters.asKeyValueList(parameters);
        String newBuf = this.reformByParameters(list);
        //@step
        if (!buf.equals(newBuf)) {
            newBuf = newBuf + "\n"; // split line
        }

        return newBuf;
    }


    private boolean isJsLine(String line) {
        return line.contains(Keyword.ScriptToken.getToken());
    }

    private String formatByParameters(String line, List keyValues) {
        if (StringUtils.isEmpty(line)) {
            return line;
        }
        String tempLine = line;
        //@step
        for (Iterator iterator = keyValues.iterator(); iterator.hasNext();) {
            KeyValuePair keyValuePair = (KeyValuePair) iterator.next();
            if (!line.contains(keyValuePair.getName())) continue;
            //@step
            tempLine  = keyValuePair.assign(tempLine);
        }
        return tempLine;
    }


    private String formatLineIfNeeds(String line, List paramters) {
        if (isJsLine(line)) {
            return formatByJavascriptFunction(line,paramters);
        }
        return formatByParameters(line,paramters);
    }

    private String formatByJavascriptFunction(String line, List KeyValuePairList) {
        if (!isJsLine(line)) {
            return line;
        }
        String ScriptToken = Keyword.ScriptToken.getToken();
        String buf = line.replace(ScriptToken,"$$$");
        String[] parts = buf.split("[\\$$$]+", -1);
        String sqlPart = parts[0];
        String jsPart = parts[1];
        if (StringUtils.isEmpty(sqlPart) || StringUtils.isEmpty(jsPart)) {
            return line;
        }
        //@step
        for (Iterator iterator = KeyValuePairList.iterator(); iterator.hasNext();) {
            KeyValuePair keyValuePair = (KeyValuePair) iterator.next();
            if (!jsPart.contains(keyValuePair.getName())) continue;
             //@step
            jsPart  = keyValuePair.assign(jsPart);
        }
        //@step
        String js =  Keyword.Line.as(jsPart, sqlPart);
        sqlPart = this.invokeJS(js);

        String newLine = sqlPart;

        boolean hiddenLine = StringUtils.isEmpty(newLine);

        if (hiddenLine) {
            newLine ="";
        }
        //@step
        if (this.keepJScript) {
            //keep the original script
            newLine = newLine + ScriptToken  + parts[1];

        }
        //@step
        if (hiddenLine && this.keepOriginalSqlStatement) {
            String crOrNot = StringUtils.isEmpty(newLine) ? "" : "\n";
            newLine = newLine + crOrNot + sqlCommentLine(parts[0]) ;
        }

        return newLine;

    }

    private String sqlCommentLine(String line) {
        return   Keyword.SQLComment.getToken() + line;
    }

    private String reformByParameters(final List KeyValuePairList) {
        if (StringUtils.isEmpty(buf)) {
            return buf ;
        }

        Listener<String> onReadLine = new Listener<String> () {
            @Override
            public String onEvent(String value) {
                return formatLineIfNeeds(value, KeyValuePairList);
            }

        };

        String newBufString = StringStream.read(buf, onReadLine);
        Tracer.debug(this, "reformByParameters? from=>to", buf, newBufString);
        return newBufString;
    }



    private String invokeJS(String js) {
        Tracer.debug(this,"try invokeJS?", js);
        Object result = null;
        try {
            result = this.engineer.run(js);
        }catch (ScriptException e){
            //e.printStackTrace();
            Tracer.warning(this,"Failed run js",e.getMessage());

        }catch (Exception e) {
            Tracer.e(this,"Failed run js",e);
        }
        if (null == result) {
            return null;
        }
        if (result instanceof String) {
            return (String) result;
        }
        return result.toString();
    }
}

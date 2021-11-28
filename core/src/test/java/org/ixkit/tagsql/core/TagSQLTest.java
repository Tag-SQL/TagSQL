package org.ixkit.tagsql.core;

import org.ixkit.land.asset.AssetUtil;
import org.ixkit.land.runtime.Tracer;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class TagSQLTest {

    @Test
    public void testLineFill() {
        String line ="name ='abc' " + Syntax.Keyword.ScriptToken.getToken() + "  " + "lib.replace(@Line,'abc',@{name})";
        String sql = TagSQLEngine.getDefault().feed(line).fill("name","xyz").getSQL();
        String expectLine ="name ='xyz' " ;
        Assert.assertTrue(sql.startsWith(expectLine));
     }

    @Test
    public void testLineHide() {
        String line ="name ='abc' " + Syntax.Keyword.ScriptToken.getToken() + "lib.replace(@Line,'abc',@{name})";
        String sql = TagSQLEngine.getDefault().feed(line).fill("name",null).getSQL();
        String expectLine ="-- name ='abc' " ;
        Assert.assertTrue(sql.contains(expectLine));
    }

    @Test
    public void testDateBetween() {
        String line ="AND A.birthday BETWEEN '1990-1-1' AND '2020-12-30'" + Syntax.Keyword.ScriptToken.getToken()
                + "lib.replace(@Line,'1990-1-1',@{beginDate},'2020-12-30',@{endDate})";
        String sql = TagSQLEngine.getDefault().feed(line).fill("beginDate","1888-1-1", "endDate","2222-12-30").getSQL();
        String expectLine ="BETWEEN '1888-1-1' AND '2222-12-30'" ;
        Assert.assertTrue(sql.contains(expectLine));
    }

    @Test
    public void testDateBetweenNullCase() {
        String line ="AND A.birthday BETWEEN '1990-1-1' AND '2020-12-30'" + Syntax.Keyword.ScriptToken.getToken()
                + "lib.replace(@Line,'1990-1-1',@{beginDate},'2020-12-30',@{endDate})";
        String sql = TagSQLEngine.getDefault().feed(line).fill("beginDate",null, "endDate",null).getSQL();
        String expectLine ="-- AND A.birthday BETWEEN '1990-1-1' AND '2020-12-30'" ;
        Assert.assertTrue(sql.contains(expectLine));
    }

    @Test
    public void testZeroCase() {
        String line ="AND A.score > 1888 " + Syntax.Keyword.ScriptToken.getToken()
                + "lib.replace(@Line,1888,@{score})";
        String sql = TagSQLEngine.getDefault().feed(line).fill("score",0).getSQL();
        String expectLine ="AND A.score > 1888" ;
        Assert.assertFalse(sql.contains(expectLine));
    }

    @Test
    public void testSql() {
        String buf = AssetUtil.load("sql"+ File.separator+ "test" + File.separator+"list.sql");
        String valueOfName = "abcdefghijklmnopqrstuvwxyz!@#$%^&*()";
        Object[] args = {"firstName",valueOfName,"beginDate","1888-1-1", "endDate","2222-12-30", "score",0};
        String sql = TagSQLEngine.getDefault().feed(buf).fill(args).getSQL();
        Tracer.debug(this,"after?",sql);
        Assert.assertTrue(sql.contains(valueOfName));
    }
}

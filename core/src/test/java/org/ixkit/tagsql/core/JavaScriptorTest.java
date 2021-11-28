
package org.ixkit.tagsql.core;

import org.ixkit.tagsql.js.JavaScriptor;

import java.util.HashMap;
import java.util.Map;

public class JavaScriptorTest {


   // @Test
    public  void testLib() throws Exception {

        String[] scripts = { "a.js", "b.js", "main.js", "c.js" };
        Map<String, Object> params = new HashMap<String, Object>() {
            {
                put("key", "val");
                put("key2", new Object[] { new Object[] { 1, 2 }, 10, "abc" });
            }
        };

        String js = "console.log('hello java');";
        Object res = new JavaScriptor(scripts, params).run(js);
        System.out.println(((String[]) res)[0]);
    }
}

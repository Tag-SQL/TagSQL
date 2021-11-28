package org.ixkit.land.runtime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.ixkit.land.lang.Strings;


public class Tracer {

    private static boolean debug = true;
    public static void setDebug(boolean value){
        debug = value;
    }

    public static boolean isDebug(){
        return debug;
    }
    enum Console{
        out,
        err{
            @Override
            public   void print(String buf) {
                System.err.print(buf);

            }
            @Override
            public   void println(String buf) {
                System.err.print(buf +"\n");
            }

        },
        warning{
            @Override
            public   void print(String buf) {
                System.err.print(buf);

            }
            @Override
            public   void println(String buf) {
                System.err.print(buf +"\n");
            }

        }
        ;

        public  void print(String buf) {
            System.out.print(buf);
        }

        public  void println(String buf) {
            System.out.print(buf +"\n");

        }

    }
    private static  Tracer INSTANCE;
    public synchronized static Tracer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Tracer();
        }
        return INSTANCE;
    }

    public static String toPrettyString(Object target){
        if(null == target){
            return "null";
        }
        String result = "";
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            result = gson.toJson(target);
        }catch (Exception e){
           //@ System.err.println("error toPrettyString:" +e.getMessage());
            result = target.toString();
        }
        return result;

    }

    public  void print(String buf) {
        Console.out.print(buf);

    }

    public  void println(String buf) {
        Console.out.println(buf);
    }


    private static void outPrettyString(Console console,String buf) {
        console.println("String:{");
        String[] lines = Strings.readAbleString(buf);
        if(null != lines) {
            for (int i = 0; i < lines.length; i++) {
                console.println(lines[i] );

            }
        }

        console.println("}");
    }

    public void log(Object ...targets){
        System.out.println(targets);
    }

    private static String toBuf(Console console,Object ... targets){
        int count = null == targets? 0 : targets.length;
        StringBuffer buffer = new StringBuffer();
        for(int i =0 ; i < count ; i++){
            Object item = targets[i];
            if (item instanceof String) {
                outPrettyString(console,(String)item);
                continue;
            }
            buffer.append(toPrettyString(item)+",\n");
        }
        String buf = buffer.toString();
        return  buf;
    }
    public static void debug(Object sender, String fmt, Object ... targets  ){
        if (!isDebug()){
            return;
        }
        Console.out.println("T->"+sender +","+ fmt +",");
        String buf = toBuf(Console.out,targets);
        Console.out.println(buf );
    }

    public static void warning(Object sender, String fmt, Object ... targets  ){
        Console.warning.println("⚠️->"+sender +","+ fmt +",");
        String buf = toBuf(Console.warning,targets);
        Console.warning.println(buf );
    }

    public static void e(Object sender, String fmt, Object ... targets  ){
        if (!isDebug()){
            return;
        }

        int count = null == targets? 0 : targets.length;
        StringBuffer buffer = new StringBuffer();
        for(int i =0 ; i < count ; i++){
            buffer.append(toPrettyString(targets[i])+",\n");
        }
        String buf = buffer.toString();
        Console.err.println("🔥->"+sender +","+ fmt +","+ buf);
    }


}

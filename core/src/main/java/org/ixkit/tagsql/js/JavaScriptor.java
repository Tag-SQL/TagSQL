package org.ixkit.tagsql.js;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.ixkit.land.json.Strings;
import org.ixkit.land.runtime.Tracer;

import javax.script.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class JavaScriptor {
	private ArrayList<String> refScripts;
	private Map<String, Object> scriptsParams;
	private FileReader scriptReader;

	private boolean applyScriptsParams = false;

	private ScriptEngine scriptEngine;



	public JavaScriptor(String scriptPath) {
		refScripts = new ArrayList<String>();
		refScripts.add(scriptPath);
	}

	public JavaScriptor(String[] scriptPath) {
		this.refScripts = new ArrayList<String>(Arrays.asList(scriptPath));
	}

	public JavaScriptor(String[] scripts, Map<String, Object> scriptsParams) {
		this.refScripts = new ArrayList<String>(Arrays.asList(scripts));
		this.scriptsParams = scriptsParams;
		applyScriptsParams = true;
	}

	private static String getKeyByIndex(Map<String, Object> hash, int index) {
		int i = 0;
		for (String key : hash.keySet())
			if (i == index)
				return key;
			else
				i++;
		return "";
	}




	private void hookConsole(ScriptEngine js){
		Bindings bindings = js.getBindings(ScriptContext.ENGINE_SCOPE);
	//	bindings.put("console", System.out);
		bindings.put("console", Tracer.getInstance());
	}
	private boolean isReady = false;

	private static ScriptEngine startEngine(){
		ScriptEngineManager sm = new ScriptEngineManager();
		NashornScriptEngineFactory factory = null;
		for (ScriptEngineFactory f : sm.getEngineFactories()) {
			if (f.getEngineName().equalsIgnoreCase("Oracle Nashorn")) {
				factory = (NashornScriptEngineFactory)f;
				break;
			}
		}
		String[] stringArray = new String[]{"-doe", "--global-per-engine"};
		ScriptEngine scriptEngine = factory.getScriptEngine(stringArray);
		return scriptEngine;
	}
	private JavaScriptor prepare() {
		if (null == scriptEngine) {
			scriptEngine =startEngine();// new ScriptEngineManager().getEngineByName("JavaScript");
		}
		if (isReady) {
			return this;
		}
		try {
			hookConsole(scriptEngine);
			//set global parameter 
			if (applyScriptsParams && null != scriptsParams) {
				String buf = "";
				int size = scriptsParams.size();

				scriptEngine.put("param", "{}");
				for (int i = 0; i < size; i++) {
					String key = getKeyByIndex(scriptsParams, i);
					buf += "'" + key + "': " + Strings.asJsonString(scriptsParams.get(key));
					if (i < size - 1)
						buf += ", ";

				}
				scriptEngine.eval("var param = {" + buf + "};");
			}
			//set global functions 
			for (int i = 0; i < refScripts.size(); i++) {
				//scriptReader = new FileReader(refScripts.get(i));
				String script = refScripts.get(i);
				scriptEngine.eval(script);
				// scriptReader.close();
			}
			isReady = true;
		} catch (Exception  e) {
			e.printStackTrace();
		}

		return this;
	}
	
	
	public Object run(String js) throws Exception {
		this.isReady = false;
		this.prepare();
		if (1>10) {
			final CompiledScript compiled = ((Compilable) scriptEngine).compile(js);
			return compiled.eval();
		}
		return this.scriptEngine.eval(js);
	}

}

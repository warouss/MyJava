package com.warouss.script;
import java.io.File;
import java.io.FileReader;
import java.net.URI;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class RunScriptFileDemo {

	public static void main(String[] args) {
		new RunScriptFileDemo().run("script.js");
	}
	
	private void run(String script) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		try {
			URI uri = getClass().getResource(script).toURI();
			File file = new File(uri);
			System.out.println(file);
			FileReader reader = new FileReader(file);
			engine.eval(reader);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

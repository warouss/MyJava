package com.warouss.script;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class RunJavaScript {

	public static void main(String args[]) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("javascript");
		try {
			Double hour = (Double) engine.eval("var date = new Date();"
					+ "date.getHours();");
			String msg;
			if (hour < 10) {
				msg = "Good morning";
			} else if (hour < 16) {
				msg = "Good afternoon";
			} else if (hour < 20) {
				msg = "Good evening";
			} else {
				msg = "Good night";
			}
			System.out.println(hour);
			System.out.println(msg);
		} catch (ScriptException e) {
			System.err.println(e);
		}
	}

}

package com.warouss.apache.commons.pool;

import org.apache.commons.lang3.time.DurationFormatUtils;
import org.apache.commons.lang3.time.StopWatch;

public class Chrono {
	private static int STATIC_ID = 0;
	
	private int id = -1;
	
	private StopWatch chrono = null;
	
	public Chrono() {
		STATIC_ID++;
		id = STATIC_ID;
		System.out.println("new Chrono() id="+id);
		chrono = new StopWatch();
	}
	
	public void start() {
		chrono.start();
	}
	
	public String stop() {
		chrono.stop();
		String duration = DurationFormatUtils.formatDurationHMS(chrono.getTime());
		//chrono.reset();
		return duration;
	}
	
	public String getDuration() {
		String duration = DurationFormatUtils.formatDurationHMS(chrono.getTime());
		chrono.reset();
		return duration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}

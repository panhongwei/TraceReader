package com.panda.trace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormatFile {
	//
	private String version;
	private int versioncode;
	private boolean overflow;
	private String clock;
	private long elapsedTime;
	private int methodNum;
	private int clockCallOverhead;
	private String vm;
	Map<String,String> threads=new HashMap<String, String>();
	Map<Long,MethodDes> methods=new HashMap<Long,MethodDes>();
	public Map getMethods() {
		return methods;
	}
	public void setMethods(Map methods) {
		this.methods = methods;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public int getVersioncode() {
		return versioncode;
	}
	public void setVersioncode(int versioncode) {
		this.versioncode = versioncode;
	}
	public boolean isOverflow() {
		return overflow;
	}
	public void setOverflow(boolean overflow) {
		this.overflow = overflow;
	}
	public String getClock() {
		return clock;
	}
	public void setClock(String clock) {
		this.clock = clock;
	}
	public long getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	public int getMethodNum() {
		return methodNum;
	}
	public void setMethodNum(int methodNum) {
		this.methodNum = methodNum;
	}
	public int getClockCallOverhead() {
		return clockCallOverhead;
	}
	public void setClockCallOverhead(int clockCallOverhead) {
		this.clockCallOverhead = clockCallOverhead;
	}
	public String getVm() {
		return vm;
	}
	public void setVm(String vm) {
		this.vm = vm;
	}

}

package com.panda.trace;

public class TraceRecord {
	int threadId;
	public int getThreadId() {
		return threadId;
	}
	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}
	public long getMethodValue() {
		return methodValue;
	}
	public void setMethodValue(long methodValue) {
		this.methodValue = methodValue;
	}
	public long getThreadClockDiff() {
		return threadClockDiff;
	}
	public void setThreadClockDiff(long threadClockDiff) {
		this.threadClockDiff = threadClockDiff;
	}
	public long getWallClockDiff() {
		return wallClockDiff;
	}
	public void setWallClockDiff(long wallClockDiff) {
		this.wallClockDiff = wallClockDiff;
	}
	long methodValue;
	long threadClockDiff;
	long wallClockDiff;
	MethodDes m;
	public MethodDes getM() {
		return m;
	}
	public void setM(MethodDes m) {
		this.m = m;
	}
	int action;
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public void reNameMethod(String name){
		m.renameMethod(name);
	}
	public void reNameClass(String old,String name){
		m.renameClass(old, name);
		m.renameSig("L"+old+";", "L"+name+";");
	}
}

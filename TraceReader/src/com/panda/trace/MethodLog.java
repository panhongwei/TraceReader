package com.panda.trace;

import java.util.ArrayList;
import java.util.List;

public class MethodLog implements Comparable{
	TraceRecord record;
	public TraceRecord getRecord() {
		return record;
	}
	public void setRecord(TraceRecord record) {
		this.record = record;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
	public MethodLog getParent() {
		return parent;
	}
	public void setParent(MethodLog parent) {
		this.parent = parent;
	}
	public MethodLog getPartner() {
		return partner;
	}
	public void setPartner(MethodLog partner) {
		this.partner = partner;
	}
	public List<MethodLog> getChild() {
		return child;
	}
	public void setChild(List<MethodLog> child) {
		this.child = child;
	}
	String FullName;
	String methodName;
	String source;
	int action;
	MethodLog parent;
	MethodLog partner;
	List<MethodLog> child=new ArrayList<>();
	public MethodLog(){}
	public MethodLog(String name){
		this.parent=null;
		this.methodName=name;
		this.FullName=name;
		this.source="unknown";
		this.action=3;
		this.record=new TraceRecord();
		
	}
	public MethodLog(String name,int action){
		this.parent=null;
		this.methodName=name;
		this.FullName=name;
		this.source="unknown";
		this.action=action;
		this.record=new TraceRecord();
		
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		MethodLog o1=(MethodLog)o;
		return o1.record.threadClockDiff-this.record.threadClockDiff;
	}
}

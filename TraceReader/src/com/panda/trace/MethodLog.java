package com.panda.trace;

import java.util.ArrayList;
import java.util.List;

public class MethodLog implements Comparable{
	TraceRecord record;
	long threadCostTime;
	public long getThreadCostTime() {
		return threadCostTime;
	}
	public void setThreadCostTime(long threadCostTime) {
		this.threadCostTime = threadCostTime;
	}
	public long getWallCostTime() {
		return wallCostTime;
	}
	public void setWallCostTime(long wallCostTime) {
		this.wallCostTime = wallCostTime;
	}
	long wallCostTime;
	public TraceRecord getRecord() {
		return record;
	}
	public String getFullName() {
		return record.m.getMethodDescriptor()+"."+record.m.getMethodName()+record.m.getMethodSig();
	}
	public String getOriginFullName() {
		return record.m.getOldMethodDescriptor()+"."+record.m.getOldMethodName()+record.m.getOldMethodSig();
	}
//	public void setFullName(String fullName) {
//		FullName = fullName;
//	}
	public String getMethodName() {
		return record.m.getMethodName();
	}
//	public void setMethodName(String methodName) {
//		this.methodName = methodName;
//	}
	public String getSource() {
		return record.m.getSource().split("\t")[0];
	}
//	public void setSource(String source) {
//		this.source = source;
//	}
	public int getAction() {
		return record.action;
	}
//	public void setAction(int action) {
//		this.action = action;
//	}
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
	MethodLog parent;
	MethodLog partner;
	List<MethodLog> child=new ArrayList<>();
	public MethodLog(){}
	public MethodLog(String name){
		this.parent=null;
		this.record=new TraceRecord();
		this.record.m=new MethodDes();
		this.record.m.setMethodDescriptor("");
		this.record.m.setMethodName(name);
		this.record.m.setMethodSig("");
		this.record.m.setSource("unknown");;
		this.record.action=3;
	}
	public MethodLog(TraceRecord r){
		this.record = r;
		
	}
	public MethodLog(String name,int action){
		this.parent=null;
//		this.methodName=name;
//		this.FullName=name;
//		this.source="unknown";
//		this.action=action;
		this.record=new TraceRecord();
		this.record.m=new MethodDes();
		this.record.m.setMethodDescriptor("");
		this.record.m.setMethodName(name);
		this.record.m.setMethodSig("");
		this.record.m.setSource("unknown");;
		this.record.action=action;
		
	}
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		if(!(o instanceof MethodLog)){
			throw new ClassCastException("Not MethodLog Class!");
		}
		MethodLog o1=(MethodLog)o;
		return (int) (o1.child.size()-this.child.size());
	}
}

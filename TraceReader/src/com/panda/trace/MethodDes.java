package com.panda.trace;

public class MethodDes {
	private long method;
	private String methodDescriptor;
	private String methodName;
	private String methodSig;
	public String getOldMethodDescriptor() {
		return oldMethodDescriptor;
	}
	public String getOldMethodName() {
		return oldMethodName;
	}
	public String getOldMethodSig() {
		return oldMethodSig;
	}
	private String oldMethodDescriptor;
	private String oldMethodName;
	private String oldMethodSig;
	private String source;
	public long getMethod() {
		return method;
	}
	public void setMethod(long method) {
		this.method = method;
	}
	public String getMethodDescriptor() {
		return methodDescriptor;
	}
	public void setMethodDescriptor(String methodDescriptor) {
		this.methodDescriptor = methodDescriptor;
		this.oldMethodDescriptor=methodDescriptor;
	}
	public String getMethodSig() {
		return methodSig;
	}
	public void setMethodSig(String methodSig) {
		this.methodSig = methodSig;
		this.oldMethodSig=methodSig;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
		this.oldMethodName=methodName;
	}
	public void renameClass(String oldCls,String cls){
		this.methodDescriptor=methodDescriptor.replace(oldCls, cls);
	}
	public void renameSig(String oldCls,String cls){
		this.methodSig=methodSig.replace(oldCls, cls);
	}
	public void renameMethod(String method){
		this.methodName=method;
	}
}

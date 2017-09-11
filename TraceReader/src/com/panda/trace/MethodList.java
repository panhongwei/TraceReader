package com.panda.trace;

public class MethodList {
	private int method;
	private String methodDescriptor;
	private String methodName;
	private String methodSig;
	private String source;
	public int getMethod() {
		return method;
	}
	public void setMethod(int method) {
		this.method = method;
	}
	public String getMethodDescriptor() {
		return methodDescriptor;
	}
	public void setMethodDescriptor(String methodDescriptor) {
		this.methodDescriptor = methodDescriptor;
	}
	public String getMethodSig() {
		return methodSig;
	}
	public void setMethodSig(String methodSig) {
		this.methodSig = methodSig;
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
	}
}

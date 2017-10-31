package com.panda.util;

import java.util.List;

import com.panda.trace.MethodLog;
import com.panda.trace.TraceRecord;

public class MethodUtil {
	public static MethodLog getRealMethod(MethodLog m,String reg){
		MethodLog topMethod=new MethodLog(m.getFullName());
		if(m.getFullName().contains(reg)){
			topMethod.getChild().add(m);
			return topMethod;
		}
		List<MethodLog> list=m.getChild();
		for(MethodLog m1:list){
			getMethod(m1,topMethod,reg);
		}
		return topMethod;
	}
	public static void getMethod(MethodLog m,MethodLog topMethod,String reg){
		if(m.getFullName().contains(reg)){
			topMethod.getChild().add(m);
			return;
		}else{
			for(MethodLog m1:m.getChild()){
				getMethod(m1,topMethod,reg);
			}
		}
	}
	public static int getChildNum(MethodLog log){
		int sum=log.getChild().size();
		if(sum==0){
			return sum;
		}
		for(MethodLog m1:log.getChild()){
			sum=sum+getChildNum(m1);
		}
		return sum;
	}
//	public static String getJavaSig(TraceRecord r){
//		if(r.getM()==null){
//			return null;
//		}
//		String sig=r.getM().getMethodSig();
//		return anlysisSig(sig);
//	}
	public static String anlysisSig(String sig){
		if(!sig.startsWith("(")){
			return null;
		}
		char[] sigChars=sig.toCharArray();
		int i=0;
		String javaSig="";
		i++;
		while(sigChars[i]!=')'){
			char c=sigChars[i++];
			String prim=getPrimitiveString(c);
			if(prim.equals("")){
				if(c=='L'){
					while(sigChars[i]!=';'){
						javaSig=javaSig+sigChars[i];
						i++;
					}
					javaSig=javaSig+";";
					i++;
					continue;
				}else{
					javaSig=javaSig+c;
					while(sigChars[i]=='['){
						javaSig=javaSig+sigChars[i];
						i++;
					}
					if(sigChars[i]=='L'){
						i++;
						javaSig=javaSig+"L";
						while(sigChars[i]!=';'){
							javaSig=javaSig+sigChars[i];
							i++;
						}
						javaSig=javaSig+";";
						i++;
						continue;
					}else{
						javaSig=javaSig+sigChars[i++]+";";
						continue;
					}
				}
			}else{
				javaSig=javaSig+prim+";";
			}
		}
		return javaSig;
	}
	public static String getPrimitiveString(char c){
		switch(c){
			case 'I':{
				return "int";
			}
			case 'B':{
				return "byte";
			}
			case 'S':{
				return "short";
			}
			case 'J':{
				return "long";
			}
			case 'D':{
				return "double";
			}
			case 'C':{
				return "char";
			}
			case 'F':{
				return "float";
			}
			case 'Z':{
				return "boolean";
			}
			default:{
				return "";
			}
		}
	}
	public static boolean isPrimitiveString(String s){
		if(s.equals("int")||s.equals("byte")||s.equals("short")||s.equals("long")||s.equals("double")||s.equals("char")
				||s.equals("float")||s.equals("boolean")){
			return true;
		}else{
			return false;
		}
	}
}

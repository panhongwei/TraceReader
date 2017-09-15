package com.panda.util;

import java.util.List;

import com.panda.trace.MethodLog;

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
}

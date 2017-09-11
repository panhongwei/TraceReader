package com.panda.trace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Threads {
	//List<MethodLog> methods=new ArrayList<>();
	public List<String> names=new ArrayList<>();
	Map<String,TraceThread> threads=new HashMap<>();
	List<MethodList> methodList=new ArrayList<>();
	public List<MethodList> getMethodList() {
		return methodList;
	}
	public void setMethodList(List methodList) {
		this.methodList = methodList;
	}
	public Map<String, TraceThread> getThreads() {
		return threads;
	}
	public void setThreads(Map<String, TraceThread> threads) {
		this.threads = threads;
	}
	@SuppressWarnings("unchecked")
	public Threads(Trace trace) throws Exception{
		this.threads=trace.threads;
		this.names=trace.names;
		for(Long it:trace.fmFile.methods.keySet()){
			methodList.add(trace.fmFile.methods.get(it));
		}
		
		Collections.sort(names,new Comparator<String>() {
            public int compare(String o1, String o2) {
                return Integer.parseInt(o1)-Integer.parseInt(o2);
            }
        });
		for (String key : threads.keySet()) {
			threads.get(key).sortMethods();
		}
		//Collections.sort(methods);
	}
}

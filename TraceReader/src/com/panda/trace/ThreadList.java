package com.panda.trace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThreadList {
	public List<String> names;
	Map<String,TraceThread> threads;
	protected Map<String,TraceRecord> nameMap;
	boolean sort;
	public Map<String, TraceRecord> getNameMap() {
		return nameMap;
	}
	public Map<String, TraceThread> getThreads() {
		return threads;
	}
	public void setThreads(Map<String, TraceThread> threads) {
		this.threads = threads;
	}
	@SuppressWarnings("unchecked")
	protected ThreadList() throws Throwable{
		names=new ArrayList<>();
		threads=new HashMap<>();
		nameMap=new HashMap<>();
		sort=true;
	}
	protected void sort(){
		if(sort){
			sort=false;
			for (String key : threads.keySet()) {
				threads.get(key).sortMethods();
			}
		}
	}
	public List<String> getAllName(){
		 List<String> nameList = new ArrayList<String>();  
		 nameList.addAll(nameMap.keySet()); 
	     return nameList;
	}
	public void reset(){
		names.clear();
		threads.clear();
		nameMap.clear();
	}
}

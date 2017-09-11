package com.panda.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.JList;

import com.panda.trace.Threads;
import com.panda.trace.TraceThread;

@SuppressWarnings("serial")
public class ThreadJListModel extends AbstractListModel{
	private TraceFrame trf;
	public ThreadJListModel(TraceFrame trf){
		this.trf=trf;
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
//		return 5;
		if(trf.traceThreads==null){
			return 0;
		}
		return trf.traceThreads.names.size();
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		//return "aaaa";
		if(trf.traceThreads==null){
			return null;
		}
//		System.out.println(trf.traceThreads.getThreads().size());
//		if(trf.traceThreads.)
//		if(trf.jcb.getSelectedItem().toString().equals("线程列表")){
//			 List<Map.Entry<String,TraceThread>> list = new ArrayList<Map.Entry<String,TraceThread>>(trf.traceThreads.getThreads().entrySet());
//		}else if(trf.jcb.getSelectedItem().toString().equals("方法列表")){
//			
//		}
		return trf.traceThreads.names.get(index)+"\t"
		+trf.traceThreads.getThreads().get(trf.traceThreads.names.get(index)).getName()+"("+
		trf.traceThreads.getThreads().get(trf.traceThreads.names.get(index)).getMethods().size()+")";
	}

}

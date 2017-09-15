package com.panda.ui.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JList;

import com.panda.trace.ThreadList;
import com.panda.trace.TraceThread;
import com.panda.ui.TraceFrame;

@SuppressWarnings("serial")
public class ThreadJListModel extends AbstractListModel{
	private TraceFrame trf;
	private String filter="";
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public ThreadJListModel(TraceFrame trf){
		this.trf=trf;
//		System.out.println(trf.getTraceThreads().names.size());
	}
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		if(trf.getTraceThreads()==null){
			return 0;
		}
		return trf.getTraceThreads().names.size();
	}

	@Override
	public Object getElementAt(int index) {
		// TODO Auto-generated method stub
		if(trf.getTraceThreads()==null){
			return null;
		}
		String name=trf.getTraceThreads().names.get(index)+"\t"
				+trf.getTraceThreads().getThreads().get(trf.getTraceThreads().names.get(index)).getName()+"("+
				trf.getTraceThreads().getThreads().get(trf.getTraceThreads().names.get(index)).getMethods().size()+")";
		//JLabel label=new JLabel(name);
		return name;
	}

}

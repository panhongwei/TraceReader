package com.panda.ui.list;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JList;

import com.panda.trace.MethodLog;
import com.panda.trace.TraceThread;
import com.panda.ui.TraceFrame;

public class ThreadListExt extends JList{
	TraceFrame frame;
	ThreadJListModel model;
	public ThreadListExt(TraceFrame frame){
		this.frame=frame;
		model=new ThreadJListModel(frame);
		this.setModel(model);
		this.addMouseListener(new ThreadMouseAdapter(frame, this));
	}
	public String getSelectedName(){
		if(frame.getTraceThreads()==null||frame.getTraceThreads().names==null){
			return "";
		}
		return frame.getTraceThreads().names.get(this.getSelectedIndex());
	}
	public void deleteSelectedName(String name){
		if(frame.getTraceThreads()==null||frame.getTraceThreads().names==null){
			return ;
		}
		frame.getTraceThreads().names.remove(name);
		this.updateUI();
		return;
	}
	public void filterToThread(String reg){
		model.setFilter(reg);
		frame.addFilterName(reg);
		this.updateUI();
	}
	public ThreadJListModel getModel(){
		return model;
	}
}

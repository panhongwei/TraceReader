package com.panda.ui.list;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import com.panda.trace.MethodLog;
import com.panda.trace.TraceThread;
import com.panda.ui.TraceFrame;
import com.panda.ui.menu.ThreadListPopupMenu;

public class ThreadMouseAdapter extends MouseAdapter{
	TraceFrame frame;
	ThreadListExt thread;
	ThreadListPopupMenu pop;
	public ThreadMouseAdapter(TraceFrame frame,ThreadListExt thread){
		this.frame=frame;
		this.thread=thread;
		pop=new ThreadListPopupMenu(thread);
	}
	public void mouseClicked(MouseEvent e) {

		// TODO Auto-generated method stub
		if(e.getClickCount()==2){
			if(frame.getTraceThreads()==null||frame.getTraceThreads().names==null){
				return;
			}
			String name=thread.getSelectedName();
			frame.extendMethod(name,thread.getModel().getFilter());			
		}else if(e.isMetaDown()){
			Component c=thread.findComponentAt(e.getX(), e.getY());
			pop.setFocus(c);
			pop.show(e.getComponent(), e.getX(), e.getY());
		}
		
	}
}

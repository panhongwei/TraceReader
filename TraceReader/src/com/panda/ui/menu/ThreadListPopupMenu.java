package com.panda.ui.menu;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import com.panda.ui.TraceFrame;
import com.panda.ui.list.ThreadListExt;

public class ThreadListPopupMenu extends JPopupMenu{
	ThreadListExt thread;
	private JMenuItem copy = null, filter = null, delete = null;
	Component focus;
	public Component getFocus() {
		return focus;
	}
	public void setFocus(Component focus) {
		this.focus = focus;
	}
	public ThreadListPopupMenu(ThreadListExt thread){
		this.thread=thread;
	    this.add(copy = new JMenuItem("复制"));  
	    this.add(filter = new JMenuItem("过滤"));  
//	    this.add(delete = new JMenuItem("删除")); 
	    copy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name=ThreadListPopupMenu.this.thread.getSelectedName();
				Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();  
		        Transferable tText = new StringSelection(name);  
//		        System.out.println(name);
		        clip.setContents(tText, null); 
			}
		});
	    filter.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String filterStr=JOptionPane.showInputDialog(ThreadListPopupMenu.this.thread,"输入过滤字符串：");
				if(filterStr==null){
					return;
				}
				ThreadListPopupMenu.this.thread.filterToThread(filterStr);
			}
		});
//	    delete.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				String name=ThreadListPopupMenu.this.focus.getName();
//				 System.out.println(name);
//				 ThreadListPopupMenu.this.thread.deleteSelectedName(name);
//			}
//		});
//	    this.setVisible(true);
	}
}

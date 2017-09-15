package com.panda.ui.tree;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

import com.panda.trace.MethodLog;

public class AbstractNode extends DefaultMutableTreeNode{
	  protected MethodLog m; 
	boolean showTime=false;
	  public boolean isShowTime() {
		return showTime;
	}
	public void setShowTime(boolean showTime) {
		this.showTime = showTime;
	}
	public MethodLog getM() {
		return m;
	}
	public void setM(MethodLog m) {
		this.m = m;
	}
	public AbstractNode(MethodLog m)
	  {
		  super();
		  this.m=m;
	  }
	  public AbstractNode(Icon icon,MethodLog m)
	  {
		  super();
		  this.m=m;
	  }
		 public String getText()
		 {
			 return this.m.getFullName();
		 }  
}

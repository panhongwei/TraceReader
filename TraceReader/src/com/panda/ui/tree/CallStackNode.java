package com.panda.ui.tree;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

import com.panda.trace.MethodLog;

public class CallStackNode extends AbstractNode{
	  private boolean needTab=false;
	  int childNum;

	  public int getChildNum() {
		return childNum;
	}

	public void setChildNum(int childNum) {
		this.childNum = childNum;
	}

	public boolean isNeedTab() {
		return needTab;
	  }

	  public void setNeedTab(boolean needTab) {	
		this.needTab = needTab;
	  }
	  public CallStackNode(MethodLog m)
	  {
		  super(m);
		  this.setAllowsChildren(true);
	  }
	  public CallStackNode(MethodLog m,int childNum)
	  {
		  super(m);
		  this.childNum=childNum;
		  this.setAllowsChildren(true);
	  }
	  
	  //包含文本和图片的节点构造
	  public CallStackNode(Icon icon,MethodLog m)
	  {
		  super(icon,m);
		  this.setAllowsChildren(true);
	  }
}

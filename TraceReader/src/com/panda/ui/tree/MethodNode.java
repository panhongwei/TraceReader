package com.panda.ui.tree;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

import com.panda.trace.MethodLog;

public class MethodNode extends AbstractNode{
	  private boolean needTab=false;
	  private boolean rootNode=false;
	  int pos=0;
	  public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public boolean isRootNode() {
		return rootNode;
	}

	private boolean needExpend=true;

	  public void setNeedExpend(boolean needExpend) {
		this.needExpend = needExpend;
	}

	public boolean isNeedExpend() {
		return needExpend;
	  }

	public boolean isNeedTab() {
		return needTab;
	  }

	  public void setNeedTab(boolean needTab) {	
		this.needTab = needTab;
	  }
	  public MethodNode(MethodLog m)
	  {
		  super(m);
		  this.setAllowsChildren(true);
	  }
	  public MethodNode(MethodLog m,int n)
	  {
		  super(m);
		  this.pos=n;
		  rootNode=true;
		  this.setAllowsChildren(true);
	  }
	  //包含文本和图片的节点构造
	  public MethodNode(Icon icon,MethodLog m)
	  {
		  super(icon,m);
		  this.setAllowsChildren(true);
	  }  
}	

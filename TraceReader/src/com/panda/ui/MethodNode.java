package com.panda.ui;

import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;

public class MethodNode extends DefaultMutableTreeNode{
	  protected Icon icon;  
	  protected String txt;  
	  private boolean selected=false;
	  private boolean needTab=false;

	  public boolean isNeedTab() {
		return needTab;
	}

	public void setNeedTab(boolean needTab) {
		this.needTab = needTab;
	}

	public boolean isSelected() {
		  return selected;
	  }

	  public void setSelected(boolean selected) {
		  this.selected = selected;
	  }

	//只包含文本的节点构造
	  public MethodNode(String txt)
	  {
		  super();
		  this.txt=txt;
	  }  
	  
	  //包含文本和图片的节点构造
	  public MethodNode(Icon icon,String txt)
	  {
		  super();
		  this.icon = icon;  
		  this.txt = txt;
	  }
	 public void setIcon(Icon icon) 
	 {   
	    this.icon = icon;   
	 }   
	  
	 public Icon getIcon() 
	 {   
		 return icon;   
	  } 
	 
	 public void setText(String txt)
	 {
		 this.txt=txt;
	 }
	 
	 public String getText()
	 {
		 return txt;
	 }    
}	

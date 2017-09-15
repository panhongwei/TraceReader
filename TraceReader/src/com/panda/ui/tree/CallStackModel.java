package com.panda.ui.tree;

import java.util.List;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import com.panda.trace.MethodLog;
import com.panda.util.MethodUtil;

public class CallStackModel extends DefaultTreeModel{
	public CallStackModel(TreeNode root) {
		super(root);
		// TODO Auto-generated constructor stub
	}
	public void addNode(CallStackNode node,MethodLog log){
		int i=0;
		for(MethodLog m1:log.getChild()){
			CallStackNode node1=new CallStackNode(m1,MethodUtil.getChildNum(m1));
			this.insertNodeInto(node1, node, i++);
			if(m1.getChild().size()==0){
				//System.out.println(m1.getFullName()+">>>>no child!");
				continue;
			}else{
				addNode(node1,m1);
			}
		}
	}

}

package com.panda.ui;

import java.util.List;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import com.panda.trace.MethodLog;

public class ListTreeModel extends DefaultTreeModel{
	private int where=-1;
	public int getWhere() {
		return where;
	}
	public void setWhere(int where) {
		this.where = where;
	}
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private List<MethodLog> methods;
	public List<MethodLog> getMethods() {
		return methods;
	}
	public void setMethods(List<MethodLog> methods) {
		this.methods = methods;
	}
	public ListTreeModel(TreeNode root) {
		super(root);
		// TODO Auto-generated constructor stub
	}

}

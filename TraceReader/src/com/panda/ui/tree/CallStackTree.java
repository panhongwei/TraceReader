package com.panda.ui.tree;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import com.panda.trace.MethodLog;
import com.panda.trace.TraceThread;
import com.panda.ui.TraceFrame;
import com.panda.ui.menu.TreePopupMenu;
import com.panda.util.MethodUtil;

public class CallStackTree extends JTree{
	TraceFrame frame;
	public TraceFrame getFrame() {
		return frame;
	}
	private CallStackModel treeModel;
	static final CallStackNode ROOT = new CallStackNode(new MethodLog("Method CallStack:")); 
	static String mtReg="";
	static int times;
	private String name;
	TreePopupMenu pop;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CallStackTree(TraceFrame frame){
		this.frame=frame;
		treeModel=new CallStackModel(ROOT);
		this.setModel(treeModel);
		pop=new TreePopupMenu(this);
		this.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.isMetaDown()){
					pop.show(e.getComponent(), e.getX(), e.getY());
					pop.setFocus(CallStackTree.this.getPathForLocation( e.getX(),e.getY()));
				}
			}
		});
	}
	public void fillTree(String reg){
		if(name==null||name==""){
			return;
		}
		ROOT.removeAllChildren();
		MethodLog top=TraceThread.topMethod;
		CallStackNode node=new CallStackNode(top);
		treeModel.insertNodeInto(node, ROOT, 0);
		int i=0;
		for(MethodLog m:top.getChild()){
			if(m.getRecord().getThreadId()==Integer.parseInt(name)){
				if(reg!=null&&!reg.equals("")){
					m=MethodUtil.getRealMethod(m,reg);
				}
				if(m.getChild().size()==0){
					continue;
				}
				CallStackNode md=new CallStackNode(m,MethodUtil.getChildNum(m));
				
				//System.out.println(m.getFullName()+" "+top.getChild().size()+" "+i);
				treeModel.insertNodeInto(md, node, i++);
				treeModel.addNode(md,m);
			}
		}
		treeModel.reload();
	}
	public void extendTreeMode(String reg){
		 if(mtReg.equals(reg)){
			 times++;
		 }else{
			 mtReg=reg;
			 times=1;
		 }
		 int n=times;
		 Enumeration<CallStackNode> enums=ROOT.preorderEnumeration();
		 while(enums.hasMoreElements()&&n>0){
			 CallStackNode node=(CallStackNode) enums.nextElement();
			 if(node.getText().contains(reg)){
				 this.addSelectionPath(new TreePath(node.getPath()));
				 n--;
			 }
		 }
	}
	public void extendMethod(String reg){
		fillTree(reg);
	}
}

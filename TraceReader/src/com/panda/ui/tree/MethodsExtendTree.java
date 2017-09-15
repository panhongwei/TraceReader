package com.panda.ui.tree;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JTree;

import com.panda.trace.MethodLog;
import com.panda.trace.TraceThread;
import com.panda.ui.TraceFrame;

public class MethodsExtendTree extends JTree{
	TraceFrame frame;
	public TraceFrame getFrame() {
		return frame;
	}
	private MethodsExtendModel mModel;
	static final MethodNode ROOT = new MethodNode(new MethodLog("Methods List:")); 
	public final static int MAX=2000;
	public MethodsExtendTree(TraceFrame frame){
		super();
		this.frame=frame;
		mModel=new MethodsExtendModel(ROOT);
		this.setModel(mModel);
		this.addKeyListener(new ExtendTreeKeyAdapter(this));
		this.addMouseListener(new ExtendTreeMouseAdapter(this));
	}
	protected void selectMMode(List<MethodLog> methods,String reg){
		 Enumeration<MethodNode> enums=ROOT.children();
		 while(enums.hasMoreElements()){
			 MethodNode node=(MethodNode) enums.nextElement();
			 if(node.getText().contains(reg)){
				 node.setNeedTab(true);
				 //System.out.println(node.getText());
			 }else{
				 node.setNeedTab(false);
			 }
		 }
	}
	//一次最多加载2000节点
	public void reloadMMode(List<MethodLog> methods,int fromWhen,String reg){
		int i=0;
		if(methods==null){
			return;
		}
		if(fromWhen==-1){
			return;
		}
		if(reg!=null&reg!=""){
			List methods_=new ArrayList();
			for(i=0;i<methods.size();++i){
				MethodLog m=methods.get(i);
				if(m.getFullName().equals(TraceThread.noPart.getFullName())){
					m=m.getPartner();
				}
				if(!m.getFullName().contains(reg)){
					continue;
				}
				methods_.add(m);
			}
			methods=methods_;
		}
		if(fromWhen==0)
			ROOT.removeAllChildren();
		int min,max;
		if(MAX>=methods.size()){
			min=0;
			max=methods.size();
			mModel.setWhere(-1);
//			System.out.println(methods.size()+" "+mModel.getWhere());
		}else{
			min=fromWhen*MAX;
			max=(fromWhen+1)*MAX;
			if(max>=methods.size()){
				max=methods.size();
				mModel.setWhere(-1);
			}else{
				mModel.setWhere(fromWhen+1);
			}
		}
		int n=min;
		for(i=min;i< max;++i){
			MethodLog m=methods.get(i);
			if(m.getFullName().equals(TraceThread.noPart.getFullName())){
				m=m.getPartner();
			}
			if(!m.getFullName().contains(reg)){
				continue;
			}
			MethodNode mm=new MethodNode(m,n);
			mModel.insertNodeInto(mm,ROOT,n++);
			MethodNode parent=new MethodNode(new MethodLog("===parent==="));
			MethodNode child=new MethodNode(new MethodLog("===child==="));
			mModel.insertNodeInto(parent, mm, 0);
			mModel.insertNodeInto(child, mm, 1);
		}
		mModel.reload();
	}
	public void reloadMMode(int  where,String reg){
		ROOT.removeAllChildren();
		reloadMMode(mModel.getMethods(),where,reg);
		mModel.reload();
	}
}

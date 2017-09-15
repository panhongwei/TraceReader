package com.panda.ui.tree;

import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.panda.ui.menu.ThreadListPopupMenu;
import com.panda.ui.menu.TreePopupMenu;

public class ExtendTreeMouseAdapter extends MouseAdapter{
	MethodsExtendTree mTree;
	TreePopupMenu pop;
	public ExtendTreeMouseAdapter(MethodsExtendTree mTree){
		this.mTree=mTree;
		pop=new TreePopupMenu(mTree);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getClickCount()==2){
			MethodNode note = (MethodNode) mTree.getLastSelectedPathComponent();
			TreePath p=mTree.getSelectionPath();
			if(note.isRootNode()&&note.isNeedExpend()){
				note.setNeedExpend(false);
				MethodNode n0=(MethodNode)note.getChildAt(0);
				MethodNode n1=(MethodNode)note.getChildAt(1);
				if(n0.getText().equals("===parent===")){
					MethodNode mp=new MethodNode(note.m.getParent());
					((DefaultTreeModel) mTree.getModel()).insertNodeInto(mp, n0, 0);
				}
				if(n1.getText().equals("===parent===")){
					MethodNode mp=new MethodNode(note.m.getParent());
					((DefaultTreeModel) mTree.getModel()).insertNodeInto(mp, n1, 0);
				}
				if(n0.getText().equals("===child===")){
					for(int j=0;j<note.m.getChild().size();++j){
						MethodNode mj=new MethodNode(note.m.getChild().get(j));
						((DefaultTreeModel) mTree.getModel()).insertNodeInto(mj, n0, j);
					}
				}
				if(n1.getText().equals("===child===")){
					for(int j=0;j<note.m.getChild().size();++j){
						MethodNode mj=new MethodNode(note.m.getChild().get(j));
						((DefaultTreeModel) mTree.getModel()).insertNodeInto(mj, n1, j);
					}
				}
			}
			if(note.getChildCount()==0){
				if(note.getText().equals("===parent===")||note.getText().equals("===child===")){
					return;
				}
				MethodsExtendModel model=(MethodsExtendModel)mTree.getModel();
				mTree.selectMMode(model.getMethods(),note.getText());
				model.reload();
				mTree.addSelectionPath(p);;
			}
			
		}if(e.isMetaDown()){
			pop.show(e.getComponent(), e.getX(), e.getY());
			pop.setFocus(mTree.getPathForLocation( e.getX(),e.getY()));
		}
	}
}

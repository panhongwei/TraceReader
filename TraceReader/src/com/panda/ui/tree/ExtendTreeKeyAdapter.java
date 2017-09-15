package com.panda.ui.tree;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.tree.TreePath;

public class ExtendTreeKeyAdapter extends KeyAdapter{
	MethodsExtendTree mTree;
	public ExtendTreeKeyAdapter(MethodsExtendTree mTree){
		this.mTree=mTree;
	}
	@Override
	public void keyPressed(KeyEvent e)  {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			MethodNode note = (MethodNode) mTree.getLastSelectedPathComponent();
			TreePath p=mTree.getSelectionPath();
//			System.out.println(note.getChildCount());
			if(note.getText().equals("===parent===")||note.getText().equals("===child===")){
				return;
			}
			MethodsExtendModel model=(MethodsExtendModel)mTree.getModel();
			mTree.selectMMode(model.getMethods(),note.getText());
			model.reload();
			mTree.addSelectionPath(p);;
		}
	}

}

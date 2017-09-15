package com.panda.ui.tree;

import java.awt.Color;
import java.awt.Component;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.panda.trace.MethodLog;

@SuppressWarnings("serial")
public class MethodRenderer extends DefaultTreeCellRenderer{
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,boolean sel, boolean expanded, boolean leaf, int row,boolean hasFocus)
    {   
       super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,row, hasFocus); //调用父类的该方法   
       MethodLog m=((MethodNode) value).getM(); //从节点读取文本
       setForeground(Color.BLUE);
       setBackgroundSelectionColor(Color.LIGHT_GRAY);
       setOpaque(false);
       String text=((MethodNode) value).isRootNode()?(((MethodNode) value).getPos()+"\t"+m.getFullName()):m.getFullName();
       text=((MethodNode) value).showTime?(text+"    <threadTime:"+(m.getThreadCostTime()*1.0)/1000+"s/wallTime:"+(m.getWallCostTime()*1.0)/1000+"s>"):
    	   text;
       
       if(((MethodNode) value).isNeedTab()){
    	   setOpaque(true);
           setForeground(Color.RED);
           setBackground(Color.LIGHT_GRAY);
       }
       setIcon(null);
       setText(text);//设置文本
       return this;   
     }
}

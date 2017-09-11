package com.panda.ui;

import java.awt.Color;
import java.awt.Component;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

@SuppressWarnings("serial")
public class MethodRenderer extends DefaultTreeCellRenderer{
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,boolean sel, boolean expanded, boolean leaf, int row,boolean hasFocus)
    {   
       super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,row, hasFocus); //调用父类的该方法   
       Icon icon = ((MethodNode) value).getIcon();//从节点读取图片
       String txt=((MethodNode) value).getText(); //从节点读取文本
//       setOpaque(true);
       setForeground(Color.BLUE);
       setBackgroundSelectionColor(Color.LIGHT_GRAY);
//       setBackground(Color.YELLOW);
       if(((MethodNode) value).isNeedTab()){
    	   setOpaque(true);
           setForeground(Color.RED);
           setBackground(Color.LIGHT_GRAY);
           setIcon(null);//设置图片
           setText(txt);//设置文本
           return this;
       }
       if(((MethodNode) value).isSelected()){
    	   //sets
       }
       setOpaque(false);
       setIcon(icon);//设置图片
       setText(txt);//设置文本
       return this;   
     }
}

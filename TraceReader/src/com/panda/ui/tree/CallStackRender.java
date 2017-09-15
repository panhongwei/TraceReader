package com.panda.ui.tree;

import java.awt.Color;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.panda.trace.MethodLog;

public class CallStackRender extends DefaultTreeCellRenderer{
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,boolean sel, boolean expanded, boolean leaf, int row,boolean hasFocus)
    {   
       super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,row, hasFocus); //调用父类的该方法   
       MethodLog m=((CallStackNode) value).getM(); 
//       setOpaque(true);
       setForeground(Color.BLUE);
       setOpaque(false);
       setBackgroundSelectionColor(Color.LIGHT_GRAY);
       String text=m.getFullName();
       text=text+"("+((CallStackNode) value).getChildNum()+")";
       text=((CallStackNode) value).showTime?(text+"    <threadTime:"+(m.getThreadCostTime()*1.0)/1000+"s/wallTime:"+(m.getWallCostTime()*1.0)/1000+"s>"):
    	   text;
       if(((CallStackNode) value).isNeedTab()){
    	   setOpaque(true);
           setForeground(Color.RED);
           setBackground(Color.LIGHT_GRAY);
           return this;
       }
       setIcon(null);//设置图片
       setText(text);//设置文本
//       if(((CallStackNode) value).showTime){
//    	   String time=(" <threadTime:"+(m.getThreadCostTime()*1.0)/1000+"s/wallTime:"+(m.getWallCostTime()*1.0)/1000+"s>");
//    	   JLabel l=new JLabel(time);
//    	   l.setForeground(Color.BLACK);
//       }
       return this;   
     }
}
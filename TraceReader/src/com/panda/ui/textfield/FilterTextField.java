package com.panda.ui.textfield;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import com.panda.ui.TraceFrame;
import com.panda.ui.tree.CallStackTree;
import com.panda.ui.tree.MethodsExtendTree;

public class FilterTextField extends JTextField{
	TraceFrame jframe;
	String regString;
	public FilterTextField(TraceFrame jframe){
		this.jframe=jframe;
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e)  {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					//System.out.println(searchField.getText());
					regString=FilterTextField.this.getText();
					FilterTextField.this.jframe.evalSearch(regString);
				}
			}
			
		});
	}
}

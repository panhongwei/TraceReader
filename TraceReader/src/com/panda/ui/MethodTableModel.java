package com.panda.ui;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.panda.trace.MethodDes;
import com.panda.trace.MethodLog;

public class MethodTableModel extends AbstractTableModel{
	TraceFrame log;
	public MethodTableModel(TraceFrame log){
		this.log=log;
	} 
	@Override
	public String getColumnName(int column){
		if(column==0){
			return "序号";
		}else if(column==1){
			return "方法";
		}else if(column==2){
			return "运行时间";
		}
		return null;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		//System.out.println(TraceFrame.getChildNum(log));
		if(log.traceThreads!=null)
			return log.traceThreads.getAllName().size();
		else 
			return 0;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		if(columnIndex==0){
			return rowIndex;
		}else if(columnIndex==1){
			if(log.traceThreads!=null){
				String m=log.traceThreads.getAllName().get(rowIndex);
				return m;
			}
		}
		return null;
	}
	
}

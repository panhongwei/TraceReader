package com.panda.ui.drop;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.panda.trace.BytesHelper;
import com.panda.trace.ThreadList;
import com.panda.trace.Trace;
import com.panda.trace.TraceThread;
import com.panda.ui.TraceFrame;

public class DropTargetAdapterExt extends DropTargetAdapter{
	JFrame frame;
	public DropTargetAdapterExt(JFrame frame){
		super();
		this.frame=frame;
	}
	@Override
	public void drop(DropTargetDropEvent dtde) {
		// TODO Auto-generated method stub
		try
        {
	        Transferable tf=dtde.getTransferable();
	        if(tf.isDataFlavorSupported(DataFlavor.javaFileListFlavor))
	        {
		        dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
		        List lt=(List)tf.getTransferData(DataFlavor.javaFileListFlavor);
		        Iterator itor=lt.iterator();
		        while(itor.hasNext())
		        {
			        File fl=(File)itor.next();
			        if(fl.getName().endsWith(".trace")){
						try {
							if(((TraceFrame)frame).getTraceThreads()!=null){
								((TraceFrame)frame).getTraceThreads().reset();
							}
							TraceThread.topMethod.getChild().clear();
							byte[] bytes=BytesHelper.toByteArray(fl.getPath());
							//long current=System.currentTimeMillis();
							Trace trace=new Trace(bytes);
							//long current1=System.currentTimeMillis();
							
							((TraceFrame)frame).setTraceThreads(trace.getThreadList());
							long current2=System.currentTimeMillis();
							//System.out.println(current1-current);
							//System.out.println(current2-current1);
							//threadList.
							((TraceFrame)frame).updateUI();;
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "解析文件出错！", "提示", JOptionPane.OK_OPTION); 
						}
					}else{
						JOptionPane.showMessageDialog(null, "选中非trace文件！", "提示", JOptionPane.OK_OPTION); 
					}
//			        System.out.println(f.getAbsolutePath());
		        }
		        dtde.dropComplete(true);
	         }
	         else
	         {
		        dtde.rejectDrop();
	         }
         }
         catch(Exception e)
         {
	         e.printStackTrace();
         }
	}

}

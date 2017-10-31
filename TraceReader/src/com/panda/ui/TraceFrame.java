package com.panda.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.dnd.DropTarget;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TooManyListenersException;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.panda.trace.MethodLog;
import com.panda.trace.ThreadList;
import com.panda.trace.TraceRecord;
import com.panda.trace.TraceThread;
import com.panda.ui.combo.OrderComboBox;
import com.panda.ui.drop.DropTargetAdapterExt;
import com.panda.ui.list.ThreadListExt;
import com.panda.ui.menu.ThreadListPopupMenu;
import com.panda.ui.menu.TraceMenuBar;
import com.panda.ui.textfield.FilterTextField;
import com.panda.ui.tree.CallStackRender;
import com.panda.ui.tree.CallStackTree;
import com.panda.ui.tree.MethodRenderer;
import com.panda.ui.tree.MethodsExtendModel;
import com.panda.ui.tree.MethodsExtendTree;
import com.panda.util.MethodUtil;

public class TraceFrame extends JFrame{

	/**
	 * panda version 1.1
	 */
	private DropTarget dropTarget;
	private JPanel jp0,jp1;
	private JSplitPane jspMain;
	JTextField searchField; 
	private static final long serialVersionUID = 1L;
	ThreadList traceThreads;
	public ThreadList getTraceThreads() {
		return traceThreads;
	}
	public void setTraceThreads(ThreadList traceThreads) {
		this.traceThreads = traceThreads;
	}
	String filter="";
	int hooks=0;
	int renames=0;
	//	Trace trace;
	JList threadList;
	JTree callstackTree,mTree;
	JTable mtable;
	JTabbedPane tabbedPane;
//	JComboBox jcb;
	public TraceFrame() throws TooManyListenersException{
		this.setTitle("Trace文件分析工具 V1.1");
		Toolkit tk = this.getToolkit();
		Dimension dm = tk.getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLocation((int) (dm.getWidth() - 800) / 2,  
                (int) (dm.getHeight() - 500) / 2);
		this.setSize(800, 500);
		initJMenu();
		initArea();
		//实现拖拽打开
		dropTarget = new DropTarget();
		dropTarget.setComponent(this);
		dropTarget.addDropTargetListener(new DropTargetAdapterExt(this));
    	this.setVisible(true);
	}
	public void initJMenu(){
		this.setJMenuBar(new TraceMenuBar(this));
	}
	public void initArea(){
		//combox
		jp0=new JPanel();
		jp1=new JPanel();
		BorderLayout border=new BorderLayout();
		jp1.setLayout(border);
		jp1.add(new OrderComboBox(this),BorderLayout.NORTH);
		BorderLayout border0=new BorderLayout();
		jp0.setLayout(border0);
		
		//splitpane
		jspMain=new JSplitPane();
		jspMain.setDividerLocation(200);
		
		//threadlist界面
		threadList=new ThreadListExt(this);
		
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(threadList);
		jp1.add(jScrollPane, BorderLayout.CENTER);
//		TextAreaMenu m=new TextAreaMenu();
//		jp1.add();
		jspMain.setLeftComponent(jp1);
		
		//callstack 
		callstackTree=new CallStackTree(this); 
		callstackTree.setCellRenderer(new CallStackRender());
		callstackTree.setBorder(BorderFactory.createTitledBorder(getBoderName(0)));
		JScrollPane jScrollPane1 = new JScrollPane();
		jScrollPane1.setViewportView(callstackTree);
		
		//Method list Tree
		mTree=new MethodsExtendTree(this);
		mTree.setCellRenderer(new MethodRenderer());
		mTree.setBorder(BorderFactory.createTitledBorder(getBoderName(1)));
		
		mtable=new JTable(new MethodTableModel(this));
		
		tabbedPane=new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addTab("Trace树形结构", jScrollPane1);
		JScrollPane jspMtree=new JScrollPane(mTree);
		final JScrollBar jsb=jspMtree.getVerticalScrollBar();
		jspMtree.setVerticalScrollBar(jsb);
//		jspMtree.setVerticalScrollBarPolicy(                
//	                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jsb.addAdjustmentListener(new AdjustmentListener(){
			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {
				// TODO Auto-generated method stub
				if(((MethodsExtendModel)mTree.getModel()).getWhere()!=-1&&jsb.getValue()>jsb.getMaximum()*0.9){
//					System.out.println(mModel.getWhere());
					jsb.setValue((int)(jsb.getMaximum()*0.5));
					((MethodsExtendTree) mTree).reloadMMode(((MethodsExtendModel)mTree.getModel()).getMethods(),((MethodsExtendModel)mTree.getModel()).getWhere(),searchField.getText());
				}
				
			}
			
		});

		tabbedPane.addTab("Trace方法列表", jspMtree);
		tabbedPane.addTab("Trace方法集合", new JScrollPane(mtable));
		jspMain.setRightComponent(tabbedPane);
		tabbedPane.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if(tabbedPane.getSelectedIndex()==1){
					((MethodsExtendTree) mTree).reloadMMode(0,"");
					return;
				}
			}
			
		});
		
		JPanel jp3=new JPanel();
		jp3.setBorder(BorderFactory.createTitledBorder("查  找"));
		BorderLayout border3=new BorderLayout();
		jp3.setLayout(border3);
		searchField=new FilterTextField(this);
		jp3.add(searchField,border3.CENTER);
		
		jp0.add(jspMain,border0.CENTER);
		jp0.add(jp3, border0.SOUTH);
		this.getContentPane().add(jp0);
	}
	public File chooseFile(){
		JFileChooser jfc=new JFileChooser();  
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY );  
        jfc.showDialog(new JLabel(), "选择");  
        File file=jfc.getSelectedFile();
        return file;
	}
	public static int getChildNum(MethodLog log){
		int sum=log.getChild().size();
		if(sum==0){
			return sum;
		}
		for(MethodLog m1:log.getChild()){
			sum=sum+getChildNum(m1);
		}
		return sum;
	}
	public void updateUI(){
		threadList.updateUI();
	}
	public void extendMethod(String name,String reg){
		((CallStackTree) callstackTree).setName(name);
		((CallStackTree) callstackTree).extendMethod(reg);
		TraceThread thread=traceThreads.getThreads().get(name);
		List<MethodLog> methods=thread.getMethods();
		((MethodsExtendModel)mTree.getModel()).setMethods(methods);
		if(tabbedPane.getSelectedIndex()==1){
			((MethodsExtendTree) mTree).reloadMMode(0,reg);
		}
	}
	public void extendMethod(String reg){
		((CallStackTree) callstackTree).extendMethod(reg);
		TraceThread thread=traceThreads.getThreads().get(((CallStackTree) callstackTree).getName());
		List<MethodLog> methods=thread.getMethods();
		((MethodsExtendModel)mTree.getModel()).setMethods(methods);
		if(tabbedPane.getSelectedIndex()==1){
			((MethodsExtendTree) mTree).reloadMMode(0,reg);
		}
	}
	public void evalSearch(String reg){
		if(tabbedPane.getSelectedIndex()==0){
			if(reg=="")
				return;
			((CallStackTree) callstackTree).extendTreeMode(reg);
			return;
		}
		if(tabbedPane.getSelectedIndex()==1){
			((MethodsExtendTree) mTree).reloadMMode(0,reg);
			return;
		}
	}
	public void addFilterName(String reg){
		filter=reg;
		reloadLabelName();
		if(tabbedPane.getSelectedIndex()==1){
			((MethodsExtendTree) mTree).reloadMMode(0,"");
		}else if(tabbedPane.getSelectedIndex()==0){
			((CallStackTree) callstackTree).fillTree(reg);
		}
	}
	private void reloadLabelName(){
		mTree.setBorder(BorderFactory.createTitledBorder(getBoderName(1)));
		callstackTree.setBorder(BorderFactory.createTitledBorder(getBoderName(0)));
	}
	public String getBoderName(int i){
		if(i==0){
			return "调用关系<显示字符:"+(filter.equals("")?"all":filter)+",添加hook函数:"+hooks+",重命名:"+renames+">";
		}else if(i==1){
			return "方法列表<显示字符:"+(filter.equals("")?"all":filter)+",添加hook函数:"+hooks+",重命名:"+renames+">";
		}else{
			return "uknown";
		}
	}
	public synchronized void rename(String origin,String name,String rename){
		Map<String,TraceRecord> nameMap=this.getTraceThreads().getNameMap();
		String clsName=name.substring(0, name.indexOf("."));
		String methodName=name.substring(name.indexOf("."),name.indexOf("("));
		String sig=name.substring(name.indexOf("("),name.length());
		String reClsName=rename.substring(0, rename.indexOf("."));
		String reMethodName=rename.substring(rename.indexOf("."),rename.indexOf("("));
		String reSig=rename.substring(rename.indexOf("("),rename.length());
		if(reClsName.equals("")||reMethodName.equals("")||reSig.equals("")){
			JOptionPane.showMessageDialog(this, "改名失败!");
			return;
		}
		if((!clsName.equals(reClsName)&&!sig.equals(reSig))){
			JOptionPane.showMessageDialog(this, "一次只允许修改一个类!");
			return;
		}
		if(!methodName.equals(reMethodName)){
			TraceRecord r=nameMap.get(origin);
			r.reNameMethod(reMethodName);
		}
		if(!clsName.equals(reClsName)){
			for(Entry<String,TraceRecord> entry :nameMap.entrySet()){
				TraceRecord r=entry.getValue();
				r.reNameClass(clsName,reClsName);
			}
		}else if(!sig.equals(reSig)){
			String[] str= MethodUtil.anlysisSig(sig).split(";");
			String[] res= MethodUtil.anlysisSig(reSig).split(";");
			if(str.length!=res.length){
				JOptionPane.showMessageDialog(this, "改名失败!");
				return;
			}
			for(int i=0;i<str.length;++i){
				if(MethodUtil.isPrimitiveString(str[i])){
					continue;
				}else if(!str[i].equals(res[i])){
					for(Entry<String,TraceRecord> entry :nameMap.entrySet()){
						TraceRecord r=entry.getValue();
						r.reNameClass(str[i],res[i]);
					}
				}
			}
		}
		renames++;
		reloadLabelName();
	}
}

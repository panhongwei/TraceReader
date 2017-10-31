package com.panda.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.panda.trace.BytesHelper;
import com.panda.trace.ThreadList;
import com.panda.trace.Trace;
import com.panda.trace.TraceThread;
import com.panda.ui.TraceFrame;

@SuppressWarnings("serial")
public class TraceMenuBar extends JMenuBar{
	JFrame frame;
	private JMenu menu1, menu2, menu3, menu4, menu5;
	private JMenuItem it1,it2;
	final String VERSION ="V 1.1";
	final String HELP="	使用方法：\r\n1.拖拽文件到界面，会自动解析trace文件；\r\n"
								+ "2.双击某个线程名称，会以树形界面显示调用堆栈；\r\n"
								+ "3.查找框支持对某些字符串查找；\r\n"
								+ "4.右键点击线程列表支持过滤，复制；\r\n"
								+ "5.右键点击mehtod，支持复制，显示时间，改名；";
	public TraceMenuBar(JFrame frame){
		super();
		this.frame=frame;
		buildMenu();
	}
	private boolean buildMenu(){
		menu1 = new JMenu("文   件(F)");  
        menu1.setMnemonic('f'); //助记符  
        it1=new JMenuItem("打开");
        it2=new JMenuItem("新建窗口");
        menu1.add(it1);
        menu1.add(it2);
        it1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub  
				File fl=((TraceFrame)frame).chooseFile();
				if(fl==null){
					return;
				}
				if(fl.getName().endsWith(".trace")){
					try {
						if(((TraceFrame)frame).getTraceThreads()!=null){
							((TraceFrame)frame).getTraceThreads().reset();
						}
						TraceThread.topMethod.getChild().clear();
//						File fl=new File
						byte[] bytes=BytesHelper.toByteArray(fl.getPath());
						Trace trace=new Trace(bytes);
						((TraceFrame)frame).setTraceThreads(trace.getThreadList());
						//threadList.
						((TraceFrame)frame).updateUI();
						System.out.println(TraceThread.topMethod.getChild().size());
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "解析文件出错！", "提示", JOptionPane.OK_OPTION); 
					}
				}else{
					JOptionPane.showMessageDialog(null, "选中非trace文件！", "提示", JOptionPane.OK_OPTION); 
				}
			}
		});
        menu2 = new JMenu("编辑");  
        menu2.setMnemonic('E');  
        menu3 = new JMenu("格式");  
        menu4 = new JMenu("查看");  
        menu5 = new JMenu("帮助");
        JMenuItem help=new JMenuItem("帮助文档");
        JMenuItem version=new JMenuItem("版本");
        menu5.add(help);
        menu5.add(version);
        help.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, HELP);
			}
		});
        version.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, VERSION);
			}
		});
//        jb2 = new JButton("打开"); 
        this.add(menu1);  
        this.add(menu2);  
        this.add(menu3);  
        this.add(menu4);  
        this.add(menu5);
		return true;
	}
}

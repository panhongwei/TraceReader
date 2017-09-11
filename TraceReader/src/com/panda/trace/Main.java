package com.panda.trace;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.panda.ui.TraceFrame;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//File fl=new File("./test.trace");
		TraceFrame fm=new TraceFrame();
		
		//System.out.print(fl.length())
//		byte[] bytes=BytesHelper.toByteArray("./test.trace");
//		Trace trace=new Trace(bytes);
//		Threads srm=new Threads(trace);
//		MethodLog toplevel=TraceThread.topMethod;
//		for(MethodLog m:toplevel.child){
//			//d(m);
//			if(m.record.threadId==1){
//				System.out.println(m.FullName+" "+toplevel.child.size());
//				printChild(m);
//				//System.out.println("===========================================");
//			}
//		}
//		for (String key : srm.threads.keySet()) {
//			System.out.println(srm.threads.get(key).threadId+"||"+srm.threads.get(key).methods.size());
//			if(srm.threads.get(key).threadId==3){
//				
//				MethodLog toplevel=TraceThread.topMethod;
//				for(MethodLog m:toplevel.child){
//					//d(m);
//				}
//			}
//		}
//		srm.threads
//		for (String key : srm.threads.keySet()) {
//		  // System.out.println(srm.threads.get(key).threadId+" "+srm.threads.get(key).methods.size());
//			   int num=1;
//			   for(int i=0;i<srm.threads.get(key).methods.size();++i){
//				   if(num>0){
//					   //System.out.println("parent:"+srm.threads.get(key).methods.get(i).parent.methodName);
//					   if(srm.threads.get(key).methods.get(i).action==0)
//						   System.out.println(srm.threads.get(key).threadId+"\t"+srm.threads.get(key).methods.get(i).methodName);
//				   }
//				   num--;
//			   }
//		}
//		srm.methods.get(0).methodName;
//		System.out.println("threads="+srm.threads.size());
//		for(int i=0;i<srm.threads.size();++i){
//				System.out.println(srm.threads.get(i+"").name);
////				System.out.println(srm.methods.get(i).methodName+" "+srm.methods.get(i).record.threadClockDiff);
//		}
//		System.out.println(srm.methods.get(0).methodName+" "+srm.methods.get(0).record.threadClockDiff);
//		System.out.println(srm.methods.get(1).methodName+" "+srm.methods.get(1).record.threadClockDiff);
//		System.out.println(srm.methods.get(2).methodName+" "+srm.methods.get(2).record.threadClockDiff);
//		System.out.println(srm.methods.get(3).methodName+" "+srm.methods.get(3).record.threadClockDiff);
//		System.out.println(srm.methods.get(srm.methods.size()-1).methodName+" "+srm.methods.get(srm.methods.size()-1).record.threadClockDiff);
		
	}
	public static void printChild(MethodLog m){
		System.out.println("======"+m.methodName+" "+m.child.size()+" "+m.partner.child.size()+"==========");
		for(MethodLog m1:m.child){
			System.out.println(m1.FullName);
		}
		for(MethodLog m1:m.child){
			if(m1.child.size()==0){
				System.out.println(m1.FullName+">>>>no child!");
				System.out.println("");
				continue ;
			}
			System.out.println("");
			printChild(m1);
		}
	}

}

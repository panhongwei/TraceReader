package com.panda.trace;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TraceThread {
	int threadId;
	public int getThreadId() {
		return threadId;
	}
	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}
	public List<MethodLog> getMethods() {
		return methods;
	}
	public void setMethods(List<MethodLog> methods) {
		this.methods = methods;
	}
	String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	List<MethodLog> methods=new ArrayList<>();
	public final static MethodLog topMethod=new MethodLog("TopMethod");
	public final static MethodLog endMethod=new MethodLog("endMethod");
	public final static MethodLog noPart=new MethodLog("noPart");
	public void sortMethods(){
		Stack<MethodLog>  stack=new Stack();
		int n=0;
		//stack.push(topMethod);
		for(int i=0;i<methods.size();++i){
			if(methods.get(i).action==0){
				stack.push(methods.get(i));
			}else{
				if(stack.isEmpty()){
					methods.get(i).parent=noPart;
					n++;
					continue;
				}
				stack.pop();
			}
		}
		for(int i=0;i<stack.size();++i){
			methods.add(new MethodLog("noPart",1));
		}
		stack.clear();
		stack.push(topMethod);
		for(int i=0;i<n;++i){
			stack.push(new MethodLog("noPart",0));
		}
		for(int i=0;i<methods.size();++i){
			if(methods.get(i).action==0){
				methods.get(i).parent=stack.get(stack.size()-1);
				stack.get(stack.size()-1).child.add(methods.get(i));
				stack.push(methods.get(i));
			}else{
				MethodLog m=stack.pop();
				m.partner=methods.get(i);
				methods.get(i).partner=m;
//				if(threadId==2){
//					System.out.println("+++"+methods.get(i).partner);
//				}
			}
		}
		List<MethodLog> m=new ArrayList();
		for(int i=0;i<methods.size();++i){
			if(methods.get(i).action==0){
				if(methods.get(i).parent==null){
					methods.get(i).parent=methods.get(i).partner.parent;
					methods.get(i).parent.child.remove(methods.get(i).partner);
					methods.get(i).parent.child.add(methods.get(i));
					methods.get(i).child=methods.get(i).partner.child;
					for(int j=0;j<methods.get(i).child.size();++j){
						methods.get(i).child.get(j).parent=methods.get(i);
					}
				}
				if(methods.get(i).methodName.equals(noPart.methodName)){
					methods.get(i).partner.parent=methods.get(i).parent;
					methods.get(i).parent.child.remove(methods.get(i));
					methods.get(i).parent.child.add(methods.get(i).partner);
					methods.get(i).partner.child=methods.get(i).child;
					for(int j=0;j<methods.get(i).child.size();++j){
						methods.get(i).child.get(j).parent=methods.get(i).partner;
					}
				}
				m.add(methods.get(i));
			}
		}
		methods.clear();
		methods=m;
		if(threadId==2){
			for(int i=0;i<methods.size();++i){
				System.out.println(methods.get(i).FullName+" "+methods.get(i).action);
				System.out.println(methods.get(i).partner);
			}
		}
//		for(int i=0;i<topMethod.child.size();++i){
//			if(topMethod.child.get(i).record.threadId==threadId){
//				System.out.println(threadId+" \t"+topMethod.child.get(i).FullName);
//			}
//		}
		
		
		
//		if(threadId)
//		System.out.println(threadId+" "+methods.size());
//		if(methods.size()==1){
//			methods.get(0).parent=topMethod;
//			topMethod.child.add(methods.get(0));
//			methods.get(0).partner=noPart;
//			return;
//		}
//		if(methods.size()==2){
//			if(methods.get(0).action==0&&methods.get(1).action==0){
//				methods.get(0).parent=topMethod;
//				topMethod.child.add(methods.get(0));
//				methods.get(1).parent=methods.get(0);
//				methods.get(0).child.add(methods.get(1));
//				
//				methods.get(0).partner=noPart;
//				methods.get(1).partner=noPart;
//			}else if(methods.get(0).action==0&&methods.get(1).action==1){
//				methods.get(0).parent=topMethod;
//				topMethod.child.add(methods.get(0));
//				methods.get(1).parent=topMethod;
//				topMethod.child.add(methods.get(1));
//				
//				methods.get(0).partner=methods.get(1);
//				methods.get(1).partner=methods.get(0);
//			}else if(methods.get(0).action==1&&methods.get(1).action==0){
//				methods.get(0).parent=topMethod;
//				topMethod.child.add(methods.get(0));
//				methods.get(1).parent=topMethod;
//				topMethod.child.add(methods.get(1));
//				
//				methods.get(0).partner=noPart;
//				methods.get(1).partner=noPart;
//			}else if(methods.get(0).action==1&&methods.get(1).action==1){
//				methods.get(1).parent=topMethod;
//				topMethod.child.add(methods.get(1));
//				methods.get(0).parent=methods.get(1);
//				methods.get(1).child.add(methods.get(0));
//				
//				methods.get(1).partner=noPart;
//				methods.get(0).partner=noPart;
//			}
//			return;
//		}
//		int enter=0;
//		int tp=Integer.MAX_VALUE;
//		//find topMethod
//		for(int i=0;i<methods.size();++i){
//			if(enter<tp){
//				tp=enter;
//			}
//			if(methods.get(i).action==0){
//				enter++;
//			}else{
//				enter--;
//			}
//		}
//		enter=0;
//		for(int i=0;i<methods.size();++i){
//			if(enter==tp){
//				methods.get(i).parent=topMethod;
//				if(!topMethod.child.contains(methods.get(i)))
//					topMethod.child.add(methods.get(i));
//			}
//			if(methods.get(i).action==0){
//				enter++;
//			}else{
//				enter--;
//			}
//		}
//		//find partner
//		for(int i=0;i<methods.size();++i){
//			if(methods.get(i).action==0){//skip enter
//				continue;
//			}
//			int j=i-1;
//			enter=-1;
//			while(j>=0){
//				if(methods.get(j).action==0){
//					enter++;
//				}else{
//					enter--;
//				}
//				if(enter==0&&methods.get(j).action==0&&methods.get(j).methodName.equals(methods.get(i).methodName)){
//					methods.get(i).partner=methods.get(j);
//					methods.get(j).partner=methods.get(i);
//					break;
//				}
////				if(enter==1){
////					methods.get(i).parent=methods.get(j);
////					methods.get(j).child.add(methods.get(i));
////					break;
////				}
//				j--;
//			}
//			if(methods.get(i).partner==null)
//				methods.get(i).partner=noPart;
////			j=i+1;
////			enter=0;
////			if(methods.get(i).parent==null){
////				while(j<methods.size()){
////					if(methods.get(j).action==0){
////						enter++;
////					}else{
////						enter--;
////					}
////					if(enter==-1){
////						methods.get(i).parent=methods.get(j);
////						methods.get(j).child.add(methods.get(i));
////					}
////					++j;
////				}
////			}
//		}
//		for(int i=0;i<methods.size();++i){
//			findParent(methods.get(i),i);
//			if(methods.get(i).parent==null){
//				Exception e=new Exception("didn't find Parent Method!!");
//				e.printStackTrace();
//			}
//		}
//		enter=0;
//		for(int i=0;i<methods.size();++i){
//			if(methods.get(i).partner==null)
//				methods.get(i).partner=noPart;		
////			if(!methods.get(i).partner.equals(noPart)){
////				if(methods.get(i).parent==null){
////					methods.get(i).parent=methods.get(i).partner.parent;
////					methods.get(i).parent.child.add(methods.get(i));
////				}
////			}
//		}

	}
//	private void findParent(MethodLog m,int i){
//		int enter=0;
//		if(m.parent!=null)
//			return;
//		if(m.parent==null&&m.partner!=null&&m.partner.parent!=null){
//			m.parent=m.partner.parent;
//			
//			return;
//		}
//		if(m.partner==null||m.partner.equals(noPart)){
//			if(m.parent==null&&m.action==0){
//				enter=0;
//				int j=i-1;
//				while(j>=0){
//					if(methods.get(j).action==0){
//						enter++;
//					}else{
//						enter--;
//					}
//					if(enter==1){
//						m.parent=methods.get(j);
//						methods.get(j).child.add(m);
//						if(methods.get(j).partner!=null){
//							methods.get(j).partner.child.add(m);
//						}
//						return;
//					}
//					--j;
//				}
//			}else if(m.parent==null&&m.action==1){
//				enter=0;
//				int j=i+1;
//				while(j<methods.size()){
//					if(methods.get(j).action==0){
//						enter++;
//					}else{
//						enter--;
//					}
//					if(enter==-1){
//						m.parent=methods.get(j);
//						methods.get(j).child.add(m);
//						if(methods.get(j).partner!=null){
//							methods.get(j).partner.child.add(m);
//						}
//						return;
//					}
//					++j;
//				}
//			}
//		}else{
//			if(m.parent!=null){
//				return;
//			}
//			enter=0;
//			int j=i-1;
//			while(j>=0){
//				if(methods.get(j).action==0){
//					enter++;
//				}else{
//					enter--;
//				}
//				if(enter==1){
//					m.parent=methods.get(j);
//					m.partner.parent=methods.get(j);
//					methods.get(j).child.add(m);
//					if(methods.get(j).partner!=null){
//						methods.get(j).partner.child.add(m);
//					}
//					return;
//				}
//				j--;
//			}
//			if(m.parent==null){
//				enter=-1;
//				j=i+1;
//				while(j<methods.size()){
//					if(methods.get(j).action==0){
//						enter++;
//					}else{
//						enter--;
//					}
//					if(enter==-1){
//						m.parent=methods.get(j);
//						m.partner.parent=methods.get(j);
//						methods.get(j).child.add(m);
//						if(methods.get(j).partner!=null){
//							methods.get(j).partner.child.add(m);
//						}
//						return;
//					}
//				}
//			}
//		}
//	}
}

package com.panda.trace;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//File format:
//header
//record 0
//record 1
//...
//
//Header format:
//u4  magic ('SLOW')
//u2  version
//u2  offset to data
//u8  start date/time in usec
//u2  record size in bytes (version >= 2 only)
//... padding to 32 bytes
//
//Record format v1:
//u1  thread ID
//u4  method ID | method action
//u4  time delta since start, in usec
//
//Record format v2:
//u2  thread ID
//u4  method ID | method action
//u4  time delta since start, in usec
//
//Record format v3:
//u2  thread ID
//u4  method ID | method action
//u4  time delta since start, in usec
//u4  wall time since start, in usec (when clock == "dual" only)
//
//32 bits of microseconds is 70 minutes.
//
//All values are stored in little-endian order.
//copy from android 4.4
public class Trace {
	FormatFile fmFile;
	TraceFile traceFile;
	Map<String,TraceThread> threads=new HashMap<>();
	public List<String> names=new ArrayList<>();
	public Trace(byte[] bytes){
		divideBytes(bytes);
	}
	private void readTrace(byte[] data){
		traceFile=new TraceFile();
		int offset=0;
		traceFile.header.kTraceMagicValue=(char)(data[0])+""+(char)(data[1])+""+(char)(data[2])+""+(char)(data[3]);
		offset=offset+4;
		traceFile.header.trace_version=BytesHelper.read2LE(data, offset);
		offset=offset+2;
		traceFile.header.kTraceHeaderLength=BytesHelper.read2LE(data, offset);
		offset=offset+2;
		traceFile.header.start_time_=BytesHelper.read8LE(data, offset);
		offset=offset+8;
		if(traceFile.header.trace_version>=3){
			traceFile.header.record_size=14;
		}else{
			traceFile.header.record_size=10;
		}
		offset=traceFile.header.kTraceHeaderLength;
		int value;
		long current=System.currentTimeMillis();
		while(offset<data.length){
			TraceRecord r=new TraceRecord();
			r.threadId=BytesHelper.read2LE(data,offset);
			value=BytesHelper.read4LE(data,offset+2);
			r.methodValue=TraceAction.decodeMethodValue(value);
			r.threadClockDiff=BytesHelper.read4LE(data, offset+6);
			if(traceFile.header.record_size==14){
				r.wallClockDiff=BytesHelper.read4LE(data,offset+10);
			}
			r.m=fmFile.methods.get(r.methodValue);
			r.action=TraceAction.decodeAction(value);
			traceFile.records.add(r);
			MethodLog ml=new MethodLog();
			ml.record=r;
			if(r.m==null){
				ml.methodName="0x"+Integer.toHexString(r.methodValue);
				ml.action=r.action;
				ml.source="unkown";
				ml.FullName=ml.methodName;
			}else{
				ml.FullName=r.m.getMethodDescriptor()+"."+r.m.getMethodName()+r.m.getMethodSig();
				ml.methodName=r.m.getMethodName();
				ml.source=r.m.getSource().split("\t")[0];
				ml.action=r.action;
			}
			if(!threads.containsKey(ml.record.threadId+"")){
				TraceThread thread=new TraceThread();
				thread.threadId=ml.record.threadId;
				thread.methods.add(ml);
				thread.name=fmFile.threads.get(ml.record.threadId+"");
				threads.put(ml.record.threadId+"", thread);
				names.add(ml.record.threadId+"");
			}else{
				threads.get(ml.record.threadId+"").methods.add(ml);
			}
			offset=offset+traceFile.header.record_size;
		}
		long current1=System.currentTimeMillis();
		System.out.println(current1-current);
	}
	private void readFileFormat(String format){
		String[] lists=format.split("\n");
		fmFile=new FormatFile();
//		for(int i=0;i<100;++i){
//			System.out.println(lists[i]);
//		}
		int offset=0;
		fmFile.setVersion("version");
		fmFile.setVersioncode(Integer.parseInt(lists[1]));
		while(!lists[offset].equals("*threads")){
			if(lists[offset].contains("data-file-overflow")){
				String res=lists[offset].replace("data-file-overflow=", "");
				if(res.equals("true")){
					fmFile.setOverflow(true);
				}else{
					fmFile.setOverflow(false);
				}
			}else if(lists[offset].contains("clock")){
				fmFile.setClock(lists[offset].replace("clock=", ""));
			}else if(lists[offset].contains("elapsed-time-usec")){
				fmFile.setElapsedTime(Long.parseLong(lists[offset].replace("elapsed-time-usec=", "")));
			}else if(lists[offset].contains("num-method-calls")){
				fmFile.setMethodNum(Integer.parseInt(lists[offset].replace("num-method-calls=", "")));
			}else if(lists[offset].contains("clock-call-overhead-nsec")){
				fmFile.setClockCallOverhead(Integer.parseInt(lists[offset].replace("clock-call-overhead-nsec=", "")));
			}else if(lists[offset].contains("vm")){
				fmFile.setVm(lists[offset].replace("vm=", ""));
			}
			offset++;
		}
		offset++;
		//System.out.println("1\t2");
		while(!lists[offset].equals("*methods")){
			String params[]=lists[offset].split("\t");
			if(params.length>=2){
				fmFile.threads.put(params[0], params[1]);
				System.out.println(params[0]+" "+params[1]);
			}else{
				fmFile.threads.put(params[0], "unknown");
			}
			offset++;
		}
		offset++;
		while(!lists[offset].equals("*end")){
			MethodList m=new MethodList();
			String params[]=lists[offset].split("\t");
			m.setMethod(Integer.parseInt(params[0].replace("0x", ""), 16));
			m.setMethodDescriptor(params[1]);
			m.setMethodName(params[2]);
			m.setMethodSig(params[3]);
			if(params.length==6){
				m.setSource(params[4]+" "+params[5]);
			}else{
				m.setSource(params[4]);
			}
			offset++;
			fmFile.methods.put(m.getMethod(), m);
		}
	}
	private void divideBytes(byte[] bytes){
		int padding=0;
		for(int i=0;i<bytes.length;++i){
			if(bytes[i]=='S'&&bytes[i+1]=='L'&&bytes[i+2]=='O'&&bytes[i+3]=='W'){
				padding=i;
				break;
			}
		}
		StringBuffer buffer=new StringBuffer(new String(bytes,0,padding-1));
		byte[] data=new byte[bytes.length-padding];
		System.arraycopy(bytes, padding, data, 0, data.length-1);
		readFileFormat(buffer.toString());
		readTrace(data);
	}

}

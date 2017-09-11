package com.panda.trace;

public class TraceAction {
	public final static int kTraceMethodEnter=0x00;      // method entry
	public final static int kTraceMethodExit=0x01;       // method exit
	public final static int kTraceUnroll=0x02;            // method exited by exception unrolling
    // 0x03 currently unused
	public final static int kTraceMethodActionMask=0x03;  // two bits
	public static int decodeMethodValue(int methodVlaue){
		return (methodVlaue& (~kTraceMethodActionMask));
	}
	public static int decodeAction(int methodVlaue){
		return Math.abs((methodVlaue&kTraceMethodActionMask));
	}
}

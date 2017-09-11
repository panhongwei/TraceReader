package com.panda.trace;

import java.util.ArrayList;
import java.util.List;

public class TraceFile {
	TraceFileHeader header=new TraceFileHeader();
	List<TraceRecord> records=new ArrayList<TraceRecord>();
	public class TraceFileHeader {
		String kTraceMagicValue;
		int trace_version;
		int kTraceHeaderLength;
		int start_time_;
		int record_size;
	}
}


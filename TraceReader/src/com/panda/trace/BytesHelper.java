package com.panda.trace;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BytesHelper {
    public static byte[] toByteArray(String filename) throws IOException {  
    	  
        File f = new File(filename);  
        if (!f.exists()) {  
            throw new FileNotFoundException(filename);  
        }  
  
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());  
        BufferedInputStream in = null;  
        try {  
            in = new BufferedInputStream(new FileInputStream(f));  
            int buf_size = 1024;  
            byte[] buffer = new byte[buf_size];  
            int len = 0;  
            while (-1 != (len = in.read(buffer, 0, buf_size))) {  
                bos.write(buffer, 0, len);  
            }  
            return bos.toByteArray();  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw e;  
        } finally {  
            try {  
                in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            bos.close();  
        }  
    } 
    //ptr[2] | (ptr[3] << 8) | (ptr[4] << 16) | (ptr[5] << 24);
    public static int read2LE(byte[] data,int offset){
    	return (int)((data[offset]&0xff)|
				((data[offset+1]&0xff)<<8))&0xffff;
    }
    public static long read4LE(byte[] data,int offset){
    	return ((long)((data[offset]&0xff)|
				((data[offset+1]&0xff)<<8)|
				((data[offset+2]&0xff)<<16)|
				((data[offset+3]&0xff)<<24)))&0xffffffffl;
    }
    public static long read8LE(byte[] data,int offset){
    	return ((long)(data[offset]&0xff)|
				((data[offset+1]&0xff)<<8)|
				((data[offset+2]&0xff)<<16)|
				((data[offset+3]&0xff)<<24)|
				((data[offset+4]&0xff)<<32)|
				((data[offset+5]&0xff)<<40)|
				((data[offset+6]&0xff)<<48)|
				((data[offset+7]&0xff)<<56))&0xffffffffffffffffl;
    }

}

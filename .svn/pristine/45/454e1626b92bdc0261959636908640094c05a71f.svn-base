package com.spark.network.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class inputToByte {

	public static byte[] getImgByte(InputStream is) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int b;
		while ((b = is.read()) != -1) {
			bytestream.write(b);
		}
		byte[] bs = bytestream.toByteArray();
		bytestream.close();
		return bs;
	}
}

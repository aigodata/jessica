/** 
  *      
  * 
  */
package com.aigodata.jessica.basic.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.aigodata.jessica.basic.common.language.LocaleMessage;

/**
 * date: 2018年6月18日 下午4:14:25<br/>
 * 
 * @version 1.0
 * @since JDK
 * 
 */
public class ReadWriteUtil {

	/**
	 * 根据流读取内容
	 * 
	 * @param in
	 * @return
	 * @throws Exception
	 */
	public static final String read(InputStream in) throws Exception {
		StringBuffer contentBuffer = new StringBuffer();
		java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(in, "utf-8"));
		String inputLine = null;
		while ((inputLine = reader.readLine()) != null) {
			contentBuffer.append(inputLine);
			contentBuffer.append("\n");
		}
		reader.close();
		in.close();
		return contentBuffer.toString();
	}

	/**
	 * 根据文件读取内容
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static final String read(File file) throws Exception {
		return read(new FileInputStream(file));
	}

	/**
	 * 根据文件名称读取内容
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static final String read(String fileName) throws Exception {
		if (fileName == null || fileName.trim().equals("")) {
			return "";
		}
		return read(new File(fileName));
	}

	/**
	 * 写文件
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static final void write(String filePath, String fileContent) throws Exception {
		File file = new File(filePath);
		File parentFile = file.getParentFile();
		if (file.exists()) {
			throw new Exception(LocaleMessage.get("ERROR_FILE_EXIST"));
		}
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		FileOutputStream fos = new FileOutputStream(new File(filePath));
		byte[] bytes = fileContent.getBytes("UTF-8");
		fos.write(bytes, 0, bytes.length);
		fos.close();
	}
}

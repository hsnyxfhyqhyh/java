package com.ying.wso2.convert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.kang.util.FileUtil;

public class DSSLinkReplacement {

	private static String PREFIX = "MM_SP_";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String folderName = "C:\\wso2\\mine\\dss";
		testTraversingFilesAndDirectories(folderName);

	}

	public static String getContents(File aFile, String fileName) {
		StringBuffer contents = new StringBuffer ();
		
		//contents.append("<localEntry xmlns=\"http://ws.apache.org/ns/synapse\" key=\"" + PREFIX + fileName + "\">\n");
		
		try {
			// use buffering, reading one line at a time
			// FileReader always assumes default encoding is OK!
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			int i = 0;
			try {
				String line = null; // not declared within while loop
				while ((line = input.readLine()) != null && i <99999) {
					//leave the space for chapter links
					if (!line.trim().equalsIgnoreCase("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")) {
						String lineNew = line.replaceAll("lbdssuat", "lbdsstest.aaamidatlantic.com");
						contents.append(lineNew + "\n");
					}
					
				}
				i++;
			}
			finally {
				input.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		//contents.append("</localEntry>");
		
		return contents.toString();
	}

	public static void testTraversingFilesAndDirectories(String folderName){
		File file = new File(folderName);

		if (file.isDirectory()) {
//			System.out.println("Directory is  " + folderName);
			String str[] = file.list();
			for (int i = 0; i < str.length; i++) {
				String fileName = folderName + "\\" + str[i];
				
				if (!fileName.endsWith(".xml")) {
					continue;
				}
				File f = new File(fileName);
				if (f.isDirectory()) {
//					System.out.println(str[i] + " is a directory");
				} else {
					//get the new content of the file and dump it to a new file with xml added to the end of the file name
					String content = getContents(f, str[i]);
					String filename1 = folderName + "1" + "\\" + str[i];
					
					FileUtil.writeFile(content, filename1);
					System.out.println(str[i] + " is a file");
				}
			}
		} else {
			System.out.println(folderName + " is not a directory");
		}
	}
}

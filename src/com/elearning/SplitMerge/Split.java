package com.elearning.SplitMerge;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

/**
 * @author UdayGarg
 * @since 21/10/2015
 */
public class Split {
	/**
	 * 
	 * @param input
	 *            file name (f)
	 * @throws IOException
	 * @throws Exception
	 */
	public static  void split(String inputFilePath,String outputFilePath) throws IOException {
		
		System.out.println("In split");
	 
		// creating file of input file path
		File inputFile = new File(inputFilePath);
		
		// appending date,time and numbers with split file names
		String f1 = outputFilePath + "(1)" + inputFile.getName();
		String f2 = outputFilePath + "(2)" + inputFile.getName();
		String f3 = outputFilePath + "(3)" + inputFile.getName();
		

		// creating files of f1,f2 and f3 strings
		File outputFile1 = new File(f1);
		File outputFile2 = new File(f2);
		File outputFile3 = new File(f3);

		// creating input stream
		FileInputStream inputStream = new FileInputStream(inputFile);

		// calculating array length to store bytes
		byte[] inputBytes = new byte[(int) inputFile.length()];

		// reading bytes from inputFile
		inputStream.read(inputBytes);

		// creating three output streams
		FileOutputStream outputStream1 = new FileOutputStream(outputFile1);
		FileOutputStream outputStream2 = new FileOutputStream(outputFile2);
		FileOutputStream outputStream3 = new FileOutputStream(outputFile3);
		int i = 0;

		while (i != inputBytes.length) {
			// writing first 3rd part of the file in outstream1
			if (i <= inputBytes.length / 3) {
				outputStream1.write((char) inputBytes[i]);
			}
			// writing second 3rd part of the file in outstream1
			if (i > inputBytes.length / 3 && i <= (2 * inputBytes.length / 3)) {
				outputStream2.write((char) inputBytes[i]);
			}
			// writing third 3rd part of the file in outstream1
			if (i > (2 * inputBytes.length / 3) && i <= (inputBytes.length)) {
				outputStream3.write((char) inputBytes[i]);
			}
			i++;
		}

		
		
		InputStream is1=new FileInputStream(f1);
		InputStream is2=new FileInputStream(f2);
		InputStream is3=new FileInputStream(f3);
		
		
		System.out.println("file splited successfully");
		System.out.println("File names are:\n"+f1 + "\n" + f2 + "\n" + f3);
		// closing the resources
		inputStream.close();
		outputStream1.close();
		outputStream2.close();
		outputStream3.close();
		System.out.println(inputFile.delete());
		//return  inputFile.getName();
	}

}

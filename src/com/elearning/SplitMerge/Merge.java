
package com.elearning.SplitMerge;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Class to merge splitted files
 * @author UdayGarg
 * @since 21/10/2015
 */
public class Merge  {
	/**
	 * @param outputFileName(string of complete file path)
	 * @throws Exception
	 */
	public static String merge(String outputFileName ) throws Exception {
	 System.out.println("In Merge.merge method");
	 
	 //outputFileName1 is the name of output file that will contain data of merged file
		String outputFileName1 = outputFileName.substring(0,
				outputFileName.lastIndexOf('/') + 1)
				+ outputFileName
						.substring(outputFileName.indexOf(')') + 1);

		//predicting splited names and path from the requested file name i.e filePath1,filePath2,filePath
		//these three files are already exists,we just predicting file names
		String filePath1 = outputFileName.substring(0,
				outputFileName.lastIndexOf('/') + 1)
				+ "(1)"
				+ outputFileName
						.substring(outputFileName.lastIndexOf('/') + 1);
        System.out.println(filePath1);
        
		String filePath2 = outputFileName.substring(0,
				outputFileName.lastIndexOf('/') + 1)
				+ "(2)"
				+ outputFileName
						.substring(outputFileName.lastIndexOf('/') + 1);

		String filePath3 = outputFileName.substring(0,
				outputFileName.lastIndexOf('/') + 1)
				+ "(3)"
				+ outputFileName
						.substring(outputFileName.lastIndexOf('/') + 1);
		//creating files from split file names
		File inputFile1 = new File(filePath1);
		File inputFile2 = new File(filePath2);
		File inputFile3 = new File(filePath3);
		File outputFile = new File(outputFileName1);
		
        ArrayList<Byte> dd =new ArrayList<Byte>();
		// reading bytes from splited file i.e inpueFile1 and write it on
		// outputFile
		{
	 		FileInputStream inputStream = new FileInputStream(inputFile1);
			byte[] inputBytes = new byte[(int) inputFile1.length()];
			inputStream.read(inputBytes);
			int i = 0;
 			 while (i != inputBytes.length) {
				if (i <= inputBytes.length) {
					 
					dd.add(inputBytes[i]);
				}
				i++;
			}
 			 inputStream.close();
		}

		// reading bytes from splited file i.e inpuefile2 and write it on
		// outputFile
		{
 			FileInputStream inputStream = new FileInputStream(inputFile2);
			byte[] inputBytes = new byte[(int) inputFile2.length()];
			inputStream.read(inputBytes);
			int i = 0;
			 	while (i != inputBytes.length) {
				if (i <= inputBytes.length) {
					 
					dd.add(inputBytes[i]);
				}
				i++;
			}
			inputStream.close();
		}

		// reading bytes from splited file i.e inpueFile3 and write it on
		// outputFile
		{
 			FileInputStream inputStream = new FileInputStream(inputFile3);
			byte[] inputBytes = new byte[(int) inputFile3.length()];
			inputStream.read(inputBytes);
			int i = 0;
		 	while (i != inputBytes.length) {
				if (i <= inputBytes.length) {
					 
					dd.add(inputBytes[i]);
				}
				i++;
			}
			inputStream.close();
		}
	 
		//here we are creating stream of output file name i.e outputFile
		FileOutputStream outputStream = new FileOutputStream(outputFile);
		
		//writing arraylist object(dd) data on outputStream
		for(int j=0; j< dd.size();j++ )
		{
			outputStream.write( dd.get(j));
		}
		System.out.println("file merged successfully");
		System.out.println("resulted file is : "+outputFileName1);
		outputStream.close();
 		//returning output file name
		return outputFileName1;
 	}
	 
}

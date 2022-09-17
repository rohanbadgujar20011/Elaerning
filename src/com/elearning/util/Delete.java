package com.elearning.util;

import java.io.File;

public class Delete {
	/**
 	 * @param outputFileName
	 * @return 
	 * @throws Exception
	 */
	public static String delete(String deleteFilePath)  {
	 
	 	// predicting splited names and path from the requested file name i.e
		// filePath1,filePath2,filePath 
		//these three files are already exists,we just predicting files names from the name that is stored in database
		System.out.println("in delete filepath :: "+deleteFilePath);
		try{
		String filePath1 = deleteFilePath.substring(0,
				deleteFilePath.lastIndexOf('\\') + 1)
				+ "(1)"
				+ deleteFilePath
						.substring(deleteFilePath.lastIndexOf('\\') + 1);

		String filePath2 = deleteFilePath.substring(0,
				deleteFilePath.lastIndexOf('\\') + 1)
				+ "(2)"
				+ deleteFilePath
						.substring(deleteFilePath.lastIndexOf('\\') + 1);

		String filePath3 = deleteFilePath.substring(0,
				deleteFilePath.lastIndexOf('\\') + 1)
				+ "(3)"
				+ deleteFilePath
						.substring(deleteFilePath.lastIndexOf('\\') + 1);
		// creating files of split file names
		File inputFile1 = new File(filePath1);
		File inputFile2 = new File(filePath2);
		File inputFile3 = new File(filePath3);
		//deleting the splitted files
	System.out.println(filePath1+" : delete :: "+	inputFile1.delete());
	System.out.println(filePath2+" : delete :: "+	inputFile2.delete());
	System.out.println(filePath3+" : delete :: "+	inputFile3.delete());
		
		return filePath1;
		}
		catch(Exception e)
		{
			System.out.println("Problem in Delete.java class");
			e.printStackTrace();
			
		}
		return null;
		
		
		
	}
}

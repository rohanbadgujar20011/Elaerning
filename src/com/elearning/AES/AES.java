package com.elearning.AES;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.IOUtils;

/**
 * A utility class that encrypts or decrypts a file.  
 * @author Uday
 * @see HttpServlet
 *
 */ 
	public class AES {
		
	    private static final String ALGORITHM = "AES";
	    private static final String TRANSFORMATION = "AES";
	    static File inputFile;
	    static File inputFile1s;
	    static File encryptedFile;
	    static File decryptedFile;
 	     
	    /**
	     * //this method encrypts the data by calling doCrypto method in encrypt mode
	     * @param inputFilepath(complete path of the file)
	     * @throws Exception
	     */
	    public static void encrypt(String inputFilepath)
	            throws Exception {
	    	System.out.println("IN Encrypt Method");
	    	
	    	
	    	//it will create a file i.e inputfile by the given inputFilepath string
	    	 inputFile = new File(inputFilepath);
	      	 
  	      	 System.out.println("In encrypt method :"+inputFile.getAbsolutePath());
  	      	 //here we are creating output file(encryptedFile) in which encrypted data will be stored
	 		 encryptedFile= new File(inputFilepath);
	 		//method calling for encrypt file
	        doCrypto(Cipher.ENCRYPT_MODE, "aaaaaaaaaaaaaaaa", inputFile,encryptedFile);
	    }
	 
 	    //this method decrypts the data by calling doCrypto method in decrypt mode
	    public static void decrypt(String inputFile)
	            throws Exception {
	     	File  encryptedFile = new File(inputFile);
	     	//here we are creating output file(decryptedFile) in which decrypted data will be stored
	     	decryptedFile= new File(inputFile);
	     	//method calling for encrypt file	
	        doCrypto(Cipher.DECRYPT_MODE, "aaaaaaaaaaaaaaaa", encryptedFile , decryptedFile);
	        System.out.println("decryted");
	    }
	 
	    
	    /**
 	     * @param cipherMode
	     * @param key(key should of multiple of 16 character)
	     * @param inputFile
	     * @param outputFile
	     * @throws Exception
	     */
	    //AES Algorithm's key method for encryption and decryption
	    private static void doCrypto(int cipherMode, String key, File inputFile,
	    		File outputFile) throws Exception {
	    	FileInputStream inputStream=null;
	    	    try {
 	            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
	            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
	            cipher.init(cipherMode, secretKey);
	            inputStream = new FileInputStream(inputFile);
	            byte[] inputBytes = new byte[(int) inputFile.length()];
	            inputStream.read(inputBytes);
	            byte[] outputBytes = cipher.doFinal(inputBytes);
	            FileOutputStream outputStream = new FileOutputStream(outputFile);
	            outputStream.write(outputBytes);
	            System.out.println("out put file : "+outputFile.getName());
	            //closing the resources
	            inputStream.close();
	            outputStream.close();
	             
	        } catch (Exception ex) {
	        	inputStream.close();
 	            System.out.println("");
	        }
	    }
	     public static InputStream doCrypto1( InputStream is) throws Exception {
	    	String key="aaaaaaaaaaaaaaaa";
	    	InputStream myInputStream=null;
	    	int cipherMode=	Cipher.DECRYPT_MODE;
 	    	    try {
 	            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
	            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
	            cipher.init(cipherMode, secretKey);
	            byte[] bytes = IOUtils.toByteArray(is);
	            byte[] outputBytes = cipher.doFinal(bytes);
	            
	              myInputStream = new ByteArrayInputStream(outputBytes);
	             
	            //closing the resources
	            
	            
	             
	        } catch (Exception ex) {
 ex.printStackTrace();
 	        }
	    	    return myInputStream;
	    }
	
	    
	    public static void main(String[] args) throws Exception {
			decrypt("C:/Users/Administrator/Pictures/Leaf.jpg");
		}
}


package com.elearning.shakey;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 *
  
 */
public class ShaKey {

    private static final Logger logger = Logger.getLogger(ShaKey.class.getName());
    																																																																			String Sha="MD5"; 
    																																																																			 
    /**
     * Create the checksum for specified file                                                           																																										 
     *
     * @param path
     * @return
     */
    																								                                        String a="5";
    public String checkSum(InputStream fis) {
        String checksum = null;
        try {
            
 			//FileInputStream fis = new FileInputStream(path);             																								
            MessageDigest md = MessageDigest.getInstance(Sha);

            //Using MessageDigest update() method to provide input
            byte[] buffer = new byte[8192];
            int numOfBytesRead;
            while ((numOfBytesRead = fis.read(buffer)) > 0) {
                md.update(buffer, 0, numOfBytesRead);
                
            }
            byte[] hash = md.digest();
            checksum = new BigInteger(1, hash).toString(16); //don't use this, truncates leading zero
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return checksum;
    }
    public   String checkSum1(InputStream is) {
        String checksum = null;
        Sha.concat(a);
        try {
			 
			//FileInputStream fis = new FileInputStream(path);
            MessageDigest md = MessageDigest.getInstance(Sha);

            //Using MessageDigest update() method to provide input
            byte[] buffer = new byte[8192];
            int numOfBytesRead;
            while ((numOfBytesRead = is.read(buffer)) > 0) {
                md.update(buffer, 0, numOfBytesRead);
                
            }
            byte[] hash = md.digest();
            checksum = new BigInteger(1, hash).toString(16); //don't use this, truncates leading zero
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
       System.out.println("checksum :: "+checksum);
        return checksum;
    }
    public String checkSum(String path) {
        String checksum = null;
        try {
        	Sha.concat(a);
			@SuppressWarnings("resource")
			 FileInputStream fis = new FileInputStream(path);
            MessageDigest md = MessageDigest.getInstance(Sha);

            //Using MessageDigest update() method to provide input
            byte[] buffer = new byte[8192];
            int numOfBytesRead;
            while ((numOfBytesRead = fis.read(buffer)) > 0) {
                md.update(buffer, 0, numOfBytesRead);
                
            }
            byte[] hash = md.digest();
            checksum = new BigInteger(1, hash).toString(16); //don't use this, truncates leading zero
            fis.close();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, null, ex);
        }
 
        return checksum;
    }

   /* public static void main(String args[]) {
        String file ="E:\\privacyProject\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Regeneration\\doc\\1.txt";
        String file1 = "E:\\privacyProject\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Regeneration\\temp\\1.txt";
MD5Checksum m=new MD5Checksum();
         System.out.println("MD5 checksum for file using Java 111: " + m.checkSum(file));
         System.out.println("MD5 checksum for file using Java 221: " + m.checkSum(file1));
    }*/
}
	
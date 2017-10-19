
package com.qihoo.role.demo;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    private static String tag = "Md5Util";

    private static final char[] HEX_CAPITALS = {
            '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    // private static final char[] HEX_LOWER_CASE = {
    // '0', '1', '2', '3', '4', '5', '6', '7',
    // '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    // };

    public static final String ALGORITHM = "MD5";

    public static final String DEFAULT_CHARSET = "UTF-8";

    private static final char[] HEX = { '0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    private static final char HEX_DIGITS[] = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
    };

    @SuppressWarnings("deprecation")
    public static String md5LowerCase(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance(ALGORITHM);
            md.update(string.getBytes(DEFAULT_CHARSET));
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            return buf.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static String md5Capitals(String string) {
        try {
            MessageDigest digester = MessageDigest.getInstance(ALGORITHM);
            byte[] buffer = string.getBytes(DEFAULT_CHARSET);
            digester.update(buffer);

            buffer = digester.digest();
            string = toHexCapitals(buffer);
        } catch (NoSuchAlgorithmException e) {
           
        } catch (UnsupportedEncodingException e) {
            
        }
        return string;
    }

    private static String toHexCapitals(byte[] b) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            int v = b[i];
            builder.append(HEX_CAPITALS[(0xF0 & v) >> 4]);
            builder.append(HEX_CAPITALS[0x0F & v]);
        }
        return builder.toString();
    }

    private static String toHexString(byte[] b) { // String to byte
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    /**
     * 根据文件的路径获取文件的MD5
     * 
     * @param filePath
     * @return 如果发生异常则返回空字符串
     */
    public static String getFileMD5(String filePath) {
        InputStream fis;
        byte[] buffer = new byte[1024];
        int numRead = 0;
        MessageDigest md5;
        try {
            fis = new FileInputStream(filePath);
            md5 = MessageDigest.getInstance("MD5");
            while ((numRead = fis.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }
            fis.close();
            return toHexString(md5.digest());
        } catch (Exception e) {
            return "";
        }
    }

    public static String md5(File file) {
        InputStream fis = null;
        byte[] buffer = new byte[1024];
        int numRead = 0;
        MessageDigest md5;
        
        try{
            fis = new FileInputStream(file);
            md5 = MessageDigest.getInstance(ALGORITHM);
            while((numRead = fis.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }
            
            return toHex(md5.digest());   
        } catch (Exception e) {
            
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    
                }
            }
        }
        
        return null;
    }

    private static String toHex(byte[] b) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            int v = b[i];
            builder.append(HEX[(0xF0 & v) >> 4]);
            builder.append(HEX[0x0F & v]);
        }
        return builder.toString();
    }
    
    public static String md5(String string) {
        try {
            MessageDigest digester = MessageDigest.getInstance(ALGORITHM);
            byte[] buffer = string.getBytes(DEFAULT_CHARSET);
            digester.update(buffer);

            buffer = digester.digest();
            string = toHexString(buffer);
        } catch (NoSuchAlgorithmException e) {

        } catch (UnsupportedEncodingException e) {

        } catch (Exception e) {

        }
        return string;
    }
    
    public static String encode(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(string.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
            // if (type) {
            // return buf.toString(); // 32
            // } else {
            // return buf.toString().substring(8, 24);// 16
            // }
        } catch (Exception e) {
            return null;
        }
    }

}

package com.qihoo.role.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class JavaDemo {

	public static void main(String[] args) {
		
		//***********************************************************
		//原始json串
		String json = "{\"list\":[\"ddd\"],\"roleName\":\"测试1111\",\"roleId\":1111}";
		
		System.out.println("json="+json);
		
		//进行压缩后base64
		String result = new String(Base64.encode(getGZipCompressed(json), Base64.DEFAULT));
		
		System.out.println("result="+result);
		
		//还原
		String j;
		try {
			j = new String(getGZipUncompress(Base64.decode(result.getBytes(), Base64.DEFAULT)));
			System.out.println("j="+j);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//***********************************************************
		
		
		//***********************************************************
		String url = "http://role.gamebox.360.cn/7/role/rolesave";
    	String role = URLEncoder.encode(resultByBase64);
		String param = "&plat=android&pname=com.miller.laojiumen.platform.qihoo360&appkey=50eb52044a60410359737cd3514eb14d&role=";
		String p2 = "&appid=203054056&lt=1488353159094";
	
		String sstr = "appkey=50eb52044a60410359737cd3514eb14d&appid=203054056&pname=com.miller.laojiumen.platform.qihoo360&plat=android&lt=1488353159094";
		String sign = Md5Util.md5LowerCase(sstr);
		System.out.println(sign);
		param = "sign="+sign + param + role + p2;
		System.out.println(URLEncoder.encode("com.miller.laojiumen.platform.qihoo360"));
		
		String s=HttpRequest.sendGet(url, param);
        System.out.println(s);
        
        //发送 POST 请求
        String sr=HttpRequest.sendPost(url, param);
        System.out.println(sr);
      //***********************************************************
	}
	
	private static String resultByBase64 = "eF5VkD0KwkAQhe8ydYr8rnGvYSkWghNZiLtxEyIqdvYiiBY2EawDFiqiHicJ3sLdqBC7+Xnve8PMYSY4sgFQxwApwrr0XY94jt02W75FbN+ASIoA45gJDhSKR1bkFzAgkAz5IGRxArTbU6q+TKaawfsjVMJylynVWBPB9l3TJa5FTPjkhJhiCNRqwrVSDZJppO3IE5QdlClK5UlZBNT8hvwFNBKLU1ZezuVyBY1rNFUZh+pWRaJQba56LSa6czxiG/UPvojX4Vbt8+q+LI7b6rn+gWrI4g18Xm39";
    

	//压缩
	 public static byte[] getGZipCompressed(String data) {
	        byte[] compressed = null;
	        try {
	            byte[] byteData = data.getBytes();
	            ByteArrayOutputStream bos = new ByteArrayOutputStream(
	                    byteData.length);
	            Deflater compressor = new Deflater();
	            compressor.setLevel(4); // 将当前压缩级别设置为指定值。
	            compressor.setInput(byteData, 0, byteData.length);
	            compressor.finish(); // 调用时，指示压缩应当以输入缓冲区的当前内容结尾。

	            // Compress the data
	            final byte[] buf = new byte[1024];
	            while (!compressor.finished()) {
	                int count = compressor.deflate(buf);
	                bos.write(buf, 0, count);
	            }
	            compressor.end(); // 关闭解压缩器并放弃所有未处理的输入。
	            compressed = bos.toByteArray();
	            bos.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return compressed;
	    }
	 
	 //解压
	 public static byte[] getGZipUncompress(byte[] data) throws IOException {
	        byte[] unCompressed = null;
	        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
	        Inflater decompressor = new Inflater();
	        try {
	            decompressor.setInput(data);
	            final byte[] buf = new byte[1024];
	            while (!decompressor.finished()) {
	                int count = decompressor.inflate(buf);
	                bos.write(buf, 0, count);
	            }

	            unCompressed = bos.toByteArray();
	            bos.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            decompressor.end();
	        }
	        // String test = bos.toString();
	        return unCompressed;
	    }
}

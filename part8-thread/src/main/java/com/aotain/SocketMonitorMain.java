package com.aotain;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Demo class
 *
 * @author bang
 * @date 2020/07/16
 */
public class SocketMonitorMain {

    public static void main(String[] args) {
        try{
            // http://106.75.73.230 jianshu
            // http://173.252.100.32 google
            // http://14.215.177.38 baidu

            URL u = new URL("http://14.215.177.38/index.html");
            /*初始化连接的数据*/
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(10);

            //这一步开始连接
            InputStream in = conn.getInputStream();
//            conn.disconnect();
            StringBuffer buf = new StringBuffer();
            int t =0;
            while((t = in.read())!=-1) {
                buf.append((char)t);
            }
            System.out.println(buf.toString());
        } catch (Exception e){
            System.out.println(e);
        }

    }

}

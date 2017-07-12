package com.example.yuanzi.newpostassistant.WebService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuanzi on 2017/7/12.
 */

public class WebService {

    private static String IP = "192.168.0.105:8080";

    public static String executeLogin(String username, String password){
        try{

            String path="http://"+IP+"/PostAssistant/LoginServlet";

            //发送指令和信息
            Map<String, String> params=new HashMap<String,String>();
            params.put("username",username);
            params.put("password",password);

            return sendPOSTRequest(path,params,"UTF-8");
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String executeRegister(String username, String password){
        try{

            String path="http://"+IP+"/PostAssistant/InsertServlet";

            //发送指令和信息
            Map<String, String> params=new HashMap<String,String>();
            params.put("username",username);
            params.put("password",password);

            return sendPOSTRequest(path,params,"UTF-8");
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private static String sendPOSTRequest(String path, Map<String,String> params,String encoding)throws Exception{

        List<NameValuePair> pairs=new ArrayList<NameValuePair>();
        if(params!=null &!params.isEmpty()){
            for(Map.Entry<String,String> entry:params.entrySet()){
                pairs.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            }
        }

        UrlEncodedFormEntity entity=new UrlEncodedFormEntity(pairs,encoding);

        HttpPost post=new HttpPost(path);
        post.setEntity(entity);
        DefaultHttpClient client=new DefaultHttpClient();

        //请求超时
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,10000);
        //读取超时
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,10000);

        HttpResponse response=client.execute(post);


        if(response.getStatusLine().getStatusCode()==200){
            return getInfo(response);
        }

        return null;

    }

    private static String getInfo(HttpResponse response)throws Exception{

        HttpEntity entity=response.getEntity();
        InputStream is=entity.getContent();

        byte[]data=read(is);

        return new String(data,"UTF-8");
    }




    public static byte[]read(InputStream instream)throws Exception{
        ByteArrayOutputStream outputStream =new ByteArrayOutputStream();
        byte[]buffer=new byte[1024];
        int len=0;
        while((len=instream.read(buffer))!=-1){
            outputStream.write(buffer,0,len);
        }
        instream.close();
        return outputStream.toByteArray();
    }
    private static String parseInfo(InputStream instream)throws Exception{
        byte[]data=read(instream);

        return new String(data,"UTF-8");
    }
}

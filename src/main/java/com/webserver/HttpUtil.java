package com.webserver;
import com.google.gson.Gson;
import com.webserver.domain.UserInfo;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

public class HttpUtil {


    public static String sendPost(Message message) throws URISyntaxException, IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost();

        // 设置超时时间
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);

        post = new HttpPost("http://127.0.0.1:8080");
        // 构造消息头
        post.setHeader("Content-type", "application/json; charset=utf-8");
        post.setHeader("Connection", "Close");

        // 构建消息实体
        StringEntity entity = new StringEntity(new Gson().toJson(message), Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");
        // 发送Json格式的数据请求
        entity.setContentType("application/json");
        post.setEntity(entity);

        HttpResponse response = httpClient.execute(post);
        return EntityUtils.toString(response.getEntity(),"utf-8");
    }

    public static String sendPost(String message, String url) throws URISyntaxException, IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost();

        // 设置超时时间
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);

        post = new HttpPost(url);
        // 构造消息头
        post.setHeader("Content-type", "application/json; charset=utf-8");
        post.setHeader("Connection", "Close");

        // 构建消息实体
        StringEntity entity = new StringEntity(message, Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");
        // 发送Json格式的数据请求
        entity.setContentType("application/json");
        post.setEntity(entity);

        HttpResponse response = httpClient.execute(post);
        return EntityUtils.toString(response.getEntity(),"utf-8");
    }


    public static String sendGet(String url) throws URISyntaxException, IOException {
        HttpClient httpClient = new DefaultHttpClient();


        // 设置超时时间
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);

        HttpGet get = new HttpGet(url);
        // 构造消息头
        get.setHeader("Content-type", "application/json; charset=utf-8");
        get.setHeader("Connection", "Close");


        HttpResponse response = httpClient.execute(get);
        return EntityUtils.toString(response.getEntity(),"utf-8");
    }

    public static void main(String[] args) throws IOException, URISyntaxException {


        //注册
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("331471400@qq.com");
        userInfo.setAccountType("email");
        userInfo.setPassword("123456");
//        String url = "http://he.sunzhuo.top:8090/hero/verifyCode?account=331471400@qq.com";
//        System.out.print(sendGet(url));

//        String url1 = "http://127.0.0.1:8090/hero/register?verifyCode=J8J7UB";
//        System.out.print(sendPost(new Gson().toJson(userInfo),url1));
//        http://he.sunzhuo.top/hero/verifyCode?account=331471400@qq.com
        String url2 = "http://he.sunzhuo.top:8090/hero/login";
        System.out.print(sendPost(new Gson().toJson(userInfo),url2));
    }


}
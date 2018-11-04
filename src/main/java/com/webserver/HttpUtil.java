package com.webserver;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
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


    public static void main(String[] args) throws IOException, URISyntaxException {
        System.out.print(sendPost(new Message("nihao")));;
    }
}
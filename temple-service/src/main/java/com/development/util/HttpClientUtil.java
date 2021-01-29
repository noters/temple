package com.development.util;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    /*static {
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(15000).setConnectTimeout(15000)
                .setConnectionRequestTimeout(15000).build();
    }*/

    public static String get(String url) throws Exception {
        if (url == null || "".equals(url)) {
            throw new RuntimeException("url请求地址不能为空");
        }
        CloseableHttpClient httpClient = HttpConnectionManager.getHttpClient();
        HttpGet httpget = new HttpGet(url);
        String json = null;
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpget);
            InputStream in = response.getEntity().getContent();
            json = IOUtils.toString(in, "UTF-8");
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return json;
    }

    public static String post(String url, Map<String, String> parameterMap) throws Exception {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = post(url, parameterMap, null, null, "UTF-8", httpClient);
        if (httpClient != null) {
            httpClient.close();
        }
        return result;
    }

    public static String postPool(String url, Map<String, String> parameterMap) throws Exception {
        CloseableHttpClient httpClient = HttpConnectionManager.getHttpClient();
        return post(url, parameterMap, null, null, "UTF-8", httpClient);
    }

    public static String postPool(String url, Map<String, String> parameterMap,
                                  Map<String, String> headerMap, String charset) throws Exception {
        CloseableHttpClient httpClient = HttpConnectionManager.getHttpClient();
        return post(url, parameterMap, null, headerMap, charset, httpClient);
    }

    /**
     * http的post方法
     * @param url 请求url地址
     * @param parameterMap 以form表单方式请求，与jsonOrXml二选一
     * @param jsonOrXml 无参数请求json或xml字符串，与parameterMap二选一
     * @param headerMap 请求头
     * @param charset 字符编码
     * @param httpClient http请求池
     * @return 返回
     * @throws Exception 异常
     */
    public static String post(String url, Map<String, String> parameterMap, String jsonOrXml,
                              Map<String, String> headerMap, String charset,
                              CloseableHttpClient httpClient) throws Exception {
        CloseableHttpResponse response;
        HttpEntity entity;
        String responseContent;
        if (url == null || "".equals(url)) {
            throw new RuntimeException("url请求地址不能为空");
        }
        // 创建httpPost
        HttpPost httpPost = new HttpPost(url);
        if (headerMap != null) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }
        // 创建参数队列
        if (parameterMap != null) {
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, charset));
        }
        if (jsonOrXml != null && !"".equals(jsonOrXml)) {
            StringEntity jsonEntity = new StringEntity(jsonOrXml, charset);
            httpPost.setEntity(jsonEntity);
        }
        // httpPost.setConfig(requestConfig);
        // 执行请求
        response = httpClient.execute(httpPost);
        entity = response.getEntity();
        responseContent = EntityUtils.toString(entity, charset);
        // 关闭连接,释放资源
        response.close();
        return responseContent;
    }

    /**
     * 发送post请求，不需要请求参数，直接将内容（json字符串）发送过去
     *
     * @param url 请求地址
     * @param json 请求内容
     * @return 返回
     * @throws Exception 异常
     */
    public static String post(String url, String json) throws Exception {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json;charset=UTF-8");
        return post(url, json, headerMap);
    }

    public static String postXml(String url, String xml) throws Exception {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Accept", "application/xml");
        headerMap.put("Content-Type", "application/xml;charset=UTF-8");
        return post(url, xml, headerMap);
    }

    /**
     * 发送post请求，直接将内容（字符串json，xml）发送过去，并指定头内容
     *
     * @param url 请求地址
     * @param jsonOrXml 请求内容
     * @param headerMap 请求头
     * @return 返回
     * @throws Exception 异常
     */
    public static String post(String url, String jsonOrXml, Map<String, String> headerMap) throws Exception {
        CloseableHttpClient httpClient = HttpConnectionManager.getHttpClient();
        return post(url, null, jsonOrXml, headerMap, "UTF-8", httpClient);
    }
}

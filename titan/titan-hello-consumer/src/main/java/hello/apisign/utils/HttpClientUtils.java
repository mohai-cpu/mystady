package hello.apisign.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {
    private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

    public HttpClientUtils() {
    }

    public static String doGet(String url) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                String strResult = EntityUtils.toString(response.getEntity());
                return strResult;
            }
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return null;
    }

    public static String doPost(String url, Map params) {
        BufferedReader in = null;

        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));
            List<NameValuePair> nvps = new ArrayList();
            Iterator iter = params.keySet().iterator();

            while(iter.hasNext()) {
                String name = (String)iter.next();
                String value = String.valueOf(params.get(name));
                nvps.add(new BasicNameValuePair(name, value));
            }

            request.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if (code != 200) {
                System.out.println("状态码：" + code);
                return null;
            } else {
                in = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "utf-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");

                while((line = in.readLine()) != null) {
                    sb.append(line + NL);
                }

                in.close();
                return sb.toString();
            }
        } catch (Exception var11) {
            var11.printStackTrace();
            return null;
        }
    }

    public static String doPost(String url, String params) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();
            if (state == 200) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                String var11 = jsonString;
                return var11;
            }

            log.error("请求返回:" + state + "(" + url + ")");
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException var23) {
                    var23.printStackTrace();
                }
            }

            try {
                httpclient.close();
            } catch (IOException var22) {
                var22.printStackTrace();
            }

        }

        return null;
    }
}
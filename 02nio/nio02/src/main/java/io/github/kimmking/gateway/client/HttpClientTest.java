package io.github.kimmking.gateway.client;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Objects;

public class HttpClientTest {

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 10; i++) {
            httpGet();
        }

    }

    private static void httpGet() throws IOException {

        CloseableHttpResponse response = null;
        HttpGet get = null;
        CloseableHttpClient client = null;

        try {

            client = HttpClients.createDefault();

            get = new HttpGet("http://localhost:8801");
            response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String html = EntityUtils.toString(entity, "utf-8");
                System.out.println(html);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (Objects.nonNull(response)) {
                response.close();
            }

            if (Objects.nonNull(get)) {
                get.releaseConnection();
            }

            client.close();
        }
    }

}

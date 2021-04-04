package api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;


public class FilmsTest {

    URIBuilder uriBuilder;
    HttpClient client;
    HttpGet httpGet;
    HttpResponse httpResponse;

    @Before
    public void beforeTest() throws URISyntaxException {
        client = HttpClientBuilder.create().build();
        uriBuilder=new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("swapi.dev");


    }

    @Test
    public void Test1() throws IOException, URISyntaxException {

        uriBuilder.setPath("api/films");
        httpGet=new HttpGet(uriBuilder.build());

        httpResponse=client.execute(httpGet);

        Assert.assertEquals(200, httpResponse.getStatusLine().getStatusCode());

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> respondMap=objectMapper.readValue(httpResponse.getEntity().getContent(), new TypeReference<Map<String, Object>>()
        {});


        List<Map<String, Object>> results = (List<Map<String, Object>>)respondMap.get("results");
        //System.out.println(results);

        Map<String, Object> title=results.get(0);
        System.out.println(title.get("title"));
        Assert.assertEquals("A New Hope", title.get("title"));
        Assert.assertFalse("A New Hope" == title.get("title"));






    }

}

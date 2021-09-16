package no.kristiania.http;

import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpClientTest {

    @Test
    void shouldGetSuccessfulResponseCode() {
        HttpClientMain client = new HttpClientMain("httpbin.org", 80, "/html");
        assertEquals(200, client.getStatusCode());
    }
}

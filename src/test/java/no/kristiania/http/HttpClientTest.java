package no.kristiania.http;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpClientTest {

    @Test
    void shouldGetSuccessfulResponseCode() throws IOException {
        HttpClientMain client = new HttpClientMain("httpbin.org", 80, "/html");
        assertEquals(200, client.getStatusCode());
    }

    @Test
    void shouldGetFailureResponseCode() throws IOException {
        HttpClientMain client =
                new HttpClientMain("httpbin.org", 80, "/status/403");
        assertEquals(403, client.getStatusCode());
    }

    @Test
    void shouldReadResponseHeaders() throws IOException {
        HttpClientMain client = new HttpClientMain("httpbin.org", 80, "/html");
        assertEquals("text/html; charset=utf-8", client.getHeader("Content-Type"));
    }

    @Test
    void shouldReadServerHeader() throws IOException {
        HttpClientMain client = new HttpClientMain("httpbin.org", 80, "/html");
        assertEquals("gunicorn/19.9.0", client.getHeader("Server"));
    }

    @Test
    void shouldReadContentLength() throws IOException {
        HttpClientMain client = new HttpClientMain("httpbin.org", 80, "/html");
        assertEquals(3741, client.getContentLength());
    }
}

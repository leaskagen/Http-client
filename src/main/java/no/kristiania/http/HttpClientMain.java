package no.kristiania.http;

import java.io.IOException;
import java.net.Socket;

public class HttpClientMain {

    public HttpClientMain(String host, int port, String requestTarget) {

    }

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("httpbin.org", 80);

        // request
        String request =
                "GET /html HTTP/1.1 \r\n" +
                // header fields
                "Host: httpbin.org \r\n" +
                "Connection: close \r\n" +
                "Pragma: no-cache \r\n" +
                "Cache-Control: no-cache \r\n" +
                "Upgrade-Insecure-Requests: 1 \r\n" +
                "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36 \r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9 \r\n" +
                // "Accept-Encoding: gzip, deflate \r\n" +
                "Accept-Language: en-US,en;q=0.9,nb;q=0.8,no;q=0.7 \r\n\r\n";
                socket.getOutputStream().write(request.getBytes());

        // response
        int c;
        while ((c = socket.getInputStream().read()) != -1) {
            // -1 is the only valie outside 0-255 that may be returnet from read().
            System.out.print( (char) c);
        }
    }

    public int getStatusCode() {
        return 200;
    }
}

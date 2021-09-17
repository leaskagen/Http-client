package no.kristiania.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;

public class HttpClientMain {

    private final int statusCode;
    private final HashMap<String, String> headerFieldsMap = new HashMap<>();

    public HttpClientMain(String host, int port, String requestTarget) throws IOException {
        Socket socket = new Socket(host, port);

        socket.getOutputStream().write(
                ("GET " + requestTarget + " HTTP/1.1 \r\n" +
                // header fields
                "Host: " + host + "\r\n" +
                "Connection: close \r\n" +
                "Pragma: no-cache \r\n" +
                "Cache-Control: no-cache \r\n" +
                "Upgrade-Insecure-Requests: 1 \r\n" +
                "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36 \r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9 \r\n" +
                // "Accept-Encoding: gzip, deflate \r\n" +
                "Accept-Language: en-US,en;q=0.9,nb;q=0.8,no;q=0.7 \r\n\r\n").getBytes()
        );

        headerFieldsMap.put("Content-Type", "text/html; charset=utf-8");

        String statusLine = readLine(socket);
        this.statusCode = Integer.parseInt(statusLine.split(" ")[1]);
    }

    // refactored out with own method
    private String readLine(Socket socket) throws IOException {
        StringBuilder result = new StringBuilder();
        InputStream in = socket.getInputStream();

        // response
        int c;
        while ((c = in.read()) != -1 && c != '\r') {
            // -1 is the only valie outside 0-255 that may be returnet from read().
            result.append( (char) c);
        }
        return result.toString();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getHeader(String responseHeaders) {
        return headerFieldsMap.get(responseHeaders);
    }
}

package classesJava.htttpserver;

import java.io.IOException;
import java.io.InputStream;

public class HttpUtils {
    public static String readerRequest(InputStream inputStream) throws IOException {
       String httpHeader  = "";
        while (!httpHeader.endsWith("\r\n\r\n")) {
            httpHeader += (char) inputStream.read();
        }
        return httpHeader;
    }

    public static String wrapConnectionsClouse(String str) {
        String response = "";
        response += "HTTP/1.1 200 OK\n";
        response += "Content-Type: text/html\n\n";
        String body =
                "<html>\n" +
                "<body>\n" +
                  str +"\n" +
                "</body>\n" +
                "</html>";
        response += body;
        return response;
    }

}

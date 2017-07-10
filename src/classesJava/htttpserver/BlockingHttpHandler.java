package classesJava.htttpserver;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.concurrent.Callable;


public class BlockingHttpHandler implements Callable<Void> {
    Socket socket;


    public BlockingHttpHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public Void call() throws Exception {
        String httpRequest = HttpUtils.readerRequest(socket.getInputStream());
        System.out.println("-----------------------");
        System.out.println(httpRequest);

        String httpResponse = HttpUtils.wrapConnectionsClouse("Hello");
        System.out.println(httpResponse);

        Writer writer = new OutputStreamWriter(socket.getOutputStream());
        writer.write(httpResponse);
        writer.flush();
        socket.close();
        return null;
    }

}

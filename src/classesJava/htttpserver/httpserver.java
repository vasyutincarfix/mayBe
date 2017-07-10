package classesJava.htttpserver;


import java.net.ServerSocket;
import java.net.Socket;

public class httpserver {

public static void main(String[] args) throws Exception {
    ServerSocket serverSocket = new ServerSocket(8080,256);
    while (true) {
        Socket socket = serverSocket.accept();
        new BlockingHttpHandler(socket).call();

    }
//    ExecutorService executor  =  new ThreadPoolExecutor(0,100,60L, TimeUnit.SECONDS, new SynchronousQueue<>());
//    ServerSocket serverSocket = new ServerSocket(8080,256);
//    while (true) {
//        Socket socket = serverSocket.accept();
//        executor.submit(new BlockingHttpHandler(socket));
//    }
}


}

package servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import computation.AsyncSearchSimulator;

public class MultiThreadedServer implements Runnable {

    protected int serverPort = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;
    private Logger logger = Logger.getLogger(MultiThreadedServer.class.getName());

    public MultiThreadedServer(int port) {
        this.serverPort = port;
    }

    public void run() {
        try {
            openServerSocket();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not open port", e);
            this.stop();
        }

        while (!isStopped()) {
            try (Socket clientSocket = serverSocket.accept()) {
                // on receiving a request, execute the heavy computation in a new thread
                new Thread(
                        new AsyncSearchSimulator(
                                clientSocket,
                                "Multithreaded Server"))
                        .start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.info("Server Stopped.");
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        this.isStopped = true;
    }

    private void openServerSocket() throws IOException {
        this.serverSocket = new ServerSocket(serverPort);
    }
}
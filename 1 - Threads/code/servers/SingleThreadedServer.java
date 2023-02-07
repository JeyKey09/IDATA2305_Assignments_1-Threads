package servers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingleThreadedServer implements Runnable {

    protected int serverPort = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;
    protected Logger logger = Logger.getLogger(SingleThreadedServer.class.getName());

    public SingleThreadedServer(int port) {
        this.serverPort = port;
    }

    public SingleThreadedServer() {
    }

    public void run() {

        try {
            openServerSocket();
        } catch (IOException e) {
            stop();
            logger.log(Level.SEVERE, "Could not open port", e);
        }

        while (!isStopped()) {
            try {
                Socket clientSocket = serverSocket.accept();
                computation.SearchSimulator.processClientRequest(clientSocket);
            } catch (IOException | InterruptedException e) {
                logger.log(Level.SEVERE, "Error accepting client connection", e);
            }
        }

        logger.log(Level.INFO, "Server Stopped.");
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        isStopped = true;
    }

    private void openServerSocket() throws IOException {
        this.serverSocket = new ServerSocket(serverPort);
    }
}
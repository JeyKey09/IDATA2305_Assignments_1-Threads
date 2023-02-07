package computation;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.ResponseGenerator;

public class AsyncSearchSimulator implements Runnable {

    protected Socket clientSocket = null;
    protected String serverText = null;
    private static Logger logger = Logger.getLogger(AsyncSearchSimulator.class.getName());

    public AsyncSearchSimulator(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
    }

    public void run() {
        try {
            long time1 = System.currentTimeMillis();
            logger.info("Request processing started at: " + time1);
            Thread.sleep(10 * 1000);
            long time2 = System.currentTimeMillis();
            logger.log(Level.INFO, "Request processing ended at: " + time2);
            StringBuilder response = new StringBuilder();
            String body = ResponseGenerator.generatorResponseHTML(time1, time2);
            response.append(ResponseGenerator.generatorResponseHeader(body.length()));
            response.append(body);

            clientSocket.getOutputStream().write(response.toString().getBytes());
            clientSocket.close();
        } catch (IOException | InterruptedException e) {
            // LOG error
        }
    }
}
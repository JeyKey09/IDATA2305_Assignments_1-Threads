package computation;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import utils.ResponseGenerator;

public class SearchSimulator {

    private static Logger logger = Logger.getLogger(SearchSimulator.class.getName());

    private SearchSimulator() {
    }

    public static void processClientRequest(Socket clientSocket) throws InterruptedException {
        long time1 = System.currentTimeMillis();
        logger.info("Request processing started at: " + time1);
        Thread.sleep(10 * 1000);
        long time2 = System.currentTimeMillis();
        logger.log(Level.INFO, "Request processing ended at: ", time2);
        StringBuilder response = new StringBuilder();
        String body = ResponseGenerator.generatorResponseHTML(time1, time2);
        response.append(ResponseGenerator.generatorResponseHeader(response.length()));
        response.append(body);
        try {
            clientSocket.getOutputStream().write(response.toString().getBytes());
            clientSocket.close();
        } catch (IOException e) {
            // LOG error
        }

    }
}
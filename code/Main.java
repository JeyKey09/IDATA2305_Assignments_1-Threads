import servers.MultiThreadedServer;
import servers.SingleThreadedServer;

class Main {
    public static void main(String[] args) {
        System.out.println("Starting server");
        new MultiThreadedServer().run();
    }
}
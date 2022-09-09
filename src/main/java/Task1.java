
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;

public class Task1 {
    private static final String USER_JSON_FILE = ".\\src\\Files\\user.json";
    private static final String BASE_API_URL = "https://jsonplaceholder.typicode.com";
    private static final String USERS_API_URL = BASE_API_URL + "/users";
    public static void main(String[] args) throws IOException {
        sendGET();
        sendPOST();
        sendPUT(7);
        sendDELETE(5);
        sendGETID(5);
        sendGETUSER("Leopoldo_Corkery");
    }

    private static void sendGET() throws IOException {
        var connection = createConnection("GET", USERS_API_URL);
        printResponse(connection);
    }

    private static void sendPOST() throws IOException {
        var connection = createConnection("POST", USERS_API_URL);
        attachBody(connection, USER_JSON_FILE);
        printResponse(connection);
    }

    private static void sendPUT(int userId) throws IOException {
        var connection = createConnection("PUT", USERS_API_URL + "/" + userId);
        attachBody(connection, USER_JSON_FILE);
        printResponse(connection);
    }

    private static void sendDELETE(int userId) throws IOException {
        var connection = createConnection("DELETE", USERS_API_URL + "/" + userId);
        printResponse(connection);
    }

    private static void sendGETID(int userId) throws IOException {
        var connection = createConnection("GET", USERS_API_URL + "/" + userId);
        printResponse(connection);
    }

    private static void sendGETUSER(String name) throws IOException {
        var connection = createConnection("GET", USERS_API_URL + "?username=" + name);
        printResponse(connection);
    }

    private static HttpURLConnection createConnection(String method, String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        return connection;
    }

    private static void attachBody(HttpURLConnection connection, String filename) throws IOException {
        connection.setDoOutput(true);
        OutputStream os = connection.getOutputStream();
        os.write(Files.readAllBytes(new File(filename).toPath()));
        os.flush();
        os.close();
    }

    private static void printResponse(HttpURLConnection connection) throws IOException {
        int responseCode = connection.getResponseCode();
        System.out.println(connection.getRequestMethod() + " response code: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        } else {
            System.out.println(connection.getRequestMethod() + " request not worked");
        }
    }
}

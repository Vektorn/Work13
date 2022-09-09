
import Model.Todos;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class Task3 {
    private static final String BASE_API_URL = "https://jsonplaceholder.typicode.com";
    private static final String USERS_API_URL = BASE_API_URL + "/users";
    public static void main(String[] args) throws IOException {
        int userId = 1;
        var X = userId;
        var nonCompletedTodos = getNonCompletedTodos(userId);
        var serialized = new ObjectMapper().writeValueAsString(nonCompletedTodos);
        System.out.println(serialized);
    }

    private static Object[] getNonCompletedTodos(int X) throws IOException {
        var connection = createConnection("GET", USERS_API_URL + "/" + X + "/todos");
        var response = getResponse(connection);
        var todos = new ObjectMapper().readValue(response, Todos[].class);
        var nonCompletedTodos = Arrays.stream(todos)
                .filter(x -> x.getCompleted() == false)
                .toArray();
        return nonCompletedTodos;
    }
    private static HttpURLConnection createConnection(String method, String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        return connection;
    }

    private static String getResponse(HttpURLConnection connection) throws IOException {
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
            String responseString = response.toString();
            return responseString;
        } else {
            System.out.println(connection.getRequestMethod() + " request not worked");
            return null;
        }
    }
}

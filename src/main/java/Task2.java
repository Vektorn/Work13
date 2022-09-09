
import Model.Comment;
import Model.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class Task2 {
    private static final String BASE_API_URL = "https://jsonplaceholder.typicode.com";
    private static final String USERS_API_URL = BASE_API_URL + "/users";
    private static final String POSTS_API_URL = BASE_API_URL + "/posts";

    public static void main(String[] args) throws IOException {
        var userId = 1;
        var latestPost = getLatestPost(userId);
        var comments = getCommentsForPost(latestPost.getId());
        saveCommentsToFile(userId, comments);
    }

    private static Post getLatestPost(int userId) throws IOException {
        var connection = createConnection("GET", USERS_API_URL + "/" + userId + "/posts");
        var response = getResponse(connection);
        var posts = new ObjectMapper().readValue(response, Post[].class);
        var sortedPosts = Arrays.stream(posts).sorted((o1, o2) -> {
            if (o1.getId() == o2.getId())
            {
                return 0;
            }
            if (o1.getId() > o2.getId())
            {
                return 1;
            }

            return -1;
        }).toArray();
        var latestPost = (Post) sortedPosts[sortedPosts.length-1];
        return latestPost;
    }

    private static Comment[] getCommentsForPost(int postId) throws IOException {
        var connection = createConnection("GET", POSTS_API_URL + "/" + postId + "/comments");
        var response = getResponse(connection);
        var comments = new ObjectMapper().readValue(response, Comment[].class);
        return comments;
    }

    private static void saveCommentsToFile(int userId, Comment[] comments) throws IOException {
        var postId = comments[0].postId;
        var filename = "user-" + userId + "-post-" + postId + "-comments.json";
        new ObjectMapper().writeValue(new File(filename), comments);
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
            System.out.println(responseString);
            return responseString;
        } else {
            System.out.println(connection.getRequestMethod() + " request not worked");
            return null;
        }
    }
}

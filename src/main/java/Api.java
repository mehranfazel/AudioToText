import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class Api {

    public HttpResponse<String> postTranscript(String url) throws URISyntaxException {
        Transcript transcript = new Transcript();
        transcript.setAudio_url(url);
        Gson gson = new Gson();
        String jsonRequest = gson.toJson(transcript);


        HttpRequest postRequest = HttpRequest.newBuilder().uri(new URI("https://api.assemblyai.com/v2/transcript")).
                header("Authorization", "ce1c5961afca48e88dff478dc445afb6")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest)).build();

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> postResponse;
        try {
            postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return postResponse;
        }

    public String getTranscript(HttpResponse<String> postResponse) throws URISyntaxException, IOException, InterruptedException {
        Gson gson = new Gson();
        Transcript transcript = new Transcript();
        HttpClient httpClient = HttpClient.newHttpClient();

        transcript = gson.fromJson(postResponse.body(), Transcript.class);
        HttpRequest getRequest = HttpRequest.newBuilder().
                uri(new URI("https://api.assemblyai.com/v2/transcript/" + transcript.getId()))
                .header("Authorization", "ce1c5961afca48e88dff478dc445afb6")
                .GET()
                .build();

        while (true) {
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            transcript = gson.fromJson(getResponse.body(), Transcript.class);

            if ("completed".equals(transcript.getStatus())||"error".equals(transcript.getStatus()))
            {
                System.out.println(transcript.getText());
                break;

            }
            Thread.sleep(4000);
    }
        return transcript.getText();
}}

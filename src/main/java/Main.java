import java.io.IOException;
import java.net.URISyntaxException;


public class Main {
    public static void main(String[] a) throws URISyntaxException, IOException, InterruptedException {

        Api api = new Api();
        api.getTranscript(api.postTranscript("https://github.com/mehranfazel/AudioToText/blob/main/Thirsty.mp4?raw=true"));
    }
}



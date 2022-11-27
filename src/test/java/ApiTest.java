import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;

class ApiTest {
    Api api =new Api();
String urlOne = "https://github.com/mehranfazel/AudioToText/blob/main/Record (online-voice-recorder.com).mp3?raw=true";
String urlTwo = "https://github.com/mehranfazel/AudioToText/blob/main/Thirsty.mp4?raw=true";
    @Test
     void  postTranscriptShouldReturnStringList() throws URISyntaxException {

        assertNotNull(api.postTranscript(urlOne).body());
    }
    @Test
    void getTranscriptOutput() throws URISyntaxException, IOException, InterruptedException {
      assertEquals("Hello, this is Sina from MIT University.",api.getTranscript(api.postTranscript(urlOne)));
      assertEquals("These pretzels are making me thirsty.",api.getTranscript(api.postTranscript(urlTwo)));

    }


}
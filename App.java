import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration; 
import static java.time.temporal.ChronoUnit.MINUTES;



public class App{

    String url = "https://viacep.com.br/ws/01001000/json/";

    try { HttpClient httpClient = HttpClient.newBuilder() .connectTimeout(Duration.of(1, MINUTES))
         .build();

         httpRequest = HttpRequest.newBuilder()
         .GET()
        .uri(URI.create(url))
         .build();

         httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
          System.out.println(httpResponse.body()); 
        } catch (IOException e) { e.printStackTrace();
             throw new RuntimeException(e.getMessage()); 
            } catch (InterruptedException e) { e.printStackTrace(); throw new RuntimeException(e.getMessage()); }
}
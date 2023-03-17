import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.MINUTES;



public class App{
 
     public  App (){
          Scanner in = new Scanner(System.in);
          String estado = in.next();
          String cidade = in.next();
          String logradouro = in. next();
         // System.out.println("digite um cep valido");
         // String cep = in.next();
         
         // String url = "https://viacep.com.br/ws/01001000/json/";
        // String url = "https://viacep.com.br/ws/"+cep+"/json/";
         //cidade com nome composto ta dando problema 
          //String urn = "https://viacep.com.br/ws/RS/Gravatai/Panorama/json/";
          String urn = "https://viacep.com.br/ws/"+estado+"/"+cidade+"/"+logradouro+"/json/";

    try { HttpClient httpClient = HttpClient.newBuilder() .connectTimeout(Duration.of(1, MINUTES))
         .build();
       var httpRequest = HttpRequest.newBuilder()
     .GET()
     .uri(URI.create(urn))
     
     .build();
      var httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
          System.out.println(httpResponse.body());
        
        } catch (IOException | InterruptedException e) { 
          e.printStackTrace();
          throw new RuntimeException(e.getMessage());
        }

     }
    

     public static void main(String args[]){
          new App();
     }

}

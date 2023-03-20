import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.MINUTES;

public class App {

    public static void main(String args[]) {
        menu();       
    }

    public static void menu(){
        Scanner scanner2 = new Scanner(System.in);

        int escolha = 0;

        while (escolha != 3) {
            System.out.println("O que você deseja?");
            System.out.println("1. Buscar um CEP");
            System.out.println("2. Validar um CEP");
            System.out.println("3. Sair");

            escolha = scanner2.nextInt();
            System.out.println("Sua escolha foi:" + escolha);
            System.out.println();

            switch (escolha) {
                case 1:
                    buscaCep();
                    break;
                case 2:
                    validaCep();
                    break;
                case 3:
                    System.out.println("Obrigado por utilizar o programa");
                    break;
                default:
                    System.out.println("Não sei o que tu fez, mas certo não foi.");
                    break;
            }
            
        }

    }

    public static void buscaCep(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor digite um cep");
        String cep = scanner.nextLine();
        
        String url = "https://viacep.com.br/ws/" + cep + "/json";

        try {
            HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.of(1, MINUTES))
                    .build();

            var httpRequest = HttpRequest.newBuilder()

                    .GET()
                    .uri(URI.create(url))
                    //.header("CEP", cep)
                    .build();

            var httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String json = httpResponse.body();
            
            if(json.charAt(0) != '{'){
                System.out.println("Errou no cep amigão, paia demais. Vamo denovo");
            }else{
                System.out.println(json);
                System.out.println();
            }
            menu();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

    }

    public static void validaCep(){
      Scanner in = new Scanner(System.in);
      System.out.println("Digite seu estado");
      String estado = in.nextLine();
      System.out.println("Digite sua cidade");
      String cidade = in.nextLine();
      System.out.println("Logradouro");
      String logradouro = in. nextLine();
  
      String urn = "https://viacep.com.br/ws/"+ estado +"/"+ cidade +"/"+ logradouro +"/json/";
      try {
        HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.of(1, MINUTES))
                .build();

        var httpRequest = HttpRequest.newBuilder()

                .GET()
                .uri(URI.create(urn))
               
                .build();

        var httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String json = httpResponse.body();
   
         if(json.charAt(0) != '['){
            System.out.println("Errou em algum dos dados informados anteriormente");
        }else{
            System.out.println(json);
             System.out.println();
        }
      menu();
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
        throw new RuntimeException(e.getMessage());
    }
    }

  }

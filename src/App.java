import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
  public static void main(String[] args) throws Exception{

    //    String url = "https://imdb.api.com/en/API/Top250Movies/k_" endereço do imdb sem token de acesso
    String url = "https://alura-filmes.herokuapp.com/conteudos";
    URI address = URI.create(url);
    var client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder(address).GET().build();
    HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    String body = response.body();

    System.out.println(body);
    JSONParser parser = new JSONParser();
    List<Map<String, String>> movieList = parser.parse(body);

    for (Map<String, String> movie : movieList) {
      System.out.println(movie.get("fullTitle"));
      System.out.println(movie.get("image"));

      System.out.println(movie.get("imDbRating"));
      System.out.println("----");
    }
  }
}

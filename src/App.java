import java.io.FileInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os top 250 filmes
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
       // String url = "https://alura-imdb-api.herokuapp.com/movies";

       try (FileInputStream fis = new FileInputStream("chave.properties")) {
            Properties pro = new Properties();
            pro.load(fis);
            String url = pro.getProperty("caminho");
            URI endereco = URI.create(url);
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(endereco).GET().build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            String body = response.body();

            // extrair só os dados que interessam (titulo, poster, classificação)
            var parser = new JsonParser();
            List<Map<String, String>> listaDeFilmes = parser.parse(body);

            // exibir e manipular os dados 
            for (Map<String,String> filme : listaDeFilmes) {

                System.out.println("\u001b[34;1m \u001b[40;1m Título\u001b[m:" + filme.get("title"));

                System.out.println("\u001b[34;1m \u001b[40;1m Classificação\u001b[m:" + filme.get("imDbRating"));
    
                System.out.println("\u001b[34;1m \u001b[40;1m Link da Imagem\u001b[m:" + filme.get("image"));
    
                System.out.println("---------------------------------------");
            }
    }catch( Exception e){
        System.out.println(e.getMessage());
    }

    }
}

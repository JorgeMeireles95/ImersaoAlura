import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
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
        var geradora = new GeradorDeFigurinhas();
        for (Map<String, String> filme : listaDeFilmes) {

            System.out.println("\u001b[34;1m \u001b[40;1m Título\u001b[m:" + filme.get("title"));
            System.out.println("\u001b[34;1m \u001b[40;1m Link da Imagem\u001b[m:" + filme.get("image"));

            System.out.println("---------------------------------------");

            String urlImagem = filme.get("image");

            
            //soluçoes para tratar URL caso necessário
            
            // solução extensa
            // String novaUrl = urlImagem.substring(0,urlImagem.indexOf('@')+1);
            // novaUrl += urlImagem.substring(urlImagem.lastIndexOf('.'),
            // urlImagem.length());

            //solução simples
            // String novaUrl = urlImagem.replaceAll("(_*)", "");

            
            //solução simples 02
            // String novaUrl = urlImagem.replaceAll("(?<=)[_]+", "");


            
            String titulo = filme.get("title");
            if (titulo.contains(":")) {
                titulo = titulo.replace(":", " -");

            } else {
                titulo = filme.get("title");

            }

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo + ".png";
            geradora.cria(inputStream, nomeArquivo);

        }

    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}}

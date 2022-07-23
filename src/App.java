import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes

        // String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
        // String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/TopMovies.json";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        
        //Nasa
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java/api/NASA-APOD.json";
       // String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
  
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // exibir e manipular os dados 
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);
            try {
            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo =  conteudo.titulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.titulo());
        
            } catch (java.io.FileNotFoundException err) {
                //Link da imagem quebrado
                System.out.println("Imagem não encontrada ou Link inválido");
            } catch (java.io.IOException err) {
                //Se a imagem estiver com erro por estar em outro tipo de canal
                System.out.println("Imagem com erro");
            }
        }
    }
}

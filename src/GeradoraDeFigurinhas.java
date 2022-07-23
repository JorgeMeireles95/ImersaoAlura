import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        // leitura da imagem
        // InputStream inputStream = 
        //             new FileInputStream(new File("entrada/filme-maior.jpg"));
        // InputStream inputStream = 
        //                 new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg")
        //                 .openStream();
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TYPE_INT_ARGB_PRE);

        // copiar a imagem original pra novo imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

     // Colocando uma fonte externa

		Font font = Font.createFont(Font.TRUETYPE_FONT, new File("fontes/COMIC.TTF"));
		graphics.setFont(font.deriveFont(Font.BOLD, 64));
		graphics.setColor(Color.BLUE);

		// escrever uma frase na nova imagem e a centralizando
		graphics.drawString("ESPETACULAR //", (novaImagem.getWidth() / 4), (novaAltura - 100));

        // escrever a nova imagem em um arquivo
        File diretorio = new File("espaco");
		if (!diretorio.exists()) {
			diretorio.mkdir();
		}

		// escrever a nova imagem em um arquivo
		ImageIO.write(novaImagem, "png", new File(diretorio, nomeArquivo));
       
    }

}

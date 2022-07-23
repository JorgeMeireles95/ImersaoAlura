import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    

    public void cria(InputStream inputStream, String nomeArquivo) throws Exception {

       
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra novo imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.HANGING_BASELINE, 26);
        graphics.setColor(Color.MAGENTA);
        graphics.setFont(fonte);

     
        // escrever uma frase na nova imagem
        graphics.drawString("TOP++", 100, novaAltura - 100);

       // criando um diretorio para as imagens se não existir
		File diretorio = new File("figuras");
		if (!diretorio.exists()) {
			diretorio.mkdir();
		}

		// escrever a nova imagem em um arquivo
		ImageIO.write(novaImagem, "png", new File(diretorio, nomeArquivo));

    }

}

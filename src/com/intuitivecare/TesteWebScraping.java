package com.intuitivecare;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

public class TesteWebScraping {
    // Variável Global
    static String caminho;
    static Scanner e = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        // Variáveis
        char disco;
        String destino;

        // Acessando Link
        Document site = Jsoup.connect(
                        "https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss")
                .get();

        // Procurando versão mais recente
        Elements links = site.getElementsContainingOwnText("Novembro/2021");
        for (Element link : links) {
            String linkHref = link.attr("href");
            caminho = linkHref;
        }

        // Acessando Link para chegar no arquivo
        Document version = Jsoup.connect(caminho).get();

        // Procurando link do arquivo pdf
        Elements links1 = version.getElementsContainingOwnText(".pdf");
        for (Element linkFinal : links1) {
            String linkHref = linkFinal.attr("href");
            caminho = linkHref;
        }

        // Disco que deseja salvar o arquivo
        System.out.println("Digite a letra do disco que deseja salvar o arquivo: ");
        disco = e.nextLine().toUpperCase().charAt(0);
        System.out.println("");

        // Usuário digitar destino para o arquivo pdf, exemplo - workspace\\padrao__tiss
        System.out.println("Digite o destino que deseja para o arquivo pdf...");
        System.out.println("Lembrando que para separar diretórios ultiliza-se barras invertidas duplas “\\\\”, e digitando um nome inexistente é criado uma nova pasta");
        System.out.println("Informe o destino: ");
        destino = e.next();

        // Link para download do arquivo
        URL url = new URL(caminho);

        // Caminho de download do arquivo
        File file = new File(disco + ":\\" + destino + "\\padrao_tiss_componente_organizacional_201902.pdf");

        // FileUtils é uma classe contidada na Biblioteca Commons IO, que possui métodos
        // estáticos, no caso copyURLToFile é um deles que acessa o contéudo de uma url
        // e copia em forma de arquivo.
        FileUtils.copyURLToFile(url, file);

        System.out.println("Arquivo Salvo com sucesso!!");
    }
}

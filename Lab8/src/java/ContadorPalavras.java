import java.io.*;

public class ContadorPalavras {
    public static void main(String[] args) throws Exception {
    if (args.length < 1) {
        System.err.println("Usage: java Sum filepath1 filepath2 filepathN");
        System.exit(1);
    }

//many exceptions could be thrown here. we don't care
    int acumula = 0;
    for (String path : args) {
        acumula += contarPalavras(path);
    }
    System.out.println(acumula);
}

    static int contarPalavras(String nomeArquivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
        int count = 0;
        String linha;
        while ((linha = br.readLine()) != null) {
            count += linha.split("\\s+").length;
        }
        br.close();
        return count;
    }
}

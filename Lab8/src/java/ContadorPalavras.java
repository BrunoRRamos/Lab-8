import java.io.*;
import java.util.concurrent.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ContadorPalavras {
    public static void main(String[] args) throws Exception {
    if (args.length < 1) {
        System.err.println("Usage: java Sum filepath1 filepath2 filepathN");
        System.exit(1);
    }
    

//many exceptions could be thrown here. we don't care
    for (String path : args) {
        System.out.println(contarPalavras(path));
    }
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

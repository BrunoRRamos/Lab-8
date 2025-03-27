package java;

import java.io.*;
import java.util.concurrent.*;

public class ContadorPalavrasConcorrente {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore mutex = new Semaphore(1);
        int total = 0;

        if (args.length < 1) {
            System.err.println("Usage: java Sum filepath1 filepath2 filepathN");
            System.exit(1);
        }
        
    
    //many exceptions could be thrown here. we don't care
        for (String path : args) {
            executorService.execute(() -> {
                try {
                    contarPalavras(path, total, mutex);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executorService.shutdown();

        System.out.println(total);
    }

    

    static void contarPalavras(String nomeArquivo, int total, Semaphore mutex) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
        String linha;
        while ((linha = br.readLine()) != null) {
            mutex.acquire();
            total += linha.split("\\s+").length;
            mutex.release();
        }
        br.close();
    }
}

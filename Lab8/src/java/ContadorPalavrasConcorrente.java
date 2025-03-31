import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ContadorPalavrasConcorrente {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicInteger total = new AtomicInteger(0);

        if (args.length < 1) {
            System.err.println("Usage: java Sum filepath1 filepath2 filepathN");
            System.exit(1);
        }

    //many exceptions could be thrown here. we don't care
        for (final String path : args) {
            System.out.println(path);
            executorService.execute(() -> {
                try {
                    contarPalavras(path, total);
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(total.get());
    }

    static void contarPalavras(String nomeArquivo, AtomicInteger total) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
        String linha;
        int buffer = 0;
        while ((linha = br.readLine()) != null) {
            buffer += linha.split("\\s+").length;
        }
        br.close();
        total.addAndGet(buffer);
    }
}

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    /** NÚMERO DE THREADS **/
    static final int THREAD_COUNT = 1;
    static ExecutorService threadPool;
    /** COMPRIMENTO DA MATRIZ **/
    static final int MATRIZ_LENGTH = 10000;

    /** VALOR MÁXIMO A SER GERADO **/
    static final int MAX_NUM = 100;

    /** MÉTODO DE PREENCHIMENTO
     * 1 - sequencial
     * 2 - aleatório
     **/
    static int op = 1;

    /** IMPRIMIR MATRIZ
     * 0 - Não
     * 1 - Sim
     * **/
    static int imprimir = 0;

    static int[][] matrizEntrada;
    static int[][] matrizSaida;

    //vetor auxiliar para percorrer a matriz
    static int[] posicoes;

    // Vetor auxiliar para carregar os valores lidos da matriz de entrada
    static int[] valores;

    // Array de semáforos
    static Semaphore[] mutex;

    static CountDownLatch latch;

    static int numThreads;

    static int[] posPreenchida;

    public static void main(String[] args) throws InterruptedException {
        long timeInicio = System.currentTimeMillis();

        matrizEntrada = new int[MATRIZ_LENGTH][MATRIZ_LENGTH];
        matrizSaida = new int[MATRIZ_LENGTH][MATRIZ_LENGTH];

        threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

        switch (op) {
            case 1:
                preencherSequencial();
                lerMatrizEntrada();
                preencherSaida();
                break;
            case 2:
                preencherAleatorio();
                lerMatrizEntrada();
                preencherSaida();
                break;
            default:
                System.out.println("Opção incorreta.");
                return;
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.MINUTES);

        long timeFim = System.currentTimeMillis();

        if (imprimir == 1) {
            System.out.println("Imprimir matriz de entrada:");
            pressEnter();
            imprimir(matrizEntrada);

            System.out.println("Imprimir matriz de saída:");
            pressEnter();
            imprimir(matrizSaida);
        }

        long timeTotal = System.currentTimeMillis();
        System.out.println("Número de Threads: " + THREAD_COUNT);
        System.out.println("Tempo para preencher matriz de saída: " + ((timeFim - timeInicio)) + " milisegundos.");
        System.out.println("Tempo total de execução: " + ((timeTotal - timeInicio)) + " milisegundos.");
    }

    static void preencherSequencial() throws InterruptedException {
        /** Percorre e popula a matriz sequencialmente com números aleatórios nas posições correspondentes
         * Divide as posições do vetor entre as threads para preencher em paralelo
         * Salva a última posição preenchida em um contador
         **/
        // Contador que salva última posição da matriz preenchida
        numThreads = THREAD_COUNT <= 2? THREAD_COUNT : THREAD_COUNT/2;
        posPreenchida = new int[numThreads];

        latch = new CountDownLatch(numThreads*2);

        /** Inicia as threads de preenchimento **/
        for (int i = 0; i < numThreads; i++) {
            final int inicio = (MATRIZ_LENGTH*MATRIZ_LENGTH / numThreads) * i;
            posPreenchida[i] = inicio;
            // Garante que a última thread vá até o final do array
            final int fim = i == numThreads-1? MATRIZ_LENGTH*MATRIZ_LENGTH-1 : (MATRIZ_LENGTH*MATRIZ_LENGTH / numThreads) * (i+1) - 1;
            final int threadId = i;

            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = inicio; i <= fim; i++) {
                        // Converte posição do array para matriz
                        int x = i % matrizEntrada.length;
                        int y = i / matrizEntrada.length;
                        int valor = ThreadLocalRandom.current().nextInt(MAX_NUM) + 1;

                        matrizEntrada[x][y] = valor;
                        posPreenchida[threadId]++;
                    }

                    latch.countDown();
                }
            });
        }
    }

    static void preencherAleatorio() throws InterruptedException {
        posicoes = new int[MATRIZ_LENGTH*MATRIZ_LENGTH];

        /** Inicializa o vetor auxiliar com valores de 0 até o número de elementos da matriz
         * Divide as posições do vetor entre as threads para preencher em paralelo
         **/

        latch = new CountDownLatch(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int inicio = (posicoes.length / THREAD_COUNT) * i;
            // Garante que a última thread vá até o final do array
            final int fim = i == THREAD_COUNT -1? posicoes.length-1 : (posicoes.length / THREAD_COUNT) * (i+1) - 1;

            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = inicio; i <= fim; i++) {
                        posicoes[i] = i;
                    }

                    latch.countDown();
                }
            });
        }
        latch.await();
        shuffle(posicoes);

        /** Percorre o vetor auxiliar e popula a matriz com números aleatórios nas posições correspondentes
         * Divide as posições do vetor entre as threads para preencher em paralelo
         * Salva a última posição preenchida em um contador
         **/
        // Contador que salva última posição da matriz preenchida
        numThreads = THREAD_COUNT <= 2? THREAD_COUNT : THREAD_COUNT/2;
        posPreenchida = new int[numThreads];

        latch = new CountDownLatch(numThreads*2);

        /** Inicia as threads de preenchimento **/
        for (int i = 0; i < numThreads; i++) {
            final int inicio = (posicoes.length / numThreads) * i;
            posPreenchida[i] = inicio;
            // Garante que a última thread vá até o final do array
            final int fim = i == numThreads-1? posicoes.length-1 : (posicoes.length / numThreads) * (i+1) - 1;
            final int threadId = i;

            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = inicio; i <= fim; i++) {
                        // Converte posição do array para matriz
                        int x = posicoes[i] % matrizEntrada.length;
                        int y = posicoes[i] / matrizEntrada.length;
                        int valor = ThreadLocalRandom.current().nextInt(MAX_NUM) + 1;

                        matrizEntrada[x][y] = valor;
                        posPreenchida[threadId]++;
                    }

                    latch.countDown();
                }
            });
        }
    }

    static void lerMatrizEntrada() throws InterruptedException {
        /** Percorre as posições já preenchidas da matriz de entrada e salva as ocorrências dos valores em outro contador
         * Cada thread lê o contador de uma thread de preenchimento e grava até que posição já foi lido
         **/
        // Contador que salva última posição lida
        int[] posLida = new int[posPreenchida.length];

        // Inicializa o vetor auxiliar de valores com o valor máximo gerado
        valores = new int[MAX_NUM+1];

        // Inicializa o vetor de semáforos para conter um para cada posição do vetor valores
        mutex = new Semaphore[valores.length];

        /** Inicia as threads de leitura **/
        for(int i = 0; i < numThreads; i++) {
            final int inicio = (MATRIZ_LENGTH*MATRIZ_LENGTH / numThreads) * i;
            posLida[i] = inicio;
            // Garante que a última thread vá até o final do array
            final int fim = i == numThreads-1? MATRIZ_LENGTH*MATRIZ_LENGTH-1 : (MATRIZ_LENGTH*MATRIZ_LENGTH / numThreads) * (i+1) - 1;
            mutex[i] = new Semaphore(1);
            final int threadId = i;

            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    // Enquanto não ler a última posição destinada a thread
                    while (posLida[threadId] <= fim) {

                        if (posLida[threadId] <= posPreenchida[threadId]) {
                            // Converte posição do array para matriz
                            int x = posLida[threadId] % matrizEntrada.length;
                            int y = posLida[threadId] / matrizEntrada.length;
                            int valor = matrizEntrada[x][y];


                            try {
                                mutex[threadId].acquire();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            valores[valor]++;
                            mutex[threadId].release();

                            if(posLida[threadId] == fim) break;

                            posLida[threadId]++;
                        }
                    }
                    latch.countDown();
                }
            });
        }

        latch.await();
    }

    static void preencherSaida() {
        /** Preenche a matriz de saída baseada no vetor de valores **/
        int posAtual = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            // Calcula a posição onde cada thread deve escrever a partir da ocorrência dos valores
            final int primPos = (valores.length / THREAD_COUNT) * i;
            final int ultPos = i == THREAD_COUNT-1? valores.length-1 : (valores.length / THREAD_COUNT) * (i+1) - 1;
            int somaOcorr = 0;
            for (int j = primPos; j <= ultPos; j++) {
                somaOcorr += valores[j];
            }
            final int inicio = posAtual;
            final int fim = i == THREAD_COUNT-1? MATRIZ_LENGTH*MATRIZ_LENGTH : inicio + somaOcorr;
            posAtual = fim;

            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    int pos = primPos;
                    int cont = 0;

                    for (int i = inicio; i < fim; i++) {
                        // Se gravou todas as ocorrências daquele valor, passa para o próximo valor e zera o contador
                        if (cont == valores[pos]) {
                            do {
                                if(pos < valores.length-1) pos++;
                                else continue;
                            } while (valores[pos] == 0);
                            cont = 0;
                        }
                        // Converte posição do array para matriz
                        int y = i % matrizSaida.length;
                        int x = i / matrizSaida.length;
                        matrizSaida[x][y] = pos;
                        cont++;
                    }
                }
            });
        }
    }

    static void shuffle(int[] array) {
        int index;
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            if (index != i) {
                array[index] ^= array[i];
                array[i] ^= array[index];
                array[index] ^= array[i];
            }
        }
    }

    static void imprimir(int[] vetor) {
        StringBuilder vetorStr = new StringBuilder();
        for(int x : vetor) {
            vetorStr.append("["+ String.format("%d", x) +"]");
        }
        vetorStr.append("\n");
        System.out.println(vetorStr.toString());
    }

    static void imprimir(int[][] matriz) {
        int[][] matrizCopia = matriz.clone();
        StringBuilder matrizStr = new StringBuilder();
        for (int[] linha : matrizCopia) {
            for (int x : linha) {
                matrizStr.append("["+ String.format("%2d", x) +"]");
            }
            matrizStr.append("\n");
        }
        matrizStr.append("\n");
        System.out.println(matrizStr.toString());
    }

    private static void pressEnter() {
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
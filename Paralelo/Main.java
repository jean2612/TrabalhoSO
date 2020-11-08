import java.util.Scanner;

public class Main {
    public static final int MATRIZ_LENGTH = 1000;
    public static final int MAX_NUM = 100;
    public static int op = 2; // 1 - sequencial; 2 - aleatório
    public static int imprimir = 1;

    static Matriz matrizEntrada;
    static Matriz matrizSaida;
    static Auxiliar auxiliar;

    public static void main(String[] args) {
        long timeInicio = System.currentTimeMillis();

        matrizEntrada = new Matriz(MATRIZ_LENGTH);
        matrizSaida = new Matriz(MATRIZ_LENGTH);
        auxiliar = new Auxiliar();
        Preenchedor preenchedor = new Preenchedor();
        Leitor leitor = new Leitor();
        Transportador transportador = new Transportador();


         switch (op) {
             case 1:
                    preenchedor.preencherSequencial();
                    leitor.lerSequencial();
                    transportador.transportarMatriz();
                 break;
             case 2:
                 preenchedor.preencherAleatorio();
                 leitor.lerAleatorio();
                 transportador.transportarMatriz();
                 break;
             default:
                 System.out.println("Opção incorreta.");
                 return;
         }

        long timeFim = System.currentTimeMillis();

         if(imprimir == 1){
             System.out.println("Imprimir matriz de entrada:");
             pressioneEnter();
             matrizEntrada.imprimir();

             System.out.println("Imprimir matriz de saída:");
             pressioneEnter();
             matrizSaida.imprimir();
         }

        System.out.println("Tempo para preencher matriz: " + ((timeFim - timeInicio)) + " milisegundos.");
        System.out.println("Tempo total de execução: " + ((System.currentTimeMillis() - timeInicio)) + " milisegundos.");

//        do{
//
//            System.out.print("[1] Escreve matriz do de forma  sequencial;\n[2] Escreve primeiro posições pares para depois impares;\n[7]Sobre;\n[0]Sair.\nDigite: ");
//            Scanner entrada = new Scanner(System.in);
//            op = entrada.nextInt();
//
//            switch (op){
//                case 1:
//                    preenchedor.preencherSequencial();
//                    break;
//                case 2:
//                    preenchedor.preencherAleatorio();
//                    break;
//                case 7:
//                    System.out.println("Programa feito por Jean e Thiago para SO\n");
//                    break;
//                case 0:
//                    System.out.println("Saindo!");
//                    break;
//                default:
//                    System.out.println("Número inválido");
//            }
//        }while(op!=0);
    }

    private static void pressioneEnter(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

}


//    public void preencherSequencial(){
//        long timeInicio = System.currentTimeMillis();
//
//        for(int i=0; i<matriz.length(); i++){
//            for(int j=0; j<matriz.length(); j++){
//                matriz.set(i, j, gerarNum());
//            }
//        }
//
//        long timeFim = System.currentTimeMillis();
//
//        matriz.imprimir();
//
//        System.out.println("Tempo para preencher matriz: " + ((timeFim - timeInicio)) + " milisegundos.");
//        System.out.println("Tempo total de execução: " + ((System.currentTimeMillis() - timeInicio)) + " milisegundos.");
//    }
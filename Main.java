import java.util.Scanner;

public class Main {
    public static final int MATRIZ_LENGTH = 1000;

    public static void main(String[] args){

        Matriz matriz = new Matriz(MATRIZ_LENGTH);
        Auxiliar auxiliar = new Auxiliar(matriz);
        Preenchedor preenchedor = new Preenchedor(matriz, auxiliar);
        Leitor leitor = new Leitor(matriz, auxiliar);

        int op;

        do{

            System.out.print("[1] Escreve matriz do de forma  sequencial;\n[2] Escreve primeiro posições pares para depois impares;\n[7]Sobre;\n[0]Sair.\nDigite: ");
            Scanner entrada = new Scanner(System.in);
            op = entrada.nextInt();

            switch (op){
                case 1:
                    preenchedor.preencherSequencial();
                    break;
                case 2:
                    preenchedor.preencherAleatorio();
                    break;
                case 7:
                    System.out.println("Programa feito por Jean e Thiago para SO\n");
                    break;
                case 0:
                    System.out.println("Saindo!");
                    break;
                default:
                    System.out.println("Número inválido");
            }
        }while(op!=0);
    }

}

import java.util.Random;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mat_class{

  public Mat_class(){

    //construtor

  }
  
  public void begin() {
    int num =143;
                  
      do{
          
        System.out.println("[1] Escreve matriz do de forma  sequencial;\n[2] Escreve primeiro posições pares para depois impares;\n[7]Sobre;\n[0]Sair.\nDigite: ");
        Scanner entrada = new Scanner(System.in);
        num = entrada.nextInt(); 

          switch (num){                
              case 1:
                matrizSequencial();
                break;
              case 2:
                matrizAleatoria();
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
      }while(num!=0);
  }


  public static void matrizSequencial(){        
    long timeInicio = System.currentTimeMillis();
    Random random = new Random();
    int m[][] = new int[8][8];
    
      for(int i=0; i<m.length; i++){
          for(int j=0; j<m.length; j++){          
              int numero = random.nextInt(100);
              m[i][j]=numero;
          }
      }
      for(int i=0; i<m.length; i++){
        for(int j=0; j<m.length; j++){
            System.out.print(m[i][j] + " - ");
        }
        System.out.println();
      } 
    System.out.println("Tempo total de execução: " + ((System.currentTimeMillis() - timeInicio)) + " milisegundos.");
  }


  public static void matrizAleatoria(){
    long timeInicio = System.currentTimeMillis();
    Random random = new Random();        
    int m[][] = new int[10][10];
    
    int cont=0;
    int i=0;
    int j=0;
    int valor=0;
    int numero=0;
    
    ArrayList<Integer> vetOrdenado = new ArrayList<Integer>();

    while(valor!=100){        
      vetOrdenado.add(valor);
      valor++;
    }

    System.out.println(vetOrdenado.toString());
    Collections.shuffle(vetOrdenado);    
    System.out.println(vetOrdenado.toString());    

   // while(cont != 100){
    for(cont=0; cont<100; cont++){ 
    System.out.println(vetOrdenado.get(cont));

      i = (vetOrdenado.get(cont))%10;
      j = (vetOrdenado.get(cont))/10;
      numero = random.nextInt(100);
      m[i][j]=numero;
     // cont++;
    }                  
    for(int q=0; q<m.length; q++){
      for(int w=0; w<m.length; w++){
          System.out.print(m[q][w] + " - ");
      }
      System.out.println();
    }
    System.out.println("Tempo total de execução: " + ((System.currentTimeMillis() - timeInicio)) + " milisegundos.");
  }
 
}    

import java.util.ArrayList;
import java.util.Collections;

public class Auxiliar {
  Matriz matriz;
  // Vetor auxiliar para percorrer a matriz
  ArrayList<Integer> posicoes;

  // Vetor auxiliar para carregar os valores lidos da matriz de entrada
  int[] valores;

  public Auxiliar() {
    this.matriz = Main.matrizEntrada;

    if(Main.op == 2) {

      posicoes = new ArrayList<>();

      // Inicializa o vetor auxiliar com valores de 0 até o número de elementos da matriz
      for (int i = 0; i < matriz.length() * matriz.length(); i++) {
        posicoes.add(i);
      }
    }


    // Inicializa o vetor auxiliar de valores com o o número possível de valores+1 (inicia em zero);
    valores = new int[Main.MAX_NUM + 1];
  }

  // Embaralha vetor posições
  public void shuffle(){
    Collections.shuffle(posicoes);
  }
 
}
import java.util.ArrayList;
import java.util.Collections;

public class Auxiliar {
  Matriz matriz;
  // Vetor auxiliar para percorrer a matriz
  ArrayList<Integer> posicoes;

  // Vetor auxiliar para carregar os valores lidos da matriz de entrada
  ArrayList<Integer> valores;

  public Auxiliar(Matriz matriz) {
    this.matriz = matriz;
    posicoes = new ArrayList<>();

    // Inicializa o vetor auxiliar com valores de 0 até o número de elementos da matriz
    for(int i = 0; i < matriz.length() * matriz.length(); i++){
      posicoes.add(i);
    }

    valores = new ArrayList<>();
    // Inicializa o vetor auxiliar de valores com valor de 0;
    for(int i = 0; i <= 100; i++){
      posicoes.add(0);
    }
  }

  public void shuffle(){
    Collections.shuffle(posicoes);
  }
 
}
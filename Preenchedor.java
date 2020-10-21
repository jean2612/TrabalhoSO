import java.util.Random;

public class Preenchedor {
  Matriz matriz;
  Auxiliar auxiliar;
  Random random = new Random();


  public Preenchedor(Matriz matriz, Auxiliar auxiliar) {
    this.auxiliar = auxiliar;
    this.matriz = matriz;
  }

  public int gerarNum(int numero) {
    return random.nextInt(numero) + 1;
  }

  public int gerarNum() {
    return random.nextInt(99) + 1;
  }

  public void preencherSequencial(){
    long timeInicio = System.currentTimeMillis();
    
      for(int i=0; i<matriz.length(); i++){
          for(int j=0; j<matriz.length(); j++){
              matriz.set(i, j, gerarNum());
          }
      }

      long timeFim = System.currentTimeMillis();

      matriz.imprimir();

      System.out.println("Tempo para preencher matriz: " + ((timeFim - timeInicio)) + " milisegundos.");
      System.out.println("Tempo total de execução: " + ((System.currentTimeMillis() - timeInicio)) + " milisegundos.");
  }


  public void preencherAleatorio() {
    long timeInicio = System.currentTimeMillis();

    // Mistura o vetor auxiliar para termos posições aleatórias
    auxiliar.shuffle();

    // Percorre o vetor auxiliar e popula a matriz com números aleatórios nas posições
    int x, y;
    for(int i = 0; i<auxiliar.posicoes.size(); i++){
      x = (auxiliar.posicoes.get(i)) % matriz.length();
      y = (auxiliar.posicoes.get(i)) / matriz.length();

      // Carrega valot aleatório entre 1 e 100 na posição [x,y] da matriz
      matriz.set(x, y, gerarNum());
    }

    long timeFim = System.currentTimeMillis();

    matriz.imprimir();
    System.out.println("Tempo para preencher matriz: " + ((timeFim - timeInicio)) + " milisegundos.");
    System.out.println("Tempo total de execução: " + ((System.currentTimeMillis() - timeInicio)) + " milisegundos.");
  }
 
}
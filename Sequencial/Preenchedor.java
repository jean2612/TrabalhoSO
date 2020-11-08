import java.util.Random;

public class Preenchedor {
  Matriz matriz;
  Auxiliar auxiliar;
  Random random = new Random();

  // Construtor padrão, referencia matriz de entrada e vetor auxiliar do Main
  public Preenchedor() {
    this.auxiliar = Main.auxiliar;
    this.matriz = Main.matrizEntrada;
  }

  public Preenchedor(Matriz matriz, Auxiliar auxiliar) {
    this.auxiliar = auxiliar;
    this.matriz = matriz;
  }

  // Retorna um valor entre 1 e MAX_NUM
  public int gerarNum() {
    return random.nextInt(Main.MAX_NUM) + 1;
  }

  // Retorna um valor entre 1 e @numero
  public int gerarNum(int numero) {
    return random.nextInt(numero) + 1;
  }

  public void preencherSequencial(){
      for(int i=0; i<matriz.length(); i++){
          for(int j=0; j<matriz.length(); j++){
              matriz.set(i, j, gerarNum());
          }
      }
  }


  public void preencherAleatorio() {

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
  }
 
}